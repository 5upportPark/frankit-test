package pjw.backend.frankit.repositoryImpl;

import org.springframework.stereotype.Repository;
import pjw.backend.frankit.entity.User;
import pjw.backend.frankit.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepositoryImpl extends UserRepository {
    List<User> findAll();
    Optional<User> findAllByEmail(String email);
}
