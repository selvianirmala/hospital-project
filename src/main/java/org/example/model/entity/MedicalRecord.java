package org.example.model.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MedicalRecord {
    private int id;
    private Patient patient;
    private LocalDate date;
    private String complaint;
    private String diagnosis;
    private String treatment;

    @Override
    public String toString() {
        return "MedicalRecord{" +
                "id=" + id +
                ", patient name = " + patient.getName() +
                ", date=" + date +
                ", complaint='" + complaint + '\'' +
                ", diagnosis='" + diagnosis + '\'' +
                ", treatment='" + treatment + '\'' +
                '}';
    }
}
