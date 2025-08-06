package com.example.room.services;

import com.example.room.entity.Departement;
import com.example.room.entity.dto.ApiResponse;
import com.example.room.repository.DepartementRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;

import java.util.List;
import java.util.Map;

@ApplicationScoped
public class DepartementService {

    @Inject
    DepartementRepository departementRepository;

    private static final Logger LOGGER = Logger.getLogger(DepartementService.class);

    public Response insertDepartement (Departement d){
        LOGGER.info("START insert Departement" + d);

        try {
            Map<String, Object> data = departementRepository.insertDepartement(d);
            LOGGER.info("Departement Created Successfully:" + data);

            ApiResponse<Map<String, Object>> res = new ApiResponse<>("Success Room Created", 1, data);
            return Response.status(Response.Status.CREATED).entity(res).build();
        } catch (PersistenceException e){
            LOGGER.error("Failed to Created Departement", e);
            ApiResponse<Void> res = new ApiResponse<>("Failed to Created Departement", 0, null);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }

    }

    public Response getDepartementById(int id) {
        LOGGER.info("START found Departement: " + id);
        try {
            Map<String, Object> data = departementRepository.getDepartementById(id);
            LOGGER.info("Success Departement found: " + data);

            ApiResponse<Map<String, Object>> res = new ApiResponse<>("Success Departement found", 1, data);
            return Response.ok(res).build();
        } catch (PersistenceException e) {
            LOGGER.error("Failed to found Departement", e);
            ApiResponse<Void> res = new ApiResponse<>("Departement not found", 0, null);
            return Response.status(Response.Status.NOT_FOUND).entity(res).build();
        }
    }

    public Response getDepartementAll(){
        LOGGER.info("START retrieved Departement: ");
        try {
            List<Map<String, Object>> data = departementRepository.getAllDepartement();
            LOGGER.info("Success displayed all Departement data" +data);

            ApiResponse<List<Map<String, Object>>> res = new ApiResponse<>("Success Departement retrieved", 1, data);
            return  Response.ok(res).build();
        } catch (PersistenceException e){
            LOGGER.error("Failed to retrieved Departement", e);
            ApiResponse<Void> res = new ApiResponse<>("Departement not found", 0, null);
            return  Response.status(Response.Status.NOT_FOUND).entity(res).build();
        }
    }

    public Response deleteDepartement(int id){
        LOGGER.info("Start Found departement " +id);
        try {
            boolean deleted = departementRepository.deleteDepartement(id);
            if (deleted){
                LOGGER.info("Departement deleted successfully" +id);
                ApiResponse<Void> res = new ApiResponse<>("Departement "+ id + "deleted successfully", 1, null);
                return Response.ok(res).build();
            } else {
                LOGGER.info("Departement not found , cannot deleted : " + id);
                ApiResponse<Void> res = new ApiResponse<>("Departement " + id + "not found", 0, null);
                return Response.status(Response.Status.NOT_FOUND).entity(res).build();
            }
        } catch (PersistenceException e){
            LOGGER.error("Error deleteing departement with ID : " + id , e.getMessage(),e);
            ApiResponse<Void> res = new ApiResponse<>("Eror deleting room", 0, null);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }
}
