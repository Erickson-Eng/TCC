package br.com.quatty.backend.business.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.util.List;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "manager")
public class Manager extends Profile {
    @Serial
    private static final long serialVersionUID = -8757624701631735762L;

    @OneToMany(mappedBy = "manager")
    @ToString.Exclude
    private List<Gym> gyms;
}