package com.example.cmsbe.services.seatRoom;

import com.example.cmsbe.dto.request.SeatRoomRequest;
import com.example.cmsbe.dto.response.SeatRoomResponse;
import com.example.cmsbe.entity.SeatRoom;
import com.example.cmsbe.mapper.SeatRoomMapper;
import com.example.cmsbe.repositories.SeatRoomRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeatRoomService implements ISeatRoomService {

    private final SeatRoomRepository seatRoomRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public SeatRoomService(SeatRoomRepository seatRoomRepository, ModelMapper modelMapper) {
        this.seatRoomRepository = seatRoomRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public SeatRoomResponse create(SeatRoomRequest seatRoomRequest) {
        return null;
    }

    @Override
    public SeatRoomResponse update(SeatRoomRequest seatRoomRequest, Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<SeatRoomResponse> findAllSeatRoom() {
        List<SeatRoom> seatRooms = seatRoomRepository.findAll();
        return seatRooms.stream()
                .map(SeatRoomMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public SeatRoomResponse findSeatRoomById(Long id) {
        return null;
    }
}
