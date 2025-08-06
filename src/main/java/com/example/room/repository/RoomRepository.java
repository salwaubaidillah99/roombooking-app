package com.example.room.repository;

import com.example.room.entity.Room;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;

import java.util.*;

@ApplicationScoped
public class RoomRepository {

    @Inject
    EntityManager entityManager;

    public Map<String, Object> insertRoom(Room room) {
        Query query = entityManager.createNativeQuery(
                "SELECT * FROM master_booking.create_room(?1, ?2, ?3, ?4, ?5)"
        );
        query.setParameter(1, room.getRoomName());
        query.setParameter(2, room.getLocation());
        query.setParameter(3, room.getCapacity());
        query.setParameter(4, room.getStatus());
        query.setParameter(5, room.getFasilities());

        try {
            List<Object[]> resultList = query.getResultList();
            if (resultList.isEmpty()) {
                throw new NoResultException("No room created");
            }

            Object[] result = resultList.get(0);  // ambil row pertama
            Map<String, Object> data = new HashMap<>();
            data.put("room_id", result[0]);
            data.put("room_name", result[1]);
            data.put("location", result[2]);
            data.put("capacity", result[3]);
            data.put("status", result[4]);
            data.put("created_at", result[5]);
            data.put("updated_at", result[6]);
            data.put("fasilities", result[7]);
            return data;
        } catch (NoResultException e) {
            return Collections.emptyMap();
        }
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
        data.put("fasilities", result[4]);
        data.put("status", result[5]);
//        data.put("created_at", result[6]);

        return data;
    }

    public List<Map<String, Object>> getAllRooms() {
        Query query = entityManager.createNativeQuery("SELECT * FROM master_booking.get_room()");
        List<Object[]> results = query.getResultList();

        List<Map<String, Object>> data = new ArrayList<>();
        for (Object[] row : results) {
            Map<String, Object> room = new HashMap<>();
            room.put("room_id", row[0]);
            room.put("room_name", row[1]);
            room.put("location", row[2]);
            room.put("capacity", row[3]);
            room.put("fasilities", row[4]);
            room.put("status", row[5]);
            room.put("created_at", row[6]);
            data.add(room);
        }

        return data;
    }

    public boolean deleteRoom(int roomId) {
        Query query = entityManager.createNativeQuery("SELECT * FROM master_booking.delete_room_by_id(?1)");
        query.setParameter(1, roomId);

        return (Boolean) query.getSingleResult();
    }

    public Map<String, Object> updateRoom(Room room) {
        Query query = entityManager.createNativeQuery("SELECT * FROM master_booking.update_room(?1, ?2, ?3, ?4, ?5, ?6)");
        query.setParameter(1, room.getRoom_id());
        query.setParameter(2, room.getRoomName());
        query.setParameter(3, room.getLocation());
        query.setParameter(4, room.getCapacity());
        query.setParameter(5, room.getStatus());
        query.setParameter(6, room.getFasilities());

        try {
            List<Object[]> results = query.getResultList();
            if (results.isEmpty()) {
                return Collections.emptyMap();
            }

            Object[] result = results.get(0);
            Map<String, Object> data = new HashMap<>();
            data.put("room_id", result[0]);
            data.put("room_name", result[1]);
            data.put("location", result[2]);
            data.put("capacity", result[3]);
            data.put("status", result[4]);
            data.put("updated_at", result[5]);
            data.put("fasilities", result[6]);
            return data;
        } catch (NoResultException e) {
            return Collections.emptyMap();
        }
    }


}