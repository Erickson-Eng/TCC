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
public class PracticablePK implements Serializable {
    @Serial
    private static final long serialVersionUID = 656614889487771797L;

    @Column(name = "gym_id")
    private Long gymId;
    @Column(name = "sport_id")
    private Long sportId;

}
