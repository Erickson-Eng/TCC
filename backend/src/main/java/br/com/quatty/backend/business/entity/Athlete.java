package br.com.quatty.backend.business.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;
import java.util.List;
import java.util.Set;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "athlete")
@EntityListeners(AuditingEntityListener.class)
public class Athlete extends Profile {
    @Serial
    private static final long serialVersionUID = -8917186030974958615L;

    @OneToMany(mappedBy = "athlete")
    @ToString.Exclude
    private List<Attendance> attendanceList;

    @OneToMany(mappedBy = "athlete")
    @ToString.Exclude
    private Set<Membership> membershipSet;

    @OneToMany(mappedBy = "athlete")
    @ToString.Exclude
    private List<BodyMeasure> bodyMeasures;
}