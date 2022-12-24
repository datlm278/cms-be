package com.example.cmsbe.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "CMS_CINEMA_SHOWTIME")
public class CinemaShowtime implements Serializable {

    private static final long serialVersionUID = 8081573080109375485L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CINEMA_ID")
    private Long cinemaId;

    @Column(name = "SHOWTIME_ID")
    private Long showtimeId;
}
