package br.com.quatty.backend.business.service.postgres;

import br.com.quatty.backend.api.dto.filter.BookingFilterParams;
import br.com.quatty.backend.api.dto.mapper.BookingMapper;
import br.com.quatty.backend.api.dto.request.BookingRequest;
import br.com.quatty.backend.api.dto.response.BookingResponse;
import br.com.quatty.backend.api.dto.table.BookingTableResponse;
import br.com.quatty.backend.business.entity.Booking;
import br.com.quatty.backend.business.entity.enums.ApplicationState;
import br.com.quatty.backend.business.service.BookingService;
import br.com.quatty.backend.business.service.exception.EntityNotFoundException;
import br.com.quatty.backend.business.service.exception.ValidationException;
import br.com.quatty.backend.infra.repository.BookingRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BookingServicePostgresql implements BookingService {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private BookingRepository bookingRepository;
    private BookingMapper bookingMapper;

    @Override
    public BookingResponse createBooking(BookingRequest bookingRequest) {
        validateBooking(bookingRequest);
        var entity = bookingMapper.bookingRequestToEntity(bookingRequest);
        entity = bookingRepository.save(entity);
        return bookingMapper.entityToBookingResponse(entity);
    }

    private void validateBooking(BookingRequest bookingRequest) {
        LocalDateTime checkinDateTime = parseDateTime(bookingRequest.getCheckinBooking(), bookingRequest.getCheckinBooking());
        LocalDateTime checkoutDateTime = parseDateTime(bookingRequest.getCheckoutBooking(), bookingRequest.getCheckoutBooking());

        validateDateTime(checkinDateTime, "check-in");
        validateDateTime(checkoutDateTime, "check-out");
        validateTimeInterval(checkinDateTime, checkoutDateTime);
    }

    @Override
    public BookingResponse updateStatusForBooking(Long id, BookingRequest bookingRequest) {
        if (!bookingRequest.getApplicationState().equalsIgnoreCase(ApplicationState.WAITING_FOR_APPROVAL.getCode())){
            throw new ValidationException("Só é possível realizar alteração de um agendmaento que está aguardando por aprovação.");
        }

        var entity  = verifyIfExist(id);

        return null;
    }

    @Override
    public void deleteBooking(Long id) {
        var entity = verifyIfExist(id);
        bookingRepository.delete(entity);
    }

    @Override
    public BookingTableResponse findBookingByParams(BookingFilterParams bookingFilterParams) {
        List<Booking> entityList = new ArrayList<>();
        if (bookingFilterParams.getCommunityId() != null){
           entityList = bookingRepository.findAllByCommunityId(bookingFilterParams.getCommunityId());
        }
        else if (bookingFilterParams.getGymId() != null){
           entityList = bookingRepository.findAllByGymId(bookingFilterParams.getGymId());
        }
        var entityResponse = entityList.stream().map(bookingMapper::entityToBookingResponse).toList();
        return BookingTableResponse.builder().bookingResponseList(entityResponse).build();
    }


    private Booking verifyIfExist(Long id){
        return bookingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        MessageFormat.format("Error locating {} in database from id {}", Booking.class.getName(), id)));
    }


    private LocalDateTime parseDateTime(String date, String time) {
        String dateTimeString = date + " " + time;
        return LocalDateTime.parse(dateTimeString, DATE_TIME_FORMATTER);
    }

    private void validateDateTime(LocalDateTime dateTime, String fieldName) {
        LocalDateTime currentDateTime = LocalDateTime.now();

        if (dateTime.isBefore(currentDateTime)) {
            throw new IllegalArgumentException("A data e hora de " + fieldName + " não podem ser anteriores ao momento atual.");
        }
    }

    private void validateTimeInterval(LocalDateTime checkinDateTime, LocalDateTime checkoutDateTime) {
        long intervalHours = checkinDateTime.until(checkoutDateTime, ChronoUnit.HOURS);

        if (intervalHours < 6) {
            throw new IllegalArgumentException("O intervalo mínimo para realizar uma reserva é de 6 horas.");
        }
    }
}
