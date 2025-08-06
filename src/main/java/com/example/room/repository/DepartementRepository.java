package com.example.room.repository;

import com.example.room.entity.Departement;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    public Map<String, Object> getDepartementById(int id) {
        Query query = entityManager.createNativeQuery("SELECT * FROM master_booking.get_departement_by_id(?1)");
        query.setParameter(1, id);

        Object[] result = (Object[]) query.getSingleResult();

        Map<String, Object> data = new HashMap<>();
        data.put("departement_id", result[0]);
        data.put("departement_name", result[1]);

        return data;
    }

    public List<Map<String, Object>> getAllDepartement() {
        Query query = entityManager.createNativeQuery("SELECT * FROM master_booking.departement()");
        List<Object[]> results = query.getResultList();

        List<Map<String, Object>> data = new ArrayList<>();
        for (Object[] row : results) {
            Map<String, Object> departement = new HashMap<>();
            departement.put("departement_id", row[0]);
            departement.put("departement_name", row[1]);
            departement.put("created_at", row[2]);
            data.add(departement);
        }

        return data;
    }

    public boolean deleteDepartement (int id){
        Query query= entityManager.createNativeQuery("SELECT * FROM master_booking.delete_departement_by_id(?1) ");
        query.setParameter(1, id);

        return (boolean) query.getSingleResult();
    }
}
