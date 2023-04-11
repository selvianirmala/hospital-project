package org.example.model.mapper;

import org.example.model.entity.Patient;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class PatientMapper implements RowMapper<Patient>{
    @Override
    public Patient mapRow(ResultSet rs, int rowNum) throws SQLException {
        Patient patient = new Patient();
        patient.setName(rs.getString("name"));
        patient.setGender(rs.getString("gender"));
        patient.setBirtdate(rs.getDate("birthdate").toLocalDate());
        patient.setPhone(rs.getString("phone"));
        patient.setAddress(rs.getString("address"));
        patient.setEmail(rs.getString("email"));
        return patient;
    }
}
