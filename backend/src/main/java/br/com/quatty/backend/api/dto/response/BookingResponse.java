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

    private Long id;
    private String checkinBooking;
    private String checkoutBooking;

    private String text;

    private Long gymId;
    private String gymName;

    private String applicationState;
    private Long communityId;
    private String communityName;
    private String createdDate;
    private String modifiedDate;
}
