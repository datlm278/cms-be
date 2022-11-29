package com.example.cmsbe.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@Table(name = "CMS_CINEMA_ROOM")
public class CinemaRoom implements Serializable {

    @Serial
    private static final long serialVersionUID = 8081573080109375485L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ROOM_CODE")
    private String roomCode;

    @Column(name = "TOTAL_SEATS")
    private Long totalSeats;

    @Column(name = "SCREEN_TYPE")
    private String screenType;

    @Column(name = "STATUS")
    private Long status;

    @Column(name = "CREATE_TIME", updatable = false)
    private Timestamp createTime;

    @Column(name = "UPDATE_TIME")
    private Timestamp updateTime;

    @OneToMany(mappedBy = "cinemaRoom", cascade = CascadeType.ALL)
    private List<SeatRoom> seatRooms;
}
