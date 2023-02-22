package br.com.quatty.backend.infra.repository;

import br.com.quatty.backend.business.entity.Gym;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GymRepository extends JpaRepository<Gym, Long> {
}