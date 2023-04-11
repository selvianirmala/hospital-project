package org.example.model.mapper;

import org.example.model.entity.MedicalRecord;
import org.example.model.entity.Medicine;
import org.example.model.entity.MedicineMedicalRecord;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MedicineMedicalRecordMapper implements RowMapper<MedicineMedicalRecord> {
    @Override
    public MedicineMedicalRecord mapRow(ResultSet rs, int rowNum) throws SQLException {
        Medicine medicine = new Medicine();
        medicine.setName(rs.getString("name"));
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setId(rs.getInt("id"));
        MedicineMedicalRecord medicineMedicalRecord = new MedicineMedicalRecord();
        medicineMedicalRecord.setId(rs.getInt("id"));
        medicineMedicalRecord.setMedicine(medicine);
        medicineMedicalRecord.setMedicalRecord(medicalRecord);
        medicineMedicalRecord.setDosage(rs.getString("dosage"));

        return medicineMedicalRecord;
    }
}
