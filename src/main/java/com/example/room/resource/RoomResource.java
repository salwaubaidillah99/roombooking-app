package com.example.room.resource;

import com.example.room.entity.Room;
import com.example.room.services.RoomService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.Map;

@Path("/rooms")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RoomResource {

    @Inject
    RoomService roomService;

    @POST
    public Map<String, Object> insertRoom(Room room){
        return roomService.insertRoom(room);
    }

    @GET
    @Path("/{id}")
    public Map<String, Object> getRoomById(@PathParam("id") int id) {
        return roomService.getRoomById(id);
    }

    @GET
    public Map<String, Object> getAllRooms(){
        return roomService.getAllRooms();
    }

    @DELETE
    @Path("/{id}")
    public Map<String, Object> deleteRoomById(@PathParam("id") int id) {
        return roomService.deleteRoom(id);
    }


}
