package com.example.cmsbe.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "CMS_SEAT_ROOM")
public class SeatRoom implements Serializable {

    @Serial
    private static final long serialVersionUID = 8081573080109375485L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ROW_NAME")
    private String rowName;

    @Column(name = "SEAT_NUMBER")
    private Long seatNumber;

    @Column(name = "SEAT_POSITION")
    private String seatPosition;

    @Column(name = "STATUS")
    private Long status;

    @Column(name = "CREATE_TIME", updatable = false)
    private Timestamp createTime;

    @Column(name = "UPDATE_TIME")
    private Timestamp updateTime;

    @ManyToOne
    @JoinColumn(name = "CINEMA_ROOM_CODE", referencedColumnName = "ROOM_CODE")
    private CinemaRoom cinemaRoom;
}
