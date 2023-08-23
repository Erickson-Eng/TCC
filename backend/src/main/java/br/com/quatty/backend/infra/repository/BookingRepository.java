package br.com.quatty.backend.infra.repository;

import br.com.quatty.backend.business.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findAllByCommunityId(Long id);
    List<Booking> findAllByGymId(Long id);
}