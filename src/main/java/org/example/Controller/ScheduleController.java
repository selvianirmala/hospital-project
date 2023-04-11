package org.example.Controller;

import org.example.model.entity.Schedule;
import org.example.model.entity.Doctor;
import org.example.service.IService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ScheduleController {
    private IService<Schedule> service;

    public ScheduleController() {
    }

    public ScheduleController(IService<Schedule> service) {
        this.service = service;
    }

    private Scanner in = new Scanner(System.in);
    public void scheduleMenu(){
        boolean loop = true;
        while (loop){
            System.out.println("\n==================== SCHEDULE MENU ====================");
            System.out.println("1. Add schedule");
            System.out.println("2. Show schedule");
            System.out.println("3. Update schedule");
            System.out.println("4. Delete schedule");
            System.out.println("5. Back");
            System.out.print("Choose menu > ");
            int menu = in.nextInt();
            switch (menu){
                case 1 -> addSchedule();
                case 2 -> showSchedule();
                case 3 -> updateSchedule();
                case 4 -> deleteSchedule();
                case 5 -> loop = false;
                default -> System.out.println("\nInput invalid");
            }
        }
    }

    public void addSchedule(){
        System.out.println("\n---------- ADD SCHEDULE ----------");
        System.out.print("Id Doctor: ");
        int doctorId = in.nextInt();
        System.out.print("Day: ");
        in.nextLine();
        String day = in.nextLine();

        Schedule schedule = new Schedule();
        Doctor doctor = new Doctor();
        doctor.setId(doctorId);
        schedule.setDoctor(doctor);
        schedule.setDay(day);

        service.create(schedule);
        System.out.println("\nSchedule doctor with id " + doctorId + " successfully created");
    }

    public void showSchedule(){
        System.out.println("---------- SHOW SCHEDULE ----------");
        System.out.println("1. Show all");
        System.out.println("2. Show by id");
        System.out.print("Choose menu > ");
        int menu = in.nextInt();
        switch (menu){
            case 1 -> {
                List<Schedule> scheduleList = service.getAll();
                if(scheduleList.isEmpty()){
                    System.out.println("List is empty");
                    break;
                }
                scheduleList.stream().map(schedule -> schedule.getId() + "\t |\t" +
                                schedule.getDoctor().getName() + "\t |\t" +
                                schedule.getDay() )
                        .forEach(System.out::println);
            }

            case 2 -> {
                System.out.println(">>> Show By ID <<<");
                System.out.print("Input id: ");
                int id = in.nextInt();
                Optional<Schedule> schedule = service.findById(id);
                while (schedule == null) {
                    System.out.print("Input id: ");
                    id = in.nextInt();
                    schedule = service.findById(id);
                }
                System.out.println(schedule.get());
            }

            default -> System.out.println("\nInput invalid");
        }
    }

    public void updateSchedule(){
        System.out.println("---------- UPDATE SCHEDULE ----------");
        System.out.print("Input id: ");
        int id = in.nextInt();
        Optional<Schedule> schedule = service.findById(id);
        while (schedule == null) {
            System.out.print("Input id: ");
            id = in.nextInt();
            schedule = service.findById(id);
        }
        System.out.print("Id Doctor: ");
        int doctorId = in.nextInt();
        in.nextLine();
        System.out.print("Day: ");
        String day = in.nextLine();

        Schedule newSchedule = new Schedule();
        Doctor doctor = new Doctor();
        doctor.setId(doctorId);
        newSchedule.setDoctor(doctor);
        newSchedule.setDay(day);

        service.update(newSchedule, id);
        System.out.println("\nSchedule with id " + id + " successfully updated");
    }

    public void deleteSchedule(){
        System.out.println("---------- DELETE SCHEDULE ----------");
        System.out.print("Input id: ");
        int id = in.nextInt();
        Optional<Schedule> schedule = service.findById(id);
        while (schedule == null) {
            System.out.print("Input id: ");
            id = in.nextInt();
            schedule = service.findById(id);
        }
        service.delete(id);
        System.out.println("Schedule with id " + id + " successfully deleted");
    }
}
