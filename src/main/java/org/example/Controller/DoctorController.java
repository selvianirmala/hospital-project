package org.example.Controller;

import org.example.model.entity.Doctor;
import org.example.service.IService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class DoctorController {
    private IService<Doctor> service;

    public DoctorController() {
    }

    public DoctorController(IService<Doctor> service) {
        this.service = service;
    }

    private Scanner in = new Scanner(System.in);
    public void doctorMenu(){
        boolean loop = true;
        while (loop){
            System.out.println("\n==================== DOCTOR MENU ====================");
            System.out.println("1. Add doctor");
            System.out.println("2. Show doctor");
            System.out.println("3. Update doctor");
            System.out.println("4. Delete doctor");
            System.out.println("5. Back");
            System.out.print("Choose menu > ");
            int menu = in.nextInt();
            switch (menu){
                case 1 -> addDoctor();
                case 2 -> showDoctor();
                case 3 -> updateDoctor();
                case 4 -> deleteDoctor();
                case 5 -> loop = false;
                default -> System.out.println("\nInput invalid");
            }
        }
    }

    public void addDoctor(){
        System.out.println("\n---------- ADD DOCTOR ----------");
        System.out.print("Name: ");
        in.nextLine();
        String name = in.nextLine();
        System.out.print("Gender: ");
        String gender = in.nextLine();
        System.out.print("Birthdate: ");
        String birtdate = in.nextLine();
        System.out.print("Phone: ");
        String phone = in.nextLine();
        System.out.print("Email: ");
        String email = in.nextLine();
        System.out.print("Speciality: ");
        String speciality = in.nextLine();

        Doctor doctor = new Doctor();
        doctor.setName(name);
        doctor.setGender(gender);
        doctor.setBirtdate(LocalDate.parse(birtdate));
        doctor.setPhone(phone);
        doctor.setEmail(email);
        doctor.setSpeciality(speciality);

        service.create(doctor);
        System.out.println("\nDoctor " + name + " successfully created");
    }

    public void showDoctor(){
        System.out.println("\n---------- SHOW DOCTOR ----------");
        System.out.println("1. Show all");
        System.out.println("2. Show by id");
        System.out.print("Choose menu > ");
        int menu = in.nextInt();
        switch (menu){
            case 1 -> {
                List<Doctor> doctorList = service.getAll();
                if(doctorList.isEmpty()){
                    System.out.println("List is empty");
                    break;
                }
                doctorList.stream().map(doctor -> doctor.getName() + "\t |\t" +
                                doctor.getGender() + "\t |\t" +
                                doctor.getBirtdate() + "\t |\t" +
                                doctor.getPhone() + "\t |\t" +
                                doctor.getEmail() + "\t |\t" +
                                doctor.getSpeciality())
                        .forEach(System.out::println);
            }

            case 2 -> {
                System.out.println(">>> Show By ID <<<");
                System.out.print("Input id: ");
                int id = in.nextInt();
                Optional<Doctor> doctor = service.findById(id);
                while (doctor == null) {
                    System.out.print("Input id: ");
                    id = in.nextInt();
                    doctor = service.findById(id);
                }
                System.out.println(doctor.get());
            }

            default -> System.out.println("\nInput invalid");
        }
    }

    public void updateDoctor(){
        System.out.println("\n---------- UPDATE DOCTOR ----------");
        System.out.print("Input id: ");
        int id = in.nextInt();
        Optional<Doctor> doctor = service.findById(id);
        while (doctor == null) {
            System.out.print("Input id: ");
            id = in.nextInt();
            doctor = service.findById(id);
        }
        System.out.print("Name: ");
        in.nextLine();
        String name = in.nextLine();
        System.out.print("Gender: ");
        String gender = in.nextLine();
        System.out.print("Birthdate: ");
        String birtdate = in.nextLine();
        System.out.print("Phone: ");
        String phone = in.nextLine();
        System.out.print("Email: ");
        String email = in.nextLine();
        System.out.print("Speciality: ");
        String speciality = in.nextLine();

        Doctor newDoctor = new Doctor();
        newDoctor.setName(name);
        newDoctor.setGender(gender);
        newDoctor.setBirtdate(LocalDate.parse(birtdate));
        newDoctor.setPhone(phone);
        newDoctor.setEmail(email);
        newDoctor.setSpeciality(speciality);

        service.update(newDoctor, id);
        System.out.println("\nDoctor with id " + id + " successfully updated");
    }

    public void deleteDoctor(){
        System.out.println("\n---------- DELETE DOCTOR ----------");
        System.out.print("Input id: ");
        int id = in.nextInt();
        Optional<Doctor> doctor = service.findById(id);
        while (doctor == null) {
            System.out.print("Input id: ");
            id = in.nextInt();
            doctor = service.findById(id);
        }
        service.delete(id);
        System.out.println("\nDoctor with id " + id + " successfully deleted");
    }
}
