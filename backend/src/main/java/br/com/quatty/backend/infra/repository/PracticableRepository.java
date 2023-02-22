package br.com.quatty.backend.infra.repository;

import br.com.quatty.backend.business.entity.Practicable;
import br.com.quatty.backend.business.entity.pk.PracticablePK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PracticableRepository extends JpaRepository<Practicable, PracticablePK> {
}