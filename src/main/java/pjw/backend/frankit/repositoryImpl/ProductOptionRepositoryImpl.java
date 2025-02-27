package pjw.backend.frankit.repositoryImpl;

import org.springframework.stereotype.Repository;
import pjw.backend.frankit.entity.ProductOption;
import pjw.backend.frankit.repository.ProductOptionRepository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProductOptionRepositoryImpl extends ProductOptionRepository {
    List<ProductOption> findAllByProductId(Long productId);
    List<ProductOption> findAllByProductIdIn(Collection<Long> productIds);
    long countByProductId(Long productId);
    void deleteByProductId(Long productId);
}
