package br.com.quatty.backend.infra.repository;

import br.com.quatty.backend.business.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}