package com.example.room.repository;

import com.example.room.entity.Room;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class RoomRepository {

    @Inject
    EntityManager entityManager;

    public Map<String, Object> insertRoom (Room room){
        Query query = entityManager.createNativeQuery("SELECT * FROM master_booking.create_room(?1, ?2, ?3)");
        query.setParameter(1, room.getRoomName());
        query.setParameter(2, room.getLocation());
        query.setParameter(3, room.getCapacity());

        Object[] result = (Object[]) query.getSingleResult();

        Map<String, Object> data = new HashMap<>();
        data.put("room_id", result[0]);
        data.put("room_name", result[1]);
        data.put("location", result[2]);
        data.put("capacity", result[3]);
        data.put("status", result[4]);
        data.put("created_at", result[5]);

        return data;
    }

    public Map<String, Object> getRoomById(int id) {
        Query query = entityManager.createNativeQuery("SELECT * FROM master_booking.get_room_by_id(?1)");
        query.setParameter(1, id);

        Object[] result = (Object[]) query.getSingleResult();

        Map<String, Object> data = new HashMap<>();
        data.put("room_id", result[0]);
        data.put("room_name", result[1]);
        data.put("location", result[2]);
        data.put("capacity", result[3]);
        data.put("status", result[4]);
        data.put("created_at", result[5]);

        return data;
    }
    public List<Map<String, Object>> getAllRooms() {
        Query query = entityManager.createNativeQuery("SELECT * FROM master_booking.get_room()");
        List<Object[]> results = query.getResultList();

        List<Map<String, Object>> roomList = new ArrayList<>();
        for (Object[] row : results) {
            Map<String, Object> room = new HashMap<>();
            room.put("room_id", row[0]);
            room.put("room_name", row[1]);
            room.put("location", row[2]);
            room.put("capacity", row[3]);
            room.put("status", row[4]);
            room.put("created_at", row[5]);
            roomList.add(room);
        }

        return roomList;
    }
    public boolean deleteRoom(int roomId) {
        Query query = entityManager.createNativeQuery("SELECT * FROM master_booking.delete_room_by_id(?1)");
        query.setParameter(1, roomId);

        return (Boolean) query.getSingleResult();
    }


}