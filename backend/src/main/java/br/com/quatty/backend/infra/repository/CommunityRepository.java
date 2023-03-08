package br.com.quatty.backend.infra.repository;

import br.com.quatty.backend.business.entity.Community;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommunityRepository extends JpaRepository<Community, Long> {

    List<Community> findAllByName(String name);
}