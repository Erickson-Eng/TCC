package br.com.quatty.backend.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingResponse {

    private String checkinBooking;
    private String checkoutBooking;

    private Long gymId;
    private String gymName;
    private Long communityId;
    private String communityName;
    private String createdDate;
    private String modifiedDate;
}
