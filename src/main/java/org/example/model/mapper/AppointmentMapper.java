package org.example.model.mapper;

import org.example.model.entity.Appointment;
import org.example.model.entity.Patient;
import org.example.model.entity.Schedule;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AppointmentMapper implements RowMapper<Appointment> {
    @Override
    public Appointment mapRow(ResultSet rs, int rowNum) throws SQLException {
        Patient patient = new Patient();
        patient.setName(rs.getString("name"));
        Schedule schedule = new Schedule();
        schedule.setDay(rs.getString("day"));
        Appointment appointment = new Appointment();
        appointment.setId(rs.getInt("id"));
        appointment.setPatient(patient);
        appointment.setSchedule(schedule);
        appointment.setDescription(rs.getString("description"));

        return appointment;
    }
}
