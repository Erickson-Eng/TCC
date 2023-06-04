package br.com.quatty.backend.infra.repository;

import br.com.quatty.backend.business.entity.Locale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface LocaleRepository extends JpaRepository<Locale, Long> {

    @Query("select l from Locale l join Gym g on g.locale.id = l.id where g.id = :id")
    Optional<Locale> getLocaleByGymId(Long id);
}