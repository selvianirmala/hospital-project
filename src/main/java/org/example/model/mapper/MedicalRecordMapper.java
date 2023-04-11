package org.example.model.mapper;

import org.example.model.entity.MedicalRecord;
import org.example.model.entity.Patient;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MedicalRecordMapper implements RowMapper<MedicalRecord> {
    @Override
    public MedicalRecord mapRow(ResultSet rs, int rowNum) throws SQLException {
        MedicalRecord medicalRecord = new MedicalRecord();
        Patient patient = new Patient();
        patient.setName(rs.getString("name"));
        medicalRecord.setId(rs.getInt("id"));
        medicalRecord.setPatient(patient);
        medicalRecord.setDate(rs.getDate("date").toLocalDate());
        medicalRecord.setComplaint(rs.getString("complaint"));
        medicalRecord.setDiagnosis(rs.getString("diagnosis"));
        medicalRecord.setTreatment(rs.getString("treatment"));
        return medicalRecord;
    }
}
