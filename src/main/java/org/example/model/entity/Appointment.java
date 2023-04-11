package org.example.model.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Appointment {
    private int id;
    private Patient patient;
    private Schedule schedule;
    private String description;

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", patient name = " + patient.getName() +
                ", schedule id = " + schedule.getId() +
                ", description='" + description + '\'' +
                '}';
    }
}
