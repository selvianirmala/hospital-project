package org.example.repository;

import org.example.model.entity.MedicineMedicalRecord;
import org.example.model.mapper.MedicineMedicalRecordMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class MedicineMedicalRecordRepository implements IRepository<MedicineMedicalRecord> {
    private JdbcTemplate jdbcTemplate;
    
    public MedicineMedicalRecordRepository(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private final String SQL_INSERT_MEDICINEMEDICALRECORD = "insert into medicine_medical_records (medicine_id, medical_record_id, dosage) values(?,?,?)";
    private final String SQL_GET_ALL = "select mmr.id, m.name, mr.id, mmr.dosage FROM medicine_medical_records mmr " +
            "left join medicine m ON m.id = mmr.medicine_id\n" +
            "left join medical_records mr ON mr.id = mmr.medical_record_id";
    private final String SQL_FIND_BY_ID = "select mmr.id, m.name, mr.id, mmr.dosage FROM medicine_medical_records mmr " +
            "left join medicine m ON m.id = mmr.medicine_id\n" +
            "left join medical_records mr ON mr.id = mmr.medical_record_id where mmr.id = ?";
    private final String SQL_UPDATE_MEDICINEMEDICALRECORD = "update medicine_medical_records set medicine_id = ?, medical_record_id =?, dosage=?  where id =?";
    private final String SQL_DELETE_MEDICINEMEDICALRECORD = "delete from medicine_medical_records where id =?";

    @Override
    public MedicineMedicalRecord create(MedicineMedicalRecord medicineMedicalRecord) throws Exception {
        try{
            int result = jdbcTemplate.update(SQL_INSERT_MEDICINEMEDICALRECORD,
                    medicineMedicalRecord.getMedicine().getId(),
                    medicineMedicalRecord.getMedicalRecord().getId(),
                    medicineMedicalRecord.getDosage());
            if(result<0)
                throw new Exception("Failed to create medical records");
            return medicineMedicalRecord;
        }catch (DataAccessException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<MedicineMedicalRecord> getAll() throws Exception {
        try{
            return jdbcTemplate.query(SQL_GET_ALL, new MedicineMedicalRecordMapper());
        }catch (DataAccessException e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Optional<MedicineMedicalRecord> findById(int id) throws Exception {
        try{
            MedicineMedicalRecord medicineMedicalRecords = jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new MedicineMedicalRecordMapper(), new Object[]{id});
            return Optional.ofNullable(medicineMedicalRecords);
        }catch (DataAccessException e){
            return null;
        }
    }

    @Override
    public void update(MedicineMedicalRecord medicineMedicalRecord, int id) throws Exception {
        try{
            jdbcTemplate.update(SQL_UPDATE_MEDICINEMEDICALRECORD,
                    medicineMedicalRecord.getMedicine().getId(),
                    medicineMedicalRecord.getMedicalRecord().getId(),
                    medicineMedicalRecord.getDosage(),
                    id);
        }catch (DataAccessException e){
            return;
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try{
            jdbcTemplate.update(SQL_DELETE_MEDICINEMEDICALRECORD, id);
        }catch (DataAccessException e){
            throw new Exception("Failed to delete medicineMedicalRecords");
        }
    }

}
