package org.example.service;

import org.example.model.entity.Appointment;
import org.example.model.entity.Doctor;
import org.example.repository.IRepository;

import java.util.List;
import java.util.Optional;

public class AppointmentService implements IService<Appointment>{
    private IRepository<Appointment> repository;

    public AppointmentService(IRepository<Appointment> repository) {
        this.repository = repository;
    }

    @Override
    public Appointment create(Appointment Appointment) {
        try{
            return repository.create(Appointment);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Appointment> getAll() {
        try{
            return  repository.getAll();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Appointment> findById(int id) {
        try{
            Optional<Appointment> getAppointment = repository.findById(id);
            if(getAppointment==null) {
                System.out.println("Id not found");
            }
            return getAppointment;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Appointment Appointment, int id) {
        try{
            Optional<Appointment> getAppointment = repository.findById(id);
            if(getAppointment==null) {
                System.out.println("Id not found");
            }
            repository.update(Appointment, id);
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
