package org.example.Controller;

import org.example.model.entity.Appointment;
import org.example.model.entity.Schedule;

import java.util.Scanner;

public class MainController {
    private DoctorController doctorController;
    private PatientController patientController;
    private MedicineController medicineController;
    private MedicalRecordController medicalRecordController;
    private ScheduleController scheduleController;
    private AppointmentController appointmentController;
    private MedicineMedicalRecordController medicineMedicalRecordController;

    public MainController() {
    }

    public MainController(DoctorController doctorController, PatientController patientController, MedicineController medicineController, MedicalRecordController medicalRecordController, ScheduleController scheduleController, AppointmentController appointmentController, MedicineMedicalRecordController medicineMedicalRecordController) {
        this.doctorController = doctorController;
        this.patientController = patientController;
        this.medicineController = medicineController;
        this.medicalRecordController = medicalRecordController;
        this.scheduleController = scheduleController;
        this.appointmentController = appointmentController;
        this.medicineMedicalRecordController = medicineMedicalRecordController;
    }

    public void run() {
        Scanner in = new Scanner(System.in);
        while(true){
            System.out.println("\n===============================================");
            System.out.println("||              ENIGMA HOSPITAL              ||");
            System.out.println("===============================================");
            System.out.println("1. Doctor");
            System.out.println("2. Patient");
            System.out.println("3. Medicine");
            System.out.println("4. Medical record");
            System.out.println("5. Schedule");
            System.out.println("6. Appointment");
            System.out.println("7. Medicine medical record");
            System.out.print("Choose menu > ");
            int menu = in.nextInt();
            switch (menu){
                case 1 -> doctorController.doctorMenu();
                case 2 -> patientController.patientMenu();
                case 3 -> medicineController.medicineMenu();
                case 4 -> medicalRecordController.medicalRecordMenu();
                case 5 -> scheduleController.scheduleMenu();
                case 6 -> appointmentController.appointmentMenu();
                case 7 -> medicineMedicalRecordController.medicineMedicalRecordMenu();
                default -> System.out.println("Input invalid");
            }
        }

    }
}
