package br.com.quatty.backend.infra.repository;

import br.com.quatty.backend.business.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {

    Optional<Image> findByCommunityId(Long id);
}