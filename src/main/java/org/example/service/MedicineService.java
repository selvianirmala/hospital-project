package org.example.service;

import org.example.model.entity.Medicine;
import org.example.repository.IRepository;

import java.util.List;
import java.util.Optional;

public class MedicineService implements IService<Medicine> {
    private IRepository<Medicine> repository;

    public MedicineService(IRepository<Medicine> repository) {
        this.repository = repository;
    }

    @Override
    public Medicine create(Medicine medicine) {
        try{
            return repository.create(medicine);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Medicine> getAll() {
        try{
            return  repository.getAll();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Medicine> findById(int id) {
        try{
            Optional<Medicine> getMedicine = repository.findById(id);
            if(getMedicine == null) {
                System.out.println("Id not found");
            }
            return getMedicine;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Medicine medicine, int id) {
        try{
            Optional<Medicine> getMedicine = repository.findById(id);
            if(getMedicine == null) {
                System.out.println("Id not found");
            }
            repository.update(medicine, id);
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
