package org.example.Controller;

import org.example.model.entity.Patient;
import org.example.service.IService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class PatientController {
    private IService<Patient> service;

    public PatientController() {
    }

    public PatientController(IService<Patient> service) {
        this.service = service;
    }

    private Scanner in = new Scanner(System.in);
    public void patientMenu(){
        boolean loop = true;
        while (loop){
            System.out.println("==================== PATIENT MENU ====================");
            System.out.println("1. Add patient");
            System.out.println("2. Show patient");
            System.out.println("3. Update patient");
            System.out.println("4. Delete patient");
            System.out.println("5. Back");
            System.out.print("Choose menu > ");
            int menu = in.nextInt();
            switch (menu){
                case 1 -> addPatient();
                case 2 -> showPatient();
                case 3 -> updatePatient();
                case 4 -> deletePatient();
                case 5 -> loop = false;
                default -> System.out.println("\nInput invalid");
            }
        }
    }

    public void addPatient(){
        System.out.println("---------- ADD PATIENT ----------");
        System.out.print("Name: ");
        in.nextLine();
        String name = in.nextLine();
        System.out.print("Gender: ");
        String gender = in.nextLine();
        System.out.print("Birthdate: ");
        String birtdate = in.nextLine();
        System.out.print("Adress: ");
        String address = in.nextLine();
        System.out.print("Phone: ");
        String phone = in.nextLine();
        System.out.print("Email: ");
        String email = in.nextLine();

        Patient patient = new Patient();
        patient.setName(name);
        patient.setGender(gender);
        patient.setBirtdate(LocalDate.parse(birtdate));
        patient.setAddress(address);
        patient.setPhone(phone);
        patient.setEmail(email);

        service.create(patient);
        System.out.println("\nPatient " + name + " successfully created");
    }

    public void showPatient(){
        System.out.println("---------- SHOW PATIENT ----------");
        System.out.println("1. Show all");
        System.out.println("2. Show by id");
        System.out.print("Choose menu > ");
        int menu = in.nextInt();
        switch (menu){
            case 1 -> {
                List<Patient> patientList = service.getAll();
                if(patientList.isEmpty()){
                    System.out.println("List is empty");
                    break;
                }
                patientList.stream().map(patient -> patient.getName() + "\t |\t" +
                                patient.getGender() + "\t |\t" +
                                patient.getBirtdate() + "\t |\t" +
                                patient.getAddress() + "\t |\t" +
                                patient.getPhone() + "\t |\t" +
                                patient.getEmail())
                        .forEach(System.out::println);
            }

            case 2 -> {
                System.out.println(">>> Show By ID <<<");
                System.out.print("Input id: ");
                int id = in.nextInt();
                Optional<Patient> patient = service.findById(id);
                while (patient == null) {
                    System.out.print("Input id: ");
                    id = in.nextInt();
                    patient = service.findById(id);
                }
                System.out.println(patient.get());
            }

            default -> System.out.println("\nInput invalid");
        }
    }

    public void updatePatient(){
        System.out.println("---------- UPDATE PATIENT ----------");
        System.out.print("Input id: ");
        int id = in.nextInt();
        Optional<Patient> patient = service.findById(id);
        while (patient == null) {
            System.out.print("Input id: ");
            id = in.nextInt();
            patient = service.findById(id);
        }
        System.out.print("Name: ");
        in.nextLine();
        String name = in.nextLine();
        System.out.print("Gender: ");
        String gender = in.nextLine();
        System.out.print("Birthdate: ");
        String birtdate = in.nextLine();
        System.out.print("Adress: ");
        String address = in.nextLine();
        System.out.print("Phone: ");
        String phone = in.nextLine();
        System.out.print("Email: ");
        String email = in.nextLine();

        Patient newPatient = new Patient();
        newPatient.setName(name);
        newPatient.setGender(gender);
        newPatient.setBirtdate(LocalDate.parse(birtdate));
        newPatient.setAddress(address);
        newPatient.setPhone(phone);
        newPatient.setEmail(email);

        service.update(newPatient, id);
        System.out.println("\nPatient with id" + id + " successfully updated");
    }

    public void deletePatient(){
        System.out.println("---------- DELETE PATIENT ----------");
        System.out.print("Input id: ");
        int id = in.nextInt();
        Optional<Patient> patient = service.findById(id);
        while (patient == null) {
            System.out.print("Input id: ");
            id = in.nextInt();
            patient = service.findById(id);
        }
        service.delete(id);
        System.out.println("Patient with id " + id + " successfully deleted");
    }

}
