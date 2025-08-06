package com.example.room.services;

import com.example.room.entity.Departement;
import com.example.room.entity.dto.ApiResponse;
import com.example.room.repository.DepartementRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;

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
}
