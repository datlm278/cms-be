package com.example.cmsbe.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "CMS_CINEMA_SHOWTIME")
public class CinemaShowtime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CINEMA_ID")
    private Long cinemaId;

    @Column(name = "SHOWTIME_ID")
    private Long showtimeId;
}
