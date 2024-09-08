package com.filipbyte.record_shop_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "albums")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Integer releaseYear;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    private Integer priceInPence;

    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;
}
