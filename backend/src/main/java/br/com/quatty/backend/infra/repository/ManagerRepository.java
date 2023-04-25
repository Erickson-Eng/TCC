package br.com.quatty.backend.infra.repository;

import br.com.quatty.backend.business.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
}