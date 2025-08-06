package com.example.room.repository;

import com.example.room.entity.Departement;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class DepartementRepository {

    @Inject
    EntityManager entityManager;

    public Map<String, Object> insertDepartement (Departement departement){
        Query query = entityManager.createNativeQuery("SELECT * FROM master_booking.create_departement(?1)");
        query.setParameter(1, departement.getDepartementName());

        Object[] result = (Object[]) query.getSingleResult();

        Map<String, Object> data = new HashMap<>();
        data.put("departement_id", result[0]);
        data.put("departement_name", result[1]);
        data.put("created_at", result[2]);


        return data;

    }
}
