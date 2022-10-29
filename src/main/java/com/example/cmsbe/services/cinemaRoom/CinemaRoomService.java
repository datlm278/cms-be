package com.example.cmsbe.services.cinemaRoom;

import com.example.cmsbe.common.constant.CMSConstant;
import com.example.cmsbe.dto.request.CinemaRoomRequest;
import com.example.cmsbe.dto.response.CinemaRoomResponse;
import com.example.cmsbe.entity.CinemaRoom;
import com.example.cmsbe.entity.SeatRoom;
import com.example.cmsbe.repositories.CinemaRoomRepository;
import com.example.cmsbe.repositories.SeatRoomRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CinemaRoomService implements ICinemaRoomService {

    @Autowired
    private final CinemaRoomRepository cinemaRoomRepository;

    @Autowired
    private final SeatRoomRepository seatRoomRepository;

    public CinemaRoomService(CinemaRoomRepository cinemaRoomRepository, SeatRoomRepository seatRoomRepository) {
        this.cinemaRoomRepository = cinemaRoomRepository;
        this.seatRoomRepository = seatRoomRepository;
    }

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void createCinemaRoom(CinemaRoomRequest request) {
        CinemaRoom cinemaRoom = modelMapper.map(request, CinemaRoom.class);
        cinemaRoom.setCreateTime(Timestamp.from(Instant.now()));

        Optional<CinemaRoom> room = cinemaRoomRepository.findByRoomCode(request.getRoomCode());

        if (room.isPresent()) {
            throw new RuntimeException("room is existed");
        }

        cinemaRoomRepository.save(cinemaRoom);
        seatRoomRepository.saveAllAndFlush(generateSeatRoom(cinemaRoom));
    }

    @Override
    public void updateCinemaRoom(CinemaRoomRequest request, Long id) {
        if (!request.getId().equals(id)) {
            throw new NotFoundException("cinema room id request not equal id request");
        }
        CinemaRoom roomRequest = modelMapper.map(request, CinemaRoom.class);
        roomRequest.setUpdateTime(Timestamp.from(Instant.now()));

        CinemaRoom room = cinemaRoomRepository.findById(id).orElseThrow(() -> new NotFoundException("cinema room isn't existed"));
        if (!room.getTotalSeats().equals(roomRequest.getTotalSeats()) || !room.getRoomCode().equals(roomRequest.getRoomCode())) {
            List<SeatRoom> seatRooms = seatRoomRepository.findSeatByCinemaRoomCode(room.getRoomCode());
            for (SeatRoom seatRoom : seatRooms) {
                seatRoomRepository.deleteById(seatRoom.getId());
            }
        }
        cinemaRoomRepository.save(roomRequest);
        seatRoomRepository.saveAllAndFlush(generateSeatRoom(roomRequest));

    }

    @Override
    public void deleteCinemaRoom(Long id) {
        cinemaRoomRepository.findById(id).orElseThrow(() -> new NotFoundException("cinema room isn't existed"));
        cinemaRoomRepository.deleteById(id);
    }

    @Override
    public List<CinemaRoomResponse> findAllCinemaRoom() {

        List<CinemaRoom> cinemaRooms = cinemaRoomRepository.findAll();
        return cinemaRooms.stream().map(x -> modelMapper.map(x, CinemaRoomResponse.class)).collect(Collectors.toList());
    }

    @Override
    public CinemaRoomResponse findCinemaRoomById(Long id) {
        CinemaRoom cinemaRoom = cinemaRoomRepository.findById(id).orElseThrow(() -> new NotFoundException("cinema room isn't existed"));
        return modelMapper.map(cinemaRoom, CinemaRoomResponse.class);
    }

    private List<SeatRoom> generateSeatRoom(CinemaRoom cinemaRoom) {
        List<SeatRoom> seatRooms = new ArrayList<>();
        for (char j = 'A'; j <= 'Z'; j++) {
            for (int i = 1; i <= CMSConstant.SEATS_OF_ROW; i++) {
                if (seatRooms.size() < cinemaRoom.getTotalSeats()) {
                    SeatRoom seatRoom = new SeatRoom();
                    seatRoom.setSeatNumber((long) i);
                    seatRoom.setRowName(String.valueOf(j));
                    seatRoom.setSeatPosition(j + "-" + i);
                    seatRoom.setStatus(1L);
                    seatRoom.setCreateTime(Timestamp.from(Instant.now()));
                    seatRoom.setCinemaRoom(cinemaRoom);
                    seatRooms.add(seatRoom);
                }
            }
        }
        return seatRooms;
    }
}
