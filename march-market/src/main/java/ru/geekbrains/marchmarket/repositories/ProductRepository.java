package ru.geekbrains.marchmarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.marchmarket.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
}
