package com.example.room.resource;

import com.example.room.entity.Departement;
import com.example.room.services.DepartementService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
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
}
