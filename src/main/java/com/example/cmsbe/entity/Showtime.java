package com.example.cmsbe.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
@Table(name = "cms_showtime")
public class Showtime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "DAY")
    private Date day;

    @Column(name = "HOUR")
    private Time hour;

    @Column(name = "STATUS")
    private Long status;

    @Column(name = "CREATE_TIME", updatable = false)
    private Timestamp createTime;

    @Column(name = "UPDATE_TIME")
    private Timestamp updateTime;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "cms_cinema_showtime",
            joinColumns = {@JoinColumn(name = "showtime_id")},
            inverseJoinColumns = {@JoinColumn(name = "cinema_id")})
    private List<Cinema> cinemas;
}
