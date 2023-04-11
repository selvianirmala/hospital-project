package org.example.service;

import org.example.model.entity.Patient;
import org.example.repository.IRepository;

import java.util.List;
import java.util.Optional;

public class PatientService implements IService<Patient> {
    private IRepository<Patient> repository;

    public PatientService(IRepository<Patient> repository) {
        this.repository = repository;
    }

    @Override
    public Patient create(Patient patient) {
        try{
            return repository.create(patient);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Patient> getAll() {
        try{
            return  repository.getAll();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Patient> findById(int id) {
        try{
            Optional<Patient> getPatient = repository.findById(id);
            if(getPatient.isEmpty()) {
                throw new Exception("Id not found");
            }
            return getPatient;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Patient patient, int id) {
        try{
            Optional<Patient> getPatient = repository.findById(id);
            if(getPatient==null) {
                System.out.println("Id not found");
            }
            repository.update(patient, id);
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
