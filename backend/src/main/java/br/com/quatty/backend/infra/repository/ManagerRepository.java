package br.com.quatty.backend.infra.repository;

import br.com.quatty.backend.business.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ManagerRepository extends JpaRepository<Manager, Long> {

    @Query("select m from Manager m join User u on u.id = m.user.id where u.email like :email")
    Optional<Manager> getManagerByEmail(@Param("email") String email);
}