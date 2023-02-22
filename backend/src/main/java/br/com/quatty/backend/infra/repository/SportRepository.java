package br.com.quatty.backend.infra.repository;

import br.com.quatty.backend.business.entity.Sport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SportRepository extends JpaRepository<Sport, Long> {
}