package org.example.repository;

import org.example.model.entity.Patient;
import org.example.model.mapper.PatientMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class PatientRepository implements IRepository<Patient> {
    private JdbcTemplate jdbcTemplate;

    public PatientRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private final String SQL_INSERT_PATIENT = "insert into patients (name, gender, birthdate, address, phone, email) values(?,?,?,?,?,?)";
    private final String SQL_GET_ALL = "select * from patients";
    private final String SQL_FIND_BY_ID = "select * from patients where id = ?";
    private final String SQL_UPDATE_PATIENT = "update patients set name = ?, gender =?, birthdate=?, adrress=?, phone=?, email=?  where id =?";
    private final String SQL_DELETE_PATIENT = "delete from patients where id =?";

    @Override
    public Patient create(Patient patient) throws Exception {
        try{
            int result = jdbcTemplate.update(SQL_INSERT_PATIENT,
                    patient.getName(),
                    patient.getGender(),
                    patient.getBirtdate(),
                    patient.getAddress(),
                    patient.getPhone(),
                    patient.getEmail());
            if(result<0)
                throw new Exception("Failed to create patient");
            return patient;
        }catch (DataAccessException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Patient> getAll() throws Exception {
        try{
            return jdbcTemplate.query(SQL_GET_ALL, new PatientMapper());
        }catch (DataAccessException e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Optional<Patient> findById(int id) throws Exception {
        try{
            Patient patient = jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new PatientMapper(), new Object[]{id});
            return Optional.ofNullable(patient);
        }catch (DataAccessException e){
            return null;
        }
    }

    @Override
    public void update(Patient patient, int id) throws Exception {
        try{
            jdbcTemplate.update(SQL_UPDATE_PATIENT,
                    patient.getName(),
                    patient.getGender(),
                    patient.getBirtdate(),
                    patient.getAddress(),
                    patient.getPhone(),
                    patient.getEmail(),
                    id);
        }catch (DataAccessException e){
            return;
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try{
            jdbcTemplate.update(SQL_DELETE_PATIENT, id);
        }catch (DataAccessException e){
            throw new Exception("Failed to delete doctor");
        }
    }
}
