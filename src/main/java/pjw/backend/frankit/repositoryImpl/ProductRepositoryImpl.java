package pjw.backend.frankit.repositoryImpl;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import pjw.backend.frankit.entity.Product;
import pjw.backend.frankit.repository.ProductRepository;

import java.util.List;

@Repository
public interface ProductRepositoryImpl extends ProductRepository {
    List<Product> findByIdLessThan(Long id, Pageable page);
}
