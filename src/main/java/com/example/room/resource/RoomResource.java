package com.example.room.resource;

import com.example.room.entity.Room;
import com.example.room.services.RoomService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Map;

@Path("/rooms")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RoomResource {

    @Inject
    RoomService roomService;

    @POST
    public Response insertRoom(Room room){
        return roomService.insertRoom(room);
    }

    @GET
    @Path("/{id}")
    public Response getRoomById(@PathParam("id") int id) {
        return roomService.getRoomById(id);
    }

    @GET
    public Response getAllRooms(){
        return roomService.getAllRooms();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteRoomById(@PathParam("id") int id) {
        return roomService.deleteRoom(id);
    }


}
