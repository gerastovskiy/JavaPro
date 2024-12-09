package ru.cource.task78.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.cource.task78.model.Limit;
import ru.cource.task78.repository.LimitRepository;
import ru.cource.task78.service.LimitService;
import java.math.BigDecimal;

@Service
public class LimitServiceImpl implements LimitService {
    private final LimitRepository limitRepository;
    private final BigDecimal LIMIT = new BigDecimal(10000);

    @Autowired
    public LimitServiceImpl(LimitRepository limitRepository) {
        this.limitRepository = limitRepository;
    }

    @Override
    public Limit getLimit(Long userId) {
        var limit = limitRepository.findByUserId(userId);
        return limit.orElse(new Limit(null, userId, LIMIT));
    }

    @Override
    public Limit creditLimit(Long userId, BigDecimal payment) {
        var limit = getLimit(userId);
        limit.setLimits(limit.getLimits().add(payment));
        limitRepository.save(limit);
        return limit;
    }

    @Override
    public Limit debitLimit(Long userId, BigDecimal payment) {
        var limit = getLimit(userId);
        if (limit.getLimits().compareTo(payment) > 0) {
            limit.setLimits(limit.getLimits().subtract(payment));
            limitRepository.save(limit);
        }
        else throw new IllegalArgumentException(String.format("The limit %s does not allow to process a transaction", limit.getLimits().toString()));
        return limit;
    }

    @Override
    @Scheduled(cron = "@daily")
    @Transactional
    public void setDefaultLimit() {
        limitRepository.updateAllByDefault(LIMIT);
    }
}
