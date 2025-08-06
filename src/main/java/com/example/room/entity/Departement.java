package com.example.room.entity;

import java.time.LocalDateTime;

public class Departement {

    private Integer departement_id;
    private String departementName;
    private LocalDateTime createdAt;


    public Integer getId() {
        return departement_id;
    }

    public void setId(Integer departement_id) {
        this.departement_id = departement_id;
    }

    public String getDepartementName() {
        return departementName;
    }

    public void setDepartementName(String departementName) {
        this.departementName = departementName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
