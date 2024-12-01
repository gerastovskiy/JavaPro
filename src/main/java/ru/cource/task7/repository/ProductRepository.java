package ru.cource.task7.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.cource.task7.model.Product;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    Optional<Product> findByAccountNumber(String accountNumber);
    List<Product> findByUserId(Long userId);
    void deleteByAccountNumber(String accountNumber);
}
