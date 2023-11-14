package com.works.springboot.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "wikimedia_recentChange")
public class WikimediaDB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Lob
    private String wikimediaEventData;
}
