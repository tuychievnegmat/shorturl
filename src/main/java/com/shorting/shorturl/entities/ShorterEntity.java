package com.shorting.shorturl.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(schema = "shorturlbase",name = "shorturl")
@Getter
@Setter
public class ShorterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false, unique=true)
    private Long id;

    @Column(name = "hash")
    private String hash;

    @Column(name = "original_url")
    private String originalUrl;

    public ShorterEntity(String hash, String originalUrl) {
        this.hash = hash;
        this.originalUrl = originalUrl;
    }

    public ShorterEntity() {

    }
}
