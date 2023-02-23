package br.com.quatty.backend.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequest {

    private String checkinBooking;
    private String checkoutBooking;
    private Long gymId;
    private Long communityId;
}
