package br.com.quatty.backend.infra.repository;

import br.com.quatty.backend.business.entity.Attendance;
import br.com.quatty.backend.business.entity.pk.AttendancePK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, AttendancePK> {
}