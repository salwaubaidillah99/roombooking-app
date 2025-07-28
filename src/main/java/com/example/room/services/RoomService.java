package com.example.room.services;

import com.example.room.entity.Room;
import com.example.room.repository.RoomRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class RoomService {

    @Inject
    RoomRepository roomRepository;

    public Map<String, Object> insertRoom(Room room) {
        Map<String, Object> data = roomRepository.insertRoom(room);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Room created");
        response.put("out", 1);
        response.put("data", data);

        return response;
    }

    public Map<String, Object> getRoomById(int id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Map<String, Object> data = roomRepository.getRoomById(id);

            response.put("message", "Room found");
            response.put("out", 1);
            response.put("data", data);
        } catch (PersistenceException e) {
            response.put("message", "Room not found");
            response.put("data", null);
            response.put("out", 0);
        }

        return response;
    }

    public Map<String, Object> getAllRooms() {
        Map<String, Object> response = new HashMap<>();

        try {
            List<Map<String, Object>> data = roomRepository.getAllRooms();

            response.put("message", "Room retrieved");
            response.put("out", 1);
            response.put("data", data);
        } catch (PersistenceException e) {
            response.put("message", "Room not found");
            response.put("data", null);
            response.put("out", 0);
        }

        return response;
    }

    public Map<String, Object> deleteRoom(int roomId) {
        Map<String, Object> response = new HashMap<>();

        try {
            boolean deleted = roomRepository.deleteRoom(roomId);

            if (deleted) {
                response.put("message", "Room deleted successfully");
                response.put("out", 1);
            } else {
                response.put("message", "Room not found");
                response.put("out", 0);
            }
//            response.put("data", null);
        } catch (PersistenceException e) {
            response.put("message", "Error deleting room");
//            response.put("data", null);
            response.put("out", 0);
        }

        return response;
    }




}