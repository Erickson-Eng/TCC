package br.com.quatty.backend.business.entity.pk;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class AttendancePK implements Serializable {

    @Serial
    private static final long serialVersionUID = 656614889487771797L;

    @Column(name = "athlete_id")
    private Long athleteId;

    @Column(name = "booking_id")
    private Long bookingId;
}
