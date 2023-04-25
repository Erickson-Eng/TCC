package br.com.quatty.backend.infra.repository;

import br.com.quatty.backend.business.entity.BodyMeasure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BodyMeasureRepository extends JpaRepository<BodyMeasure, Long> {
}