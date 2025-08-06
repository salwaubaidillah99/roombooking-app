package com.example.room.services;

import com.example.room.entity.Room;
import com.example.room.entity.dto.ApiResponse;
import com.example.room.repository.RoomRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class RoomService {

    @Inject
    RoomRepository roomRepository;

    private static final Logger LOGGER = Logger.getLogger(RoomService.class);


    public Response insertRoom(Room room) {
//        Map<String, Object> response = new HashMap<>();
        LOGGER.info("START insert Room: " + room);

        try {
            Map<String, Object> data = roomRepository.insertRoom(room);
            LOGGER.info("Room created successfully: " + data);

            ApiResponse<Map<String, Object>> res = new ApiResponse<>("Success Room created", 1, data);
            return Response.status(Response.Status.CREATED).entity(res).build();
        } catch (PersistenceException e) {
            LOGGER.error("Failed to create room", e);  // 👈 Log error
            ApiResponse<Void> res = new ApiResponse<>("Failed to create room", 0, null);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }

    public Response getRoomById(int id) {
        LOGGER.info("START found Room: " + id);
        try {
            Map<String, Object> data = roomRepository.getRoomById(id);
            LOGGER.info("Success Room found: " + data);

            ApiResponse<Map<String, Object>> res = new ApiResponse<>("Success Room found", 1, data);
            return Response.ok(res).build();
        } catch (PersistenceException e) {
            LOGGER.error("Failed to found room", e);
            ApiResponse<Void> res = new ApiResponse<>("Room not found", 0, null);
            return Response.status(Response.Status.NOT_FOUND).entity(res).build();
        }
    }

    public Response getAllRooms() {
        LOGGER.info("START retrieved Room: " );
        try {
            List<Map<String, Object>> data = roomRepository.getAllRooms();
            LOGGER.info("Successfully displayed all room data. " + data);
            ApiResponse<List<Map<String, Object>>> res = new ApiResponse<>("Success Rooms retrieved", 1, data);
            return Response.ok(res).build();
        } catch (PersistenceException e) {
            LOGGER.error("Failed displayed all room data", e);
            ApiResponse<Void> res = new ApiResponse<>("Room not found", 0, null);
            return Response.status(Response.Status.NOT_FOUND).entity(res).build();

        }
    }

    public Response deleteRoom(int roomId) {
        LOGGER.info("START deleted for Room id:  "+ roomId );
        try {
            boolean deleted = roomRepository.deleteRoom(roomId);
            if (deleted) {
                LOGGER.info("Room deleted Successfully "+ roomId );
                ApiResponse<Void> res = new ApiResponse<>("Room" +  roomId + "deleted successfully", 1, null);
                return Response.ok(res).build();
            } else {
                LOGGER.info("Room not found, cannot delete :{}  "+ roomId );
                ApiResponse<Void> res = new ApiResponse<>("Room" + roomId + "not found", 0, null);
                return Response.status(Response.Status.NOT_FOUND).entity(res).build();
            }
        } catch (PersistenceException e) {
            LOGGER.error("Error deleting room with ID "+   roomId, e.getMessage(), e );
            ApiResponse<Void> res = new ApiResponse<>("Error deleting room", 0, null);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }

    public Response updateRoom(Room room) {
//        Map<String, Object> response = new HashMap<>();
        LOGGER.info("START edited Room: " + room);

        try {
            Map<String, Object> data = roomRepository.updateRoom(room);
            LOGGER.info("Room created successfully: " + data);

            ApiResponse<Map<String, Object>> res = new ApiResponse<>("Success Room edited", 1, data);
            return Response.status(Response.Status.CREATED).entity(res).build();
        } catch (PersistenceException e) {
            LOGGER.error("Failed to edited room", e);
            ApiResponse<Void> res = new ApiResponse<>("Failed to edited room", 0, null);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }


}
