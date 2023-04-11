package org.example.repository;

import org.example.model.entity.Doctor;
import org.example.model.mapper.DoctorMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class DoctorRepository implements IRepository<Doctor> {

    private JdbcTemplate jdbcTemplate;

    public DoctorRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private final String SQL_INSERT_DOCTOR = "insert into doctors (name, gender, birthdate, phone, email, speciality) values(?,?,?,?,?,?)";
    private final String SQL_GET_ALL = "select * from doctors";
    private final String SQL_FIND_BY_ID = "select * from doctors where id = ?";
    private final String SQL_UPDATE_DOCTOR = "update doctors set name = ?, gender =?, birthdate=?, phone=?, email=?, speciality=?  where id =?";
    private final String SQL_DELETE_DOCTOR = "delete from doctors where id =?";

    @Override
    public Doctor create(Doctor doctor) throws Exception {
        try{
            int result = jdbcTemplate.update(SQL_INSERT_DOCTOR,
                                                doctor.getName(),
                                                doctor.getGender(),
                                                doctor.getBirtdate(),
                                                doctor.getPhone(),
                                                doctor.getEmail(),
                                                doctor.getSpeciality());
            if(result<0)
                throw new Exception("Failed to create doctor");
            return doctor;
        }catch (DataAccessException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Doctor> getAll() throws Exception {
        try{
            return jdbcTemplate.query(SQL_GET_ALL, new DoctorMapper());
        }catch (DataAccessException e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Optional<Doctor> findById(int id) throws Exception {
        try{
            Doctor doctor = jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new DoctorMapper(), new Object[]{id});
            return Optional.ofNullable(doctor);
        }catch (DataAccessException e){
            return null;
        }
    }

    @Override
    public void update(Doctor doctor, int id) throws Exception {
        try{
            jdbcTemplate.update(SQL_UPDATE_DOCTOR,
                                    doctor.getName(),
                                    doctor.getGender(),
                                    doctor.getBirtdate(),
                                    doctor.getPhone(),
                                    doctor.getEmail(),
                                    doctor.getSpeciality(),
                                    id);
        }catch (DataAccessException e){
            return;
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try{
            jdbcTemplate.update(SQL_DELETE_DOCTOR, id);
        }catch (DataAccessException e){
            throw new Exception("Failed to delete doctor");
        }
    }
}
