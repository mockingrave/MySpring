package myspring.database.JPA.repositories;

import myspring.database.JPA.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update User u set u.password = :newPassword where u.login = :login")
    void updatePassword(@Param("login") String login, @Param("newPassword") String newPassword);
}