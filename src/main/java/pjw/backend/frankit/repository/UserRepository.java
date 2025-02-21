package pjw.backend.frankit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pjw.backend.frankit.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
