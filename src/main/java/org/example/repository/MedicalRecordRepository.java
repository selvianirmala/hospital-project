package org.example.repository;

import org.example.model.entity.MedicalRecord;
import org.example.model.mapper.MedicalRecordMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class MedicalRecordRepository implements IRepository<MedicalRecord> {
    private JdbcTemplate jdbcTemplate;
    
    public MedicalRecordRepository(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private final String SQL_INSERT_MEDICALRECORD = "insert into medical_records (patient_id, date, complaint, diagnosis, treatment) values(?,?,?,?,?)";
    private final String SQL_GET_ALL = "select m.id, p.name, m.date, m.complaint, m.diagnosis, m.treatment from medical_records m LEFT JOIN patients p ON p.id=m.patient_id";
    private final String SQL_FIND_BY_ID = "select m.id, p.name, m.date, m.complaint, m.diagnosis, m.treatment from medical_records m LEFT JOIN patients p ON p.id=m.patient_id where m.id = ?";
    private final String SQL_UPDATE_MEDICALRECORD = "update medical_records set patient_id = ?, date =?, complaint=?, diagnosis=?, email=?, treatment=?  where id =?";
    private final String SQL_DELETE_MEDICALRECORD = "delete from medical_records where id =?";


    @Override
    public MedicalRecord create(MedicalRecord medicalRecords) throws Exception {
        try{
            int result = jdbcTemplate.update(SQL_INSERT_MEDICALRECORD,
                    medicalRecords.getPatient().getId(),
                    medicalRecords.getDate(),
                    medicalRecords.getComplaint(),
                    medicalRecords.getDiagnosis(),
                    medicalRecords.getTreatment());
            if(result<0)
                throw new Exception("Failed to create medical records");
            return medicalRecords;
        }catch (DataAccessException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<MedicalRecord> getAll() throws Exception {
        try{
            return jdbcTemplate.query(SQL_GET_ALL, new MedicalRecordMapper());
        }catch (DataAccessException e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Optional<MedicalRecord> findById(int id) throws Exception {
        try{
            MedicalRecord medicalRecords = jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new MedicalRecordMapper(), new Object[]{id});
            return Optional.ofNullable(medicalRecords);
        }catch (DataAccessException e){
            return null;
        }
    }

    @Override
    public void update(MedicalRecord medicalRecords, int id) throws Exception {
        try{
            jdbcTemplate.update(SQL_UPDATE_MEDICALRECORD,
                    medicalRecords.getPatient().getId(),
                    medicalRecords.getDate(),
                    medicalRecords.getComplaint(),
                    medicalRecords.getDiagnosis(),
                    medicalRecords.getTreatment(),
                    id);
        }catch (DataAccessException e){
            return;
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try{
            jdbcTemplate.update(SQL_DELETE_MEDICALRECORD, id);
        }catch (DataAccessException e){
            throw new Exception("Failed to delete medicalRecords");
        }
    }
}
