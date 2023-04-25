package br.com.quatty.backend.business.service;

import br.com.quatty.backend.api.dto.request.AttendanceRequest;

public interface AttendanceService {

    void createAttendance(AttendanceRequest attendanceRequest);

}
