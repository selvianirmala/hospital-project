package org.example.service;

import org.example.model.entity.Doctor;
import org.example.repository.IRepository;

import java.util.List;
import java.util.Optional;

public class DoctorService implements IService<Doctor> {
    private IRepository<Doctor> repository;

    public DoctorService(IRepository<Doctor> repository) {
        this.repository = repository;
    }

    @Override
    public Doctor create(Doctor doctor) {
        try{
            return repository.create(doctor);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Doctor> getAll() {
        try{
            return  repository.getAll();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Doctor> findById(int id) {
        try{
            Optional<Doctor> getDoctor = repository.findById(id);
            if(getDoctor==null) {
                System.out.println("Id not found");
            }
            return getDoctor;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Doctor doctor, int id) {
        try{
            Optional<Doctor> getDoctor = repository.findById(id);
            if(getDoctor==null) {
                System.out.println("Id not found");
            }
            repository.update(doctor, id);
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
