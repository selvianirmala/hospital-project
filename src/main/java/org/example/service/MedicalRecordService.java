package org.example.service;

import org.example.model.entity.MedicalRecord;
import org.example.repository.IRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class MedicalRecordService implements IService<MedicalRecord> {
    private IRepository<MedicalRecord> repository;

    public MedicalRecordService(IRepository<MedicalRecord> repository) {
        this.repository = repository;
    }

    @Override
    public MedicalRecord create(MedicalRecord medicalRecord) {
        try{
            LocalDate dateNow = LocalDate.now();
            medicalRecord.setDate(dateNow);
            return repository.create(medicalRecord);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<MedicalRecord> getAll() {
        try{
            return repository.getAll();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<MedicalRecord> findById(int id) {
        try{
            Optional<MedicalRecord> getMedicalRecord = repository.findById(id);
            if(getMedicalRecord == null) {
                System.out.println("Id not found");
            }
            return getMedicalRecord;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(MedicalRecord medicalRecord, int id) {
        try{
            Optional<MedicalRecord> medicalRecord1 = repository.findById(id);
            medicalRecord.setDate(medicalRecord1.get().getDate());
            if(medicalRecord1==null) {
                System.out.println("Id not found");
            }
            repository.update(medicalRecord, id);
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
