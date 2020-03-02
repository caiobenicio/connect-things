package br.com.thing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.thing.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {


}
