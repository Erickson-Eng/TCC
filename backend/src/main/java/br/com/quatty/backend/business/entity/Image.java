package br.com.quatty.backend.business.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "image")
public class Image implements Serializable {
    private static final long serialVersionUID = -9212144751510789743L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    private String type;

    @Lob
    @Column(name = "imagedata")
    private byte[] imageData;

    @OneToOne(mappedBy = "communityImage")
    private Community community;

}