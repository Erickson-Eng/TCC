package br.com.quatty.backend.infra.repository;

import br.com.quatty.backend.business.entity.Community;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityRepository extends JpaRepository<Community, Long> {
}