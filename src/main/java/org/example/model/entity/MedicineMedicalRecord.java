package org.example.model.entity;

import lombok.Data;

@Data
public class MedicineMedicalRecord {
    private int id;
    private Medicine medicine;
    private MedicalRecord medicalRecord;
    private String dosage;

    @Override
    public String toString() {
        return "MedicineMedicalRecord{" +
                "id=" + id +
                ", medicine name=" + medicine.getName() +
                ", medicalRecord id=" + medicalRecord.getId() +
                ", dosage='" + dosage + '\'' +
                '}';
    }
}
