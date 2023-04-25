package br.com.quatty.backend.business.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "gym")
public class Gym implements Serializable {
    @Serial
    private static final long serialVersionUID = 7962264726941618041L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(columnDefinition = "TEXT")
    private String rules;
    @OneToOne(cascade = {CascadeType.REFRESH}, orphanRemoval = true)
    private Locale locale;

    @Column(length = 150, nullable = false)
    private String shortDescription;

    @ManyToOne(cascade = CascadeType.REFRESH)
    private Manager manager;

    @OneToMany(mappedBy = "gym")
    @ToString.Exclude
    private Set<Practicable> practicableSet;

    @OneToMany(mappedBy = "gym")
    @ToString.Exclude
    private Set<Booking> bookingSet;

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