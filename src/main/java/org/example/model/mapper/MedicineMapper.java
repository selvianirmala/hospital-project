package org.example.model.mapper;

import org.example.model.entity.Medicine;
import org.example.model.entity.Patient;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MedicineMapper implements RowMapper<Medicine> {

    @Override
    public Medicine mapRow(ResultSet rs, int rowNum) throws SQLException {
        Medicine medicine = new Medicine();
        medicine.setName(rs.getString("name"));
        medicine.setDescription(rs.getString("description"));
        return medicine;
    }
}
