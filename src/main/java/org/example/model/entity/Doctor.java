package org.example.model.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Doctor {
    private int id;
    private String name;
    private String gender;
    private LocalDate birtdate;
    private String phone;
    private String email;
    private String speciality;
}
