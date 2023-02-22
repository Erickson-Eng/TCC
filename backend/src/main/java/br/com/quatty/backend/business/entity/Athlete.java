package br.com.quatty.backend.business.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "athlete")
@EntityListeners(AuditingEntityListener.class)
public class Athlete extends Profile {
    private static final long serialVersionUID = -8917186030974958615L;
    private Double weight;
    private Double height;
    private Double bicepsMeasurement;
    private Double forearmMeasurement;
    private Double chestMeasurement;
    private Double thighMeasurement;
    private Double calfMeasurement;

    @OneToMany(mappedBy = "athlete")
    @ToString.Exclude
    private Set<Membership> membershipSet;
}