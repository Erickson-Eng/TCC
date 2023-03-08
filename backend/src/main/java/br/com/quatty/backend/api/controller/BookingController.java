package br.com.quatty.backend.api.controller;

import br.com.quatty.backend.api.dto.filter.BookingFilterParams;
import br.com.quatty.backend.api.dto.request.BookingRequest;
import br.com.quatty.backend.api.dto.response.BookingResponse;
import br.com.quatty.backend.api.dto.table.BookingTableResponse;
import br.com.quatty.backend.business.service.BookingService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/booking")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BookingController {

    private BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingResponse> createBookingForGym(@RequestBody @Valid BookingRequest bookingRequest){
        var booking = bookingService.createBooking(bookingRequest);
        URI uri = URI.create(booking.getId().toString());
        return ResponseEntity.created(uri).body(booking);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookingResponse> deleteBooking(@PathVariable Long id){
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<BookingTableResponse> findBookingByFilter(BookingFilterParams bookingFilterParams){
        var booking = bookingService.findBookingByParams(bookingFilterParams);
        return ResponseEntity.ok().body(booking);
    }

}
