package org.example.service;

import org.example.model.entity.Doctor;
import org.example.model.entity.Schedule;
import org.example.repository.IRepository;

import java.util.List;
import java.util.Optional;

public class ScheduleService implements IService<Schedule> {
    private IRepository<Schedule> repository;

    public ScheduleService(IRepository<Schedule> repository) {
        this.repository = repository;
    }

    @Override
    public Schedule create(Schedule schedule) {
        try{
            return repository.create(schedule);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Schedule> getAll() {
        try{
            return  repository.getAll();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Schedule> findById(int id) {
        try{
            Optional<Schedule> getSchedule = repository.findById(id);
            if(getSchedule==null) {
                System.out.println("Id not found");
            }
            return getSchedule;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Schedule schedule, int id) {
        try{
            Optional<Schedule> getSchedule = repository.findById(id);
            if(getSchedule==null) {
                System.out.println("Id not found");
            }
            repository.update(schedule, id);
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


