package br.com.quatty.backend.business.service;

import br.com.quatty.backend.api.dto.filter.BookingFilterParams;
import br.com.quatty.backend.api.dto.request.BookingRequest;
import br.com.quatty.backend.api.dto.response.BookingResponse;
import br.com.quatty.backend.api.dto.table.BookingTableResponse;

public interface BookingService {

    BookingResponse createBooking(BookingRequest bookingRequest);

    BookingResponse updateStatusForBooking(Long id, BookingRequest bookingRequest);

    void deleteBooking(Long id);

    BookingTableResponse findBookingByParams(BookingFilterParams bookingFilterParams);
}
