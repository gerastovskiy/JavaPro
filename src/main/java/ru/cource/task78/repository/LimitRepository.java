package ru.cource.task78.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import ru.cource.task78.model.Limit;
import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface LimitRepository extends CrudRepository<Limit, Long> {
    Optional<Limit> findByUserId(Long userId);
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update Limit l set l.limits = :lim")
    void updateAllByDefault(@Param("lim") BigDecimal limit);
}