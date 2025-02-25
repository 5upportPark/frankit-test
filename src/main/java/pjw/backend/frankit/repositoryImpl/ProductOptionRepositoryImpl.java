package pjw.backend.frankit.repositoryImpl;

import org.springframework.stereotype.Repository;
import pjw.backend.frankit.entity.ProductOption;
import pjw.backend.frankit.repository.ProductOptionRepository;

import java.util.List;

@Repository
public interface ProductOptionRepositoryImpl extends ProductOptionRepository {
    List<ProductOption> findAllByProductId(Long productId);
}
