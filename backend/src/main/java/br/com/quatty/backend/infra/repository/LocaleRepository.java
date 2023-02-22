package br.com.quatty.backend.infra.repository;

import br.com.quatty.backend.business.entity.Locale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocaleRepository extends JpaRepository<Locale, Long> {
}