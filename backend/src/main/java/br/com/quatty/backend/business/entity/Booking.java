package br.com.quatty.backend.business.entity;

import br.com.quatty.backend.business.entity.enums.ApplicationState;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "booking")
@EntityListeners(AuditingEntityListener.class)
public class Booking implements Serializable {
    @Serial
    private static final long serialVersionUID = 3888718628884874687L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String text;
    @Column(nullable = false)
    private DayOfWeek day;
    @Column(nullable = false)
    private LocalDateTime checkinBooking;
    @Column(nullable = false)
    private LocalDateTime checkoutBooking;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApplicationState applicationState;
    @OneToMany(mappedBy = "booking")
    @ToString.Exclude
    private List<Attendance> attendanceList;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(nullable = false, referencedColumnName = "id")
    private Gym gym;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(nullable = false, referencedColumnName = "id")
    private Community community;

    @Column(name = "created_date", nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdDate;

    @Column(name = "modified_date")
    @LastModifiedDate
    private LocalDateTime modifiedDate;

    @Column(name = "created_by")
    @CreatedBy
    private String createdBy;

    @Column(name = "modified_by")
    @LastModifiedBy
    private String modifiedBy;


    public void setApplicationState(String membershipStatus) {
        this.applicationState = ApplicationState.getApplicationState(membershipStatus);
    }
}