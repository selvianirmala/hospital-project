package org.example.service;

import org.example.model.entity.Doctor;
import org.example.model.entity.MedicineMedicalRecord;
import org.example.repository.IRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class MedicineMedicalRecordService implements IService<MedicineMedicalRecord> {
    private IRepository<MedicineMedicalRecord> repository;

    public MedicineMedicalRecordService(IRepository<MedicineMedicalRecord> repository) {
        this.repository = repository;
    }

    @Override
    public MedicineMedicalRecord create(MedicineMedicalRecord medicineMedicalRecord) {
        try{
            return repository.create(medicineMedicalRecord);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<MedicineMedicalRecord> getAll() {
        try{
            return  repository.getAll();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<MedicineMedicalRecord> findById(int id) {
        try{
            Optional<MedicineMedicalRecord> getMedicineMedicalRecord = repository.findById(id);
            if(getMedicineMedicalRecord==null) {
                System.out.println("Id not found");
            }
            return getMedicineMedicalRecord;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(MedicineMedicalRecord medicineMedicalRecord, int id) {
        try{
            Optional<MedicineMedicalRecord> getMedicineMedicalRecord = repository.findById(id);
            if(getMedicineMedicalRecord==null) {
                System.out.println("Id not found");
            }
            repository.update(medicineMedicalRecord, id);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try{
            repository.delete(id);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
