package br.com.quatty.backend.api.dto.mapper;

import br.com.quatty.backend.api.dto.request.BookingRequest;
import br.com.quatty.backend.api.dto.response.BookingResponse;
import br.com.quatty.backend.business.entity.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookingMapper {


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "gym.id", source = "gymId")
    @Mapping(target = "community.id", source = "communityId")
    @Mapping(target = "checkinBooking", dateFormat = "dd/MM/yyyy HH:mm:ss")
    @Mapping(target = "checkoutBooking", dateFormat = "dd/MM/yyyy HH:mm:ss")
    Booking bookingRequestToEntity(BookingRequest bookingRequest);


    @Mapping(target = "communityId", source = "community.id")
    @Mapping(target = "communityName", source = "community.name")
    @Mapping(target = "gymId", source = "gym.id")
    @Mapping(target = "gymName", source = "gym.name")
    @Mapping(target = "checkinBooking", dateFormat = "dd/MM/yyyy HH:mm:ss")
    @Mapping(target = "checkoutBooking", dateFormat = "dd/MM/yyyy HH:mm:ss")
    @Mapping(target = "createdDate", dateFormat = "dd/MM/yyyy HH:mm:ss")
    @Mapping(target = "modifiedDate", dateFormat = "dd/MM/yyyy HH:mm:ss")
    BookingResponse entityToBookingResponse(Booking booking);
}
