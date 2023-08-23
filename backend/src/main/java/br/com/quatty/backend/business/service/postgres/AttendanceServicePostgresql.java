package br.com.quatty.backend.business.service.postgres;

import br.com.quatty.backend.api.dto.mapper.AttendanceMapper;
import br.com.quatty.backend.api.dto.request.AttendanceRequest;
import br.com.quatty.backend.business.entity.Attendance;
import br.com.quatty.backend.business.service.AttendanceService;
import br.com.quatty.backend.infra.repository.AttendanceRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class AttendanceServicePostgresql implements AttendanceService {

    private AttendanceRepository attendanceRepository;
    private AttendanceMapper attendanceMapper;

    @Override
    public void createAttendance(AttendanceRequest attendanceRequest) {
        log.info("[ATTENDANCE-SERVICE] - CREATE A ATTENDANCE");
        Attendance attendance = attendanceMapper.createEntityFromDTO(attendanceRequest);
        attendanceRepository.save(attendance);
    }
}
