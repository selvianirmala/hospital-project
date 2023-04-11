package org.example.model.entity;

import lombok.Data;

@Data
public class Schedule {
    private int id;
    private Doctor doctor;
    private String day;

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", doctor name=" + doctor.getName() +
                ", day='" + day + '\'' +
                '}';
    }
}
