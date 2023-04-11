package org.example.model.mapper;

import org.example.model.entity.Doctor;
import org.example.model.entity.Schedule;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ScheduleMapper implements RowMapper<Schedule> {
    @Override
    public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
        Doctor doctor = new Doctor();
        doctor.setName(rs.getString("name"));
        Schedule schedule = new Schedule();
        schedule.setId(rs.getInt("id"));
        schedule.setDoctor(doctor);
        schedule.setDay(rs.getString("day"));

        return schedule;
    }
}
