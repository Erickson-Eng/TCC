package br.com.quatty.backend.business.entity;

import br.com.quatty.backend.business.entity.enums.ApplicationState;
import br.com.quatty.backend.business.entity.pk.MembershipPK;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "membership")
public class Membership implements Serializable {
    @Serial
    private static final long serialVersionUID = -7406087354711767070L;

    @EmbeddedId
    @Column(unique = true, nullable = false)
    private MembershipPK membershipPK;

    @MapsId("athleteId")
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "athlete_id", updatable = false, referencedColumnName = "id")
    private Athlete athlete;

    @MapsId("communityId")
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "community_id", updatable = false, referencedColumnName = "id")
    private Community community;

    @Enumerated(EnumType.STRING)
    private ApplicationState applicationState;

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