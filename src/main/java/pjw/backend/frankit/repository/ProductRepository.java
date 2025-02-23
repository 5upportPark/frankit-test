package pjw.backend.frankit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pjw.backend.frankit.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
