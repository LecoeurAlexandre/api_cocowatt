package com.example.infrastructure.repository.impl;

import com.example.domain.entity.Reservation;
import com.example.domain.port.ReservationRepository;
import com.example.infrastructure.entity.ReservationEntity;
import com.example.infrastructure.repository.ReservationEntityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ReservationRepositoryImpl implements ReservationRepository {

    private final ModelMapper modelMapper;
    private final ReservationEntityRepository reservationEntityRepository;

    public ReservationRepositoryImpl(ModelMapper modelMapper, ReservationEntityRepository reservationEntityRepository) {
        this.modelMapper = modelMapper;
        this.reservationEntityRepository = reservationEntityRepository;
    }

    @Override
    public void save(Reservation reservation) {
        ReservationEntity reservationEntity = modelMapper.map(reservation, ReservationEntity.class);
        reservationEntityRepository.save(reservationEntity);
    }

    @Override
    public Reservation findById(int id) {
        ReservationEntity reservationEntity = reservationEntityRepository.findById(id).get();
        Reservation reservation = modelMapper.map(reservationEntity , Reservation.class);
        return reservation;
    }

    @Override
    public List<Reservation> findAll() {
        List<ReservationEntity> reservationEntityList = (List<ReservationEntity>) reservationEntityRepository.findAll();
        List<Reservation> reservationList = new ArrayList<>();
        for (ReservationEntity r : reservationEntityList) {
            Reservation reservation = modelMapper.map(r, Reservation.class);
            reservationList.add(reservation);
        }
        return reservationList;
    }

    @Override
    public void delete(Reservation reservation) {
        ReservationEntity reservationEntity = modelMapper.map(reservation, ReservationEntity.class);
        reservationEntityRepository.delete(reservationEntity);
    }
}
