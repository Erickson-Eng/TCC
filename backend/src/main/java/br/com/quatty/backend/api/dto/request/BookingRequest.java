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
public class BookingRequest {

    @NotNull(message = "Entry time is required")
    private String checkinBooking;
    @NotNull(message = "Departure time is mandatory")
    private String checkoutBooking;
    @NotNull(message = "the id of the gym being rented is mandatory")
    private Long gymId;
    @NotNull(message = "the id of the community that requested the lease is mandatory")
    private Long communityId;
    private String applicationState;
}
