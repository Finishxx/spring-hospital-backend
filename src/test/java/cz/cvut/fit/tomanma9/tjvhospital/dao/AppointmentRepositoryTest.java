package cz.cvut.fit.tomanma9.tjvhospital.dao;

import cz.cvut.fit.tomanma9.tjvhospital.domain.Appointment;
import cz.cvut.fit.tomanma9.tjvhospital.domain.Doctor;
import cz.cvut.fit.tomanma9.tjvhospital.domain.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

// inspiration from: https://www.testcontainers.org/features/creating_images/
// HEAVY inspiration from https://ajayiyengar.com/2020/05/18/integration-testing-using-testcontainers-in-a-spring-boot-2-jpa-application/
// basic CRUD test
@DataJpaTest
@AutoConfigureTestDatabase( replace = AutoConfigureTestDatabase.Replace.NONE )
@ContextConfiguration(initializers = { DoctorRepositoryIntegrationTest.PropertiesInitializer.class })
@Sql({ "classpath:create-script.sql" })
@ActiveProfiles("dbtest")
@Tag("integration")
public class AppointmentRepositoryTest extends AbstractPostgresContainerBaseTest {

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;

    private Doctor doctor1;
    private Doctor doctor2;
    private Patient patient1;
    private Patient patient2;
    private Appointment appointment1;
    private Appointment appointment2;
    private Appointment appointment3;
    private Appointment appointment4;


    @BeforeEach
    public void createDoctors() {
        doctor1 = new Doctor(0L, "Tomáš", "tomas@hospital.cz", "111-111-111");
        doctor2 = new Doctor(1L, "Jirka", "jirka@hospital.cz", "222-222-222L");
        patient1 = new Patient(0L, "Marek", LocalDate.of(1999, 9, 9), "marek@gmail.com", "333-333-333");
        patient2 = new Patient(1L, "Honza", LocalDate.of(1990, 5, 11), "honza@gmail.com", "444-444-444");
        appointment1 = new Appointment(0L, patient1, doctor1, LocalDateTime.of(2000, 1, 1, 12, 0), LocalDateTime.of(2000, 1, 1, 12, 30));
        appointment2 = new Appointment(1L, patient2, doctor1, LocalDateTime.of(2000, 1, 1, 12, 30), LocalDateTime.of(2000, 1, 1, 13, 0));
        appointment3 = new Appointment(2L, patient1, doctor2, LocalDateTime.of(2000, 2, 1, 12, 0), LocalDateTime.of(2000, 2, 1, 12, 30));
        appointment4 = new Appointment(3L, patient2, doctor2, LocalDateTime.of(2000, 2, 1, 12, 30), LocalDateTime.of(2000, 2, 1, 13, 0));
    }

    @Test
    public void saveAppointmentsAndFindDoctorsAndPatients() {
        doctorRepository.save(doctor1);
        patientRepository.save(patient1);
        patientRepository.save(patient2);

        assertTrue(doctorRepository.findById(doctor1.getId()).isPresent());
        assertTrue(patientRepository.findById(patient1.getId()).isPresent());
        assertTrue(patientRepository.findById(patient2.getId()).isPresent());

        appointmentRepository.save(appointment1);
        appointmentRepository.save(appointment2);

        Optional<Appointment> foundAppointment1 = appointmentRepository.findById(0L);
        Optional<Appointment> foundAppointment2 = appointmentRepository.findById(1L);

        assertTrue(foundAppointment1.isPresent());
        assertTrue(foundAppointment2.isPresent());

        Doctor foundDoctor1 = foundAppointment1.get().getDoctor();
        Doctor foundDoctor2 = foundAppointment2.get().getDoctor();

        assertEquals(foundDoctor1, foundDoctor2);

        Optional<Doctor> doctorRepositoryDoctor = doctorRepository.findById(doctor1.getId());

        assertTrue(doctorRepositoryDoctor.isPresent());
        assertEquals(doctorRepositoryDoctor.get(), doctor1);

        doctorRepository.deleteById(doctor1.getId());
        patientRepository.deleteById(patient1.getId());
        patientRepository.deleteById(patient2.getId());
        appointmentRepository.deleteById(appointment1.getId());
        appointmentRepository.deleteById(appointment2.getId());
    }
}
