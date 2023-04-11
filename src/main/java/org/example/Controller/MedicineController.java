package org.example.Controller;

import org.example.model.entity.Medicine;
import org.example.service.IService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class MedicineController {
    private IService<Medicine> service;

    public MedicineController() {
    }

    public MedicineController(IService<Medicine> service) {
        this.service = service;
    }

    private Scanner in = new Scanner(System.in);

    public void medicineMenu(){
        boolean loop = true;
        while (loop){
            System.out.println("\n==================== MEDICINE MENU ====================");
            System.out.println("1. Add medicine");
            System.out.println("2. Show medicine");
            System.out.println("3. Update medicine");
            System.out.println("4. Delete medicine");
            System.out.println("5. Back");
            System.out.print("Choose menu > ");
            int menu = in.nextInt();
            switch (menu){
                case 1 -> addMedicine();
                case 2 -> showMedicine();
                case 3 -> updateMedicine();
                case 4 -> deleteMedicine();
                case 5 -> loop = false;
                default -> System.out.println("\nInput invalid");
            }
        }
    }

    public void addMedicine(){
        System.out.println("\n---------- ADD MEDICINE ----------");
        System.out.print("Name: ");
        in.nextLine();
        String name = in.nextLine();
        System.out.print("Description: ");
        String description = in.nextLine();

        Medicine medicine = new Medicine();
        medicine.setName(name);
        medicine.setDescription(description);


        service.create(medicine);
        System.out.println("\nMedicine " + name + " successfully created");
    }

    public void showMedicine(){
        System.out.println("\n---------- SHOW MEDICINE ----------");
        System.out.println("1. Show all");
        System.out.println("2. Show by id");
        System.out.print("Choose menu > ");
        int menu = in.nextInt();
        switch (menu){
            case 1 -> {
                List<Medicine> medicineList = service.getAll();
                if(medicineList.isEmpty()){
                    System.out.println("List is empty");
                    break;
                }
                medicineList.stream().map(medicine -> medicine.getName() + "\n" +
                                medicine.getDescription())
                        .forEach(System.out::println);
            }

            case 2 -> {
                System.out.println("\n>>> Show By ID <<<");
                System.out.print("Input id: ");
                int id = in.nextInt();
                Optional<Medicine> medicine = service.findById(id);
                while (medicine == null) {
                    System.out.print("Input id: ");
                    id = in.nextInt();
                    medicine = service.findById(id);
                }
                System.out.println(medicine.get());
            }

            default -> System.out.println("\nInput invalid");
        }
    }

    public void updateMedicine(){
        System.out.println("\n---------- UPDATE MEDICINE ----------");
        System.out.print("Input id: ");
        int id = in.nextInt();
        Optional<Medicine> medicine = service.findById(id);
        while (medicine == null) {
            System.out.print("Input id: ");
            id = in.nextInt();
            medicine = service.findById(id);
        }
        System.out.print("Name: ");
        in.nextLine();
        String name = in.nextLine();
        System.out.print("Description: ");
        String description = in.nextLine();

        Medicine newMedicine = new Medicine();
        newMedicine.setName(name);
        newMedicine.setDescription(description);

        service.update(newMedicine, id);
        System.out.println("\nMedicine with id " + id + " successfully updated");
    }

    public void deleteMedicine(){
        System.out.println("\n---------- DELETE MEDICINE ----------");
        System.out.print("Input id: ");
        int id = in.nextInt();
        Optional<Medicine> medicine = service.findById(id);
        while(medicine == null){
            System.out.print("Input id: ");
            id = in.nextInt();
            medicine = service.findById(id);
        }
        service.delete(id);
        System.out.println("\nMedicine with id " + id + " successfully deleted");
    }
}
