package br.com.quatty.backend.api.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceRequest {

    @NotNull(message = "booking id is mandatory")
    private Long bookingId;

    @NotNull(message = "athlete id is mandatory")
    private Long athleteId;

    @NotNull(message = "confirmation is mandatory")
    private String attendanceConfirmation;
}
