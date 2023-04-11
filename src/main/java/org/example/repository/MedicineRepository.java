package org.example.repository;

import org.example.model.entity.Medicine;
import org.example.model.mapper.MedicineMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class MedicineRepository implements IRepository<Medicine> {
    private JdbcTemplate jdbcTemplate;

    public MedicineRepository(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);        
    }

    private final String SQL_INSERT_MEDICINE = "insert into medicine (name, description) values(?,?)";
    private final String SQL_GET_ALL = "select * from medicine";
    private final String SQL_FIND_BY_ID = "select * from medicine where id = ?";
    private final String SQL_UPDATE_MEDICINE = "update medicine set name = ?, description =? where id =?";
    private final String SQL_DELETE_MEDICINE = "delete from medicine where id =?";

    @Override
    public Medicine create(Medicine medicine) throws Exception {
        try{
            int result = jdbcTemplate.update(SQL_INSERT_MEDICINE,
                    medicine.getName(),
                    medicine.getDescription());
            if(result<0)
                throw new Exception("Failed to create medicine");
            return medicine;
        }catch (DataAccessException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Medicine> getAll() throws Exception {
        try{
            return jdbcTemplate.query(SQL_GET_ALL, new MedicineMapper());
        }catch (DataAccessException e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Optional<Medicine> findById(int id) throws Exception {
        try{
            Medicine medicine = jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new MedicineMapper(), new Object[]{id});
            return Optional.ofNullable(medicine);
        }catch (DataAccessException e){
            return null;
        }
    }

    @Override
    public void update(Medicine medicine, int id) throws Exception {
        try{
            jdbcTemplate.update(SQL_UPDATE_MEDICINE,
                    medicine.getName(),
                    medicine.getDescription(),
                    id);
        }catch (DataAccessException e){
            return;
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try{
            jdbcTemplate.update(SQL_DELETE_MEDICINE, id);
        }catch (DataAccessException e){
            throw new Exception("Failed to delete medicine");
        }
    }
}
