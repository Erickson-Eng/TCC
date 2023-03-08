package br.com.quatty.backend.infra.repository;

import br.com.quatty.backend.business.entity.Sport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SportRepository extends JpaRepository<Sport, Long> {

    @Query("select s from Sport s where upper(s.name) like concat('%', upper(:name), '%' ) ")
    List<Sport> findAllByName(String name);
}