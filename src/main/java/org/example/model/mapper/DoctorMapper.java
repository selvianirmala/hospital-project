package org.example.model.mapper;

import org.example.model.entity.Doctor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DoctorMapper implements RowMapper<Doctor> {
    @Override
    public Doctor mapRow(ResultSet rs, int rowNum) throws SQLException {
        Doctor doctor = new Doctor();
        doctor.setId(rs.getInt("id"));
        doctor.setName(rs.getString("name"));
        doctor.setGender(rs.getString("gender"));
        doctor.setBirtdate(rs.getDate("birthdate").toLocalDate());
        doctor.setPhone(rs.getString("phone"));
        doctor.setEmail(rs.getString("email"));
        doctor.setSpeciality(rs.getString("speciality"));
        return doctor;
    }
}

