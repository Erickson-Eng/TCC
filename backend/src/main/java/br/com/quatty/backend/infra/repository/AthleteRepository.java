package br.com.quatty.backend.infra.repository;

import br.com.quatty.backend.business.entity.Athlete;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AthleteRepository extends JpaRepository<Athlete, Long> {
}