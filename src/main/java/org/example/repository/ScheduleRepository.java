package org.example.repository;

import org.example.model.entity.Schedule;
import org.example.model.mapper.ScheduleMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class ScheduleRepository implements IRepository<Schedule> {
    private JdbcTemplate jdbcTemplate;
    public ScheduleRepository(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private final String SQL_INSERT_SCHEDULE = "insert into schedule (id_doctor, day) values(?,?)";
    private final String SQL_GET_ALL = "select s.id, d.name, s.day from schedule s LEFT JOIN doctors d ON d.id=s.id_doctor";
    private final String SQL_FIND_BY_ID = "select s.id, d.name, s.day from schedule s LEFT JOIN doctors d ON d.id=s.id_doctor where s.id = ?";
    private final String SQL_UPDATE_SCHEDULE = "update schedule set id_doctor = ?, day =?  where id =?";
    private final String SQL_DELETE_SCHEDULE = "delete from schedule where id =?";


    @Override
    public Schedule create(Schedule schedule) throws Exception {
        try{
            int result = jdbcTemplate.update(SQL_INSERT_SCHEDULE,
                    schedule.getDoctor().getId(),
                    schedule.getDay());
            if(result<0)
                throw new Exception("Failed to create medical records");
            return schedule;
        }catch (DataAccessException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Schedule> getAll() throws Exception {
        try{
            return jdbcTemplate.query(SQL_GET_ALL, new ScheduleMapper());
        }catch (DataAccessException e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Optional<Schedule> findById(int id) throws Exception {
        try{
            Schedule schedule = jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new ScheduleMapper(), new Object[]{id});
            return Optional.ofNullable(schedule);
        }catch (DataAccessException e){
            return null;
        }
    }

    @Override
    public void update(Schedule schedule, int id) throws Exception {
        try{
            jdbcTemplate.update(SQL_UPDATE_SCHEDULE,
                    schedule.getDoctor().getId(),
                    schedule.getDay(),
                    id);
        }catch (DataAccessException e){
            return;
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try{
            jdbcTemplate.update(SQL_DELETE_SCHEDULE, id);
        }catch (DataAccessException e){
            throw new Exception("Failed to delete schedule");
        }
    }
}
