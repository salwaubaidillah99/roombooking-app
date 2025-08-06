package com.example.room.resource;

import com.example.room.entity.Departement;
import com.example.room.services.DepartementService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/departements")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DepartementResource {

    @Inject
    DepartementService departementService;

    @POST
    public Response insertDepartement(Departement departement){
        return departementService.insertDepartement(departement);
    }

    @GET
    @Path("/{id}")
    public Response getDepartementById(@PathParam("id") int id) {
        return departementService.getDepartementById(id);
    }

    @GET
    public Response getDepartementAll(){
        return departementService.getDepartementAll();
    }

    @DELETE
    @Path("/{id}")
    public Response deletedDepartement(@PathParam("id") int id){
        return departementService.deleteDepartement(id);
    }
}
