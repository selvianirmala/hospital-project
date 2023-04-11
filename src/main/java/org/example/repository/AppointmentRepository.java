package org.example.repository;

import org.example.model.entity.Appointment;
import org.example.model.entity.Appointment;
import org.example.model.mapper.AppointmentMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class AppointmentRepository implements IRepository<Appointment> {
    private JdbcTemplate jdbcTemplate;
    
    public AppointmentRepository(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private final String SQL_INSERT_APPOINTMENT = "insert into appointments (patient_id, schedule_id, description) values(?,?,?)";
    private final String SQL_GET_ALL = "select a.id, p.name, s.day, a.description from appointments a LEFT JOIN patients p ON p.id=a.patient_id LEFT JOIN schedule s ON s.id = a.schedule_id";
    private final String SQL_FIND_BY_ID = "select a.id, p.name, s.day, a.description from appointments a LEFT JOIN patients p ON p.id=a.patient_id LEFT JOIN schedule s ON s.id = a.schedule_id where a.id = ?";
    private final String SQL_UPDATE_APPOINTMENT = "update appointments set patient_id = ?, schedule_id =?, description=?  where id =?";
    private final String SQL_DELETE_APPOINTMENT = "delete from appointments where id =?";

    @Override
    public Appointment create(Appointment appointment) throws Exception {
        try{
            int result = jdbcTemplate.update(SQL_INSERT_APPOINTMENT,
                    appointment.getPatient().getId(),
                    appointment.getSchedule().getId(),
                    appointment.getDescription());
            if(result<0)
                throw new Exception("Failed to create medical records");
            return appointment;
        }catch (DataAccessException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Appointment> getAll() throws Exception {
        try{
            return jdbcTemplate.query(SQL_GET_ALL, new AppointmentMapper());
        }catch (DataAccessException e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Optional<Appointment> findById(int id) throws Exception {
        try{
            Appointment appointments = jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new AppointmentMapper(), new Object[]{id});
            return Optional.ofNullable(appointments);
        }catch (DataAccessException e){
            return null;
        }
    }

    @Override
    public void update(Appointment appointment, int id) throws Exception {
        try{
            jdbcTemplate.update(SQL_UPDATE_APPOINTMENT,
                                appointment.getPatient().getId(),
                                appointment.getSchedule().getId(),
                                appointment.getDescription(),
                                id);
        }catch (DataAccessException e){
            return;
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try{
            jdbcTemplate.update(SQL_DELETE_APPOINTMENT, id);
        }catch (DataAccessException e){
            throw new Exception("Failed to delete appointments");
        }
    }
    
}
