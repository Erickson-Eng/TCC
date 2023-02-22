package br.com.quatty.backend.business.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.time.LocalDateTime;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "booking")
public class Booking implements Serializable {
    private static final long serialVersionUID = 3888718628884874687L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime checkinBooking;
    @Column(nullable = false)
    private LocalDateTime checkoutBooking;

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
}