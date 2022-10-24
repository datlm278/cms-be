package com.example.cmsbe.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "cms_cinema")
public class Cinema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "RELEASE_DATE")
    private Date releaseDate;

    @Column(name = "ENDING_DATE")
    private Date endingDate;

    @Column(name = "POSTER")
    private String poster;

    @Column(name = "DIRECTOR")
    private String director;

    @Column(name = "STATUS")
    private Long status;

    @Column(name = "CREATE_TIME")
    private Timestamp createTime;

    @Column(name = "UPDATE_TIME")
    private Timestamp updateTime;

    @ManyToOne
    @JoinColumn(name = "CINEMA_TYPE_ID", referencedColumnName = "id")
    private CinemaType cinemaTypeId;

    @ManyToOne
    @JoinColumn(name = "PRODUCER_ID", referencedColumnName = "id")
    private Producer producerId;
}
