package br.com.quatty.backend.business.entity;

import br.com.quatty.backend.business.entity.enums.AttendanceConfirmation;
import br.com.quatty.backend.business.entity.pk.AttendancePK;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@Entity
@Table(name = "attendance")
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Attendance implements Serializable {
    @Serial
    private static final long serialVersionUID = -3834859719344436414L;
    @EmbeddedId
    @Column(unique = true, nullable = false)
    private AttendancePK attendancePK;

    @MapsId("bookingId")
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "booking_id", updatable = false, referencedColumnName = "id")
    private Booking booking;

    @MapsId("atlheteId")
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "athlete_id", updatable = false, referencedColumnName = "id")
    private Athlete athlete;

    @Enumerated(EnumType.STRING)
    private AttendanceConfirmation attendanceConfirmation;

    @Column(name = "created_date", nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdDate;

    @Column(name = "modified_date")
    @LastModifiedDate
    private LocalDateTime modifiedDate;

    @Column(name = "created_by", unique = true)
    @CreatedBy
    private String createdBy;

    @Column(name = "modified_by")
    @LastModifiedBy
    private String modifiedBy;


    public void setAttendanceConfirmation(String attendanceConfirmation) {
        this.attendanceConfirmation = AttendanceConfirmation.getAttendanceStatus(attendanceConfirmation);
    }
}