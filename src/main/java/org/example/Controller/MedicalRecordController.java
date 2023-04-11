package org.example.Controller;

import org.example.model.entity.MedicalRecord;
import org.example.model.entity.Patient;
import org.example.service.IService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class MedicalRecordController {
    private IService<MedicalRecord> service;

    public MedicalRecordController() {
    }

    public MedicalRecordController(IService<MedicalRecord> service) {
        this.service = service;
    }

    private Scanner in = new Scanner(System.in);
    public void medicalRecordMenu(){
        boolean loop = true;
        while (loop){
            System.out.println("\n==================== MEDICAL RECORD MENU ====================");
            System.out.println("1. Add medical record");
            System.out.println("2. Show medical record");
            System.out.println("3. Update medical record");
            System.out.println("4. Delete medical record");
            System.out.println("5. Back");
            System.out.print("Choose menu > ");
            int menu = in.nextInt();
            switch (menu){
                case 1 -> addMedicalRecord();
                case 2 -> showMedicalRecord();
                case 3 -> updateMedicalRecord();
                case 4 -> deleteMedicalRecord();
                case 5 -> loop = false;
                default -> System.out.println("Input invalid");
            }
        }
    }

    public void addMedicalRecord(){
        System.out.println("\n---------- ADD MEDICAL RECORD ----------");
        System.out.print("Id Patient: ");
        int patientId = in.nextInt();
        System.out.print("Complaint: ");
        in.nextLine();
        String complaint = in.nextLine();
        System.out.print("Diagnosis: ");
        String diagnosis = in.nextLine();
        System.out.print("Treatment: ");
        String treatment = in.nextLine();

        MedicalRecord medicalRecord = new MedicalRecord();
        Patient patient = new Patient();
        patient.setId(patientId);
        medicalRecord.setPatient(patient);
        medicalRecord.setComplaint(complaint);
        medicalRecord.setDiagnosis(diagnosis);
        medicalRecord.setTreatment(treatment);

        service.create(medicalRecord);
        System.out.println("\nMedical record patient with id " + patientId + " successfully created");
    }

    public void showMedicalRecord(){
        System.out.println("\n---------- SHOW MEDICAL RECORD ----------");
        System.out.println("1. Show all");
        System.out.println("2. Show by id");
        System.out.print("Choose menu > ");
        int menu = in.nextInt();
        switch (menu){
            case 1 -> {
                List<MedicalRecord> medicalRecordList = service.getAll();
                if(medicalRecordList.isEmpty()){
                    System.out.println("List is empty");
                    break;
                }
                medicalRecordList.stream().map(medicalRecord -> medicalRecord.getId() + "\t |" +
                                medicalRecord.getPatient().getName() + "\t |\t" +
                                medicalRecord.getDate() + "\t |\t" +
                                medicalRecord.getComplaint() + "\t |\t" +
                                medicalRecord.getDiagnosis() + "\t |\t" +
                                medicalRecord.getTreatment())
                        .forEach(System.out::println);
            }

            case 2 -> {
                System.out.println("\n>>> Show By ID <<<");
                System.out.print("Input id: ");
                int id = in.nextInt();
                Optional<MedicalRecord> medicalRecord = service.findById(id);
                while (medicalRecord == null) {
                    System.out.print("Input id: ");
                    id = in.nextInt();
                    medicalRecord = service.findById(id);
                }
                System.out.println(medicalRecord.get());
            }

            default -> System.out.println("\nInput invalid");
        }
    }

    public void updateMedicalRecord(){
        System.out.println("\n---------- UPDATE MEDICAL RECORD ----------");
        System.out.print("Input id: ");
        int id = in.nextInt();
        Optional<MedicalRecord> medicalRecord = service.findById(id);
        while (medicalRecord == null) {
            System.out.print("Input id: ");
            id = in.nextInt();
            medicalRecord = service.findById(id);
        }
        System.out.print("Id Patient: ");
        int patientId = in.nextInt();
        in.nextLine();
        System.out.print("Complaint");
        String complaint = in.nextLine();
        System.out.print("Diagnosis: ");
        String diagnosis = in.nextLine();
        System.out.print("Treatment: ");
        String treatment = in.nextLine();

        MedicalRecord newMedicalRecord = new MedicalRecord();
        Patient patient = new Patient();
        patient.setId(patientId);
        newMedicalRecord.setPatient(patient);
        newMedicalRecord.setComplaint(complaint);
        newMedicalRecord.setDiagnosis(diagnosis);
        newMedicalRecord.setTreatment(treatment);

        service.update(newMedicalRecord, id);
        System.out.println("\nMedical record with id " + id + " successfully updated");
    }

    public void deleteMedicalRecord(){
        System.out.println("\n---------- DELETE MEDICAL RECORD ----------");
        System.out.print("Input id: ");
        int id = in.nextInt();
        Optional<MedicalRecord> medicalRecord = service.findById(id);
        while (medicalRecord == null) {
            System.out.print("Input id: ");
            id = in.nextInt();
            medicalRecord = service.findById(id);
        }
        service.delete(id);
        System.out.println("MedicalRecord with id " + id + " successfully deleted");
    }

}
