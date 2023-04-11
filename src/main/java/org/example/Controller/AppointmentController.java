package org.example.Controller;

import org.example.model.entity.Appointment;
import org.example.model.entity.Appointment;
import org.example.model.entity.Patient;
import org.example.model.entity.Schedule;
import org.example.service.IService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class AppointmentController {
    private IService<Appointment> service;

    public AppointmentController() {
    }

    public AppointmentController(IService<Appointment> service) {
        this.service = service;
    }

    private Scanner in = new Scanner(System.in);
    public void appointmentMenu(){
        boolean loop = true;
        while (loop){
            System.out.println("\n==================== APPOINTMENT MENU ====================");
            System.out.println("1. Add appointment");
            System.out.println("2. Show appointment");
            System.out.println("3. Update appointment");
            System.out.println("4. Delete appointment");
            System.out.println("5. Back");
            System.out.print("Choose menu > ");
            int menu = in.nextInt();
            switch (menu){
                case 1 -> addAppointment();
                case 2 -> showAppointment();
                case 3 -> updateAppointment();
                case 4 -> deleteAppointment();
                case 5 -> loop = false;
                default -> System.out.println("Input invalid");
            }
        }
    }

    public void addAppointment(){
        System.out.println("\n---------- ADD APPOINTMENT ----------");
        System.out.print("Id Patient: ");
        int patientId = in.nextInt();
        System.out.print("Id Schedule: ");
        int scheduleId = in.nextInt();
        in.nextLine();
        System.out.print("Description: ");
        String description = in.nextLine();

        Patient patient = new Patient();
        patient.setId(patientId);
        Schedule schedule = new Schedule();
        schedule.setId(scheduleId);
        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setSchedule(schedule);
        appointment.setDescription(description);

        service.create(appointment);
        System.out.println("\nAppointment patient with id " + patientId + " successfully created");
    }

    public void showAppointment(){
        System.out.println("\n---------- SHOW APPOINTMENT ----------");
        System.out.println("1. Show all");
        System.out.println("2. Show by id");
        System.out.print("Choose menu > ");
        int menu = in.nextInt();
        switch (menu){
            case 1 -> {
                List<Appointment> appointmentList = service.getAll();
                if(appointmentList.isEmpty()){
                    System.out.println("List is empty");
                    break;
                }
                appointmentList.stream().map(appointment -> appointment.getId() + "\t |" +
                                appointment.getPatient().getName() + "\t |\t" +
                                appointment.getSchedule().getDay() + "\t |\t" +
                                appointment.getDescription())
                        .forEach(System.out::println);
            }

            case 2 -> {
                System.out.println(">>> Show By ID <<<");
                System.out.print("Input id: ");
                int id = in.nextInt();
                Optional<Appointment> appointment = service.findById(id);
                while (appointment == null) {
                    System.out.print("Input id: ");
                    id = in.nextInt();
                    appointment = service.findById(id);
                }
                System.out.println(appointment.get());
            }
        }
    }

    public void updateAppointment(){
        System.out.println("\n---------- UPDATE APPOINTMENT ----------");
        System.out.print("Input id: ");
        int id = in.nextInt();
        Optional<Appointment> appointment = service.findById(id);
        while (appointment == null) {
            System.out.print("Input id: ");
            id = in.nextInt();
            appointment = service.findById(id);
        }
        System.out.print("Id Patient: ");
        int patientId = in.nextInt();
        System.out.print("Id Schedule: ");
        int scheduleId = in.nextInt();
        in.nextLine();
        System.out.print("Description: ");
        String description = in.nextLine();

        Patient patient = new Patient();
        patient.setId(patientId);
        Schedule schedule = new Schedule();
        schedule.setId(scheduleId);
        Appointment newAppointment = new Appointment();
        newAppointment.setPatient(patient);
        newAppointment.setSchedule(schedule);
        newAppointment.setDescription(description);

        service.update(newAppointment, id);
        System.out.println("\nAppointment with id " + id + " successfully updated");
    }

    public void deleteAppointment(){
        System.out.println("\n---------- DELETE APPOINTMENT ----------");
        System.out.print("Input id: ");
        int id = in.nextInt();
        Optional<Appointment> appointment = service.findById(id);
        while (appointment == null) {
            System.out.print("Input id: ");
            id = in.nextInt();
            appointment = service.findById(id);
        }
        service.delete(id);
        System.out.println("\nAppointment with id " + id + " successfully deleted");
    }
}
