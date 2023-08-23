package br.com.quatty.backend.api.dto.mapper;

import br.com.quatty.backend.api.dto.request.AttendanceRequest;
import br.com.quatty.backend.api.dto.response.AttendanceResponse;
import br.com.quatty.backend.business.entity.Attendance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AttendanceMapper {


    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "booking.id", source = "bookingId")
    @Mapping(target = "athlete.id", source = "athleteId")
    @Mapping(target = "attendanceConfirmation", source = "attendanceConfirmation")
    Attendance createEntityFromDTO(AttendanceRequest attendanceRequest);

    @Mapping(target = "bookingId", source = "booking.id")
    @Mapping(target = "athleteId", source = "athlete.id")
    AttendanceResponse createResponseFromEntity(Attendance attendance);
}
