package org.example.Controller;

import org.example.model.entity.MedicalRecord;
import org.example.model.entity.Medicine;
import org.example.model.entity.MedicineMedicalRecord;
import org.example.model.entity.Patient;
import org.example.service.IService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class MedicineMedicalRecordController{
    private IService<MedicineMedicalRecord> service;

    public MedicineMedicalRecordController() {
    }

    public MedicineMedicalRecordController(IService<MedicineMedicalRecord> service) {
        this.service = service;
    }

    private Scanner in = new Scanner(System.in);
    public void medicineMedicalRecordMenu(){
        boolean loop = true;
        while (loop){
            System.out.println("\n==================== MEDICINE MEDICAL RECORD MENU ====================");
            System.out.println("1. Add medical record");
            System.out.println("2. Show medical record");
            System.out.println("3. Update medical record");
            System.out.println("4. Delete medical record");
            System.out.println("5. Back");
            System.out.print("Choose menu > ");
            int menu = in.nextInt();
            switch (menu){
                case 1 -> addMedicineMedicalRecord();
                case 2 -> showMedicineMedicalRecord();
                case 3 -> updateMedicineMedicalRecord();
                case 4 -> deleteMedicineMedicalRecord();
                case 5 -> loop = false;
                default -> System.out.println("Input invalid");
            }
        }
    }

    public void addMedicineMedicalRecord(){
        System.out.println("\n---------- ADD MEDICINE MEDICAL RECORD ----------");
        System.out.print("Id Medicine: ");
        int medicineId = in.nextInt();
        System.out.print("Id Medical Record: ");
        int medicalRecordId = in.nextInt();
        in.nextLine();
        System.out.print("Dosage: ");
        String dosage = in.nextLine();

        Medicine medicine = new Medicine();
        medicine.setId(medicineId);

        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setId(medicalRecordId);

        MedicineMedicalRecord medicineMedicalRecord = new MedicineMedicalRecord();
        medicineMedicalRecord.setMedicine(medicine);
        medicineMedicalRecord.setMedicalRecord(medicalRecord);
        medicineMedicalRecord.setDosage(dosage);

        service.create(medicineMedicalRecord);
        System.out.println("Successfully created");
    }

    public void showMedicineMedicalRecord(){
        System.out.println("\n---------- SHOW MEDICINE MEDICAL RECORD ----------");
        System.out.println("1. Show all");
        System.out.println("2. Show by id");
        System.out.print("Choose menu > ");
        int menu = in.nextInt();
        switch (menu){
            case 1 -> {
                List<MedicineMedicalRecord> medicineMedicalRecordList = service.getAll();
                if(medicineMedicalRecordList.isEmpty()){
                    System.out.println("List is empty");
                    break;
                }
                medicineMedicalRecordList.stream().map(medicineMedicalRecord -> medicineMedicalRecord.getId() + "\t |" +
                                medicineMedicalRecord.getMedicine().getName() + "\t |\t" +
                                medicineMedicalRecord.getMedicalRecord().getId() + "\t |\t" +
                                medicineMedicalRecord.getDosage())
                        .forEach(System.out::println);
            }

            case 2 -> {
                System.out.println(">>> Show By ID <<<");
                System.out.print("Input id: ");
                int id = in.nextInt();
                Optional<MedicineMedicalRecord> medicineMedicalRecord = service.findById(id);
                while (medicineMedicalRecord == null) {
                    System.out.print("Input id: ");
                    id = in.nextInt();
                    medicineMedicalRecord = service.findById(id);
                }
                System.out.println(medicineMedicalRecord.get());
            }

            default -> System.out.println("\nInput invalid");
        }
    }

    public void updateMedicineMedicalRecord(){
        System.out.println("\n---------- UPDATE MEDICINE MEDICAL RECORD ----------");
        System.out.print("Input id: ");
        int id = in.nextInt();
        Optional<MedicineMedicalRecord> medicineMedicalRecord = service.findById(id);
        while (medicineMedicalRecord == null) {
            System.out.print("Input id: ");
            id = in.nextInt();
            medicineMedicalRecord = service.findById(id);
        }
        System.out.print("Id Medicine: ");
        int medicineId = in.nextInt();
        System.out.print("Id Medical Record: ");
        int medicalRecordId = in.nextInt();
        in.nextLine();
        System.out.print("Dosage: ");
        String dosage = in.nextLine();

        Medicine medicine = new Medicine();
        medicine.setId(medicineId);

        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setId(medicalRecordId);

        MedicineMedicalRecord newMedicineMedicalRecord = new MedicineMedicalRecord();
        newMedicineMedicalRecord.setMedicine(medicine);
        newMedicineMedicalRecord.setMedicalRecord(medicalRecord);
        newMedicineMedicalRecord.setDosage(dosage);

        service.update(newMedicineMedicalRecord, id);
        System.out.println("Successfully updated");
    }

    public void deleteMedicineMedicalRecord(){
        System.out.println("\n---------- DELETE MEDICINE MEDICAL RECORD ----------");
        System.out.print("Input id: ");
        int id = in.nextInt();
        Optional<MedicineMedicalRecord> medicineMedicalRecord = service.findById(id);
        while (medicineMedicalRecord == null) {
            System.out.print("Input id: ");
            id = in.nextInt();
            medicineMedicalRecord = service.findById(id);
        }
        service.delete(id);
        System.out.println("MedicalRecord with id " + id + " successfully deleted");
    }
}
