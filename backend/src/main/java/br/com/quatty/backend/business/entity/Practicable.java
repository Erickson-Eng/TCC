package br.com.quatty.backend.business.entity;

import br.com.quatty.backend.business.entity.enums.PracticableState;
import br.com.quatty.backend.business.entity.pk.PracticablePK;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "practicable")
public class Practicable implements Serializable {
    @Serial
    private static final long serialVersionUID = -7786459425458187270L;

    @EmbeddedId
    @Column(unique = true, nullable = false)
    private PracticablePK practicablePK;

    @MapsId("gymId")
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "gym_id", updatable = false, referencedColumnName = "id")
    private Gym gym;

    @MapsId("sportId")
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "sport_id", updatable = false, referencedColumnName = "id")
    private Sport sport;

    @Enumerated(EnumType.STRING)
    private PracticableState practicableState;

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


    public void setPracticableState(String practicableState) {
        this.practicableState = PracticableState.getPracticableState(practicableState);
    }
}