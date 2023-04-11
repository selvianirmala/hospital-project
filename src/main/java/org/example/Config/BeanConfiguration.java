package org.example.Config;

import org.example.Controller.*;
import org.example.model.entity.*;
import org.example.repository.*;
import org.example.service.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@PropertySource(("classpath:application.properties"))
public class BeanConfiguration {
    @Value("${driver}")
    private String dbDriver;
    @Value("${url}")
    private String url;
    @Value("${dbuser}")
    private String dbUser;
    @Value("${dbpassword}")
    private String dbPassword;

//    BEEN MAIN CONTROLLER
    @Bean
    public MainController getMainController(){
        return new MainController(getDoctorController(),
                                  getPatientController(),
                                  getMedicineController(),
                                  getMedicalRecordController(),
                                  getScheduleController(),
                                  getAppointmentController(),
                                  getMedicineMedicalRecordController());
    }

//    BEEN FOR DOCTOR
    @Bean
    public IRepository<Doctor> getDoctorRepository(){
        return new DoctorRepository(dataSource());
    }
    @Bean
    public IService<Doctor> getDoctorService(){
        return new DoctorService(getDoctorRepository());
    }
    @Bean
    public DoctorController getDoctorController(){
        return new DoctorController(getDoctorService());
    }


//    BEAN FOR PATIENT
    @Bean
    public IRepository<Patient> getPatientRepository(){
    return new PatientRepository(dataSource());
}
    @Bean
    public IService<Patient> getPatientService(){
        return new PatientService(getPatientRepository());
    }
    @Bean
    public PatientController getPatientController(){
        return new PatientController(getPatientService());
    }

//    BEAN FOR MEDICINE
    @Bean
    public IRepository<Medicine> getMedicineRepository(){
        return new MedicineRepository(dataSource());
    }
    @Bean
    public IService<Medicine> getMedicineService(){
        return new MedicineService(getMedicineRepository());
    }
    @Bean
    public MedicineController getMedicineController(){
        return new MedicineController(getMedicineService());
    }

//    BEAN FOR MEDICAL RECORD
    @Bean
    public IRepository<MedicalRecord> getMedicalRecordRepository(){
        return new MedicalRecordRepository(dataSource());
    }
    @Bean
    public IService<MedicalRecord> getMedicalRecordService(){
        return new MedicalRecordService(getMedicalRecordRepository());
    }
    @Bean
    public MedicalRecordController getMedicalRecordController(){
        return new MedicalRecordController(getMedicalRecordService());
    }

//    BEAN FOR SCHEDULE
    @Bean
    public IRepository<Schedule> getScheduleRepository(){
        return new ScheduleRepository(dataSource());
    }
    @Bean
    public IService<Schedule> getScheduleService(){
        return new ScheduleService(getScheduleRepository());
    }
    @Bean
    public ScheduleController getScheduleController(){
        return new ScheduleController(getScheduleService());
    }

//    BEAN FOR APPOINTMENT
    @Bean
    public IRepository<Appointment> getAppointmentRepository(){
        return new AppointmentRepository(dataSource());
    }
    @Bean
    public IService<Appointment> getAppointmentService(){
        return new AppointmentService(getAppointmentRepository());
    }
    @Bean
    public AppointmentController getAppointmentController(){
        return new AppointmentController(getAppointmentService());
    }

//    BEAN FOR MEDICINE_MEDICALRECORD
    @Bean
    public IRepository<MedicineMedicalRecord> getMedicineMedicalRecordRepository(){
        return new MedicineMedicalRecordRepository(dataSource());
    }
    @Bean
    public IService<MedicineMedicalRecord> getMedicineMedicalRecordService(){
        return new MedicineMedicalRecordService(getMedicineMedicalRecordRepository());
    }
    @Bean
    public MedicineMedicalRecordController getMedicineMedicalRecordController(){
        return new MedicineMedicalRecordController(getMedicineMedicalRecordService());
    }


    @Bean
    DataSource dataSource(){
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setUrl(url);
        driverManagerDataSource.setUsername(dbUser);
        driverManagerDataSource.setPassword(dbPassword);
        driverManagerDataSource.setDriverClassName(dbDriver);

        return driverManagerDataSource;
    }
}
