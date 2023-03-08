package br.com.quatty.backend.api.dto.table;

import br.com.quatty.backend.api.dto.response.BookingResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingTableResponse {

    @JsonProperty("bookingList")
    private List<BookingResponse> bookingResponseList;
}
