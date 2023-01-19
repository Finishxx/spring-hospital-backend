package cz.cvut.fit.tomanma9.tjvhospital.dao;

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
import java.util.List;
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
public class DoctorRepositoryIntegrationTest extends AbstractPostgresContainerBaseTest {

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;

    private Doctor doctor1;
    private Doctor doctor2;
    private Patient patient1;
    private Patient patient2;

    @BeforeEach
    public void createDoctors() {
        doctor1 = new Doctor(0L, "Tomáš", "tomas@hospital.cz", "111-111-111");
        doctor2 = new Doctor(1L, "Jirka", "jirka@hospital.cz", "222-222-222L");
        patient1 = new Patient(0L, "Marek", LocalDate.of(1999, 9, 9), "marek@gmail.com", "333-333-333");
        patient1 = new Patient(1L, "Honza", LocalDate.of(1990, 5, 11), "honza@gmail.com", "444-444-444");
    }

    @Test
    public void saveDoctorsAndFindById() {

        doctorRepository.save(doctor1);
        doctorRepository.save(doctor2);

        Optional<Doctor> foundDoctorOptional1 = doctorRepository.findById(doctor1.getId());
        Optional<Doctor> foundDoctorOptional2 = doctorRepository.findById(doctor2.getId());

        assertTrue(foundDoctorOptional1.isPresent());
        assertTrue(foundDoctorOptional2.isPresent());

        Doctor foundDoctor1 = foundDoctorOptional1.get();
        Doctor foundDoctor2 = foundDoctorOptional2.get();

        assertEquals(doctor1, foundDoctor1);
        assertEquals(doctor2, foundDoctor2);
        // cleanup
        doctorRepository.deleteById(doctor1.getId());
        doctorRepository.deleteById(doctor2.getId());

        assertEquals(0, doctorRepository.findAll().size());
    }

    @Test
    public void saveDoctorsAndFindAll() {

        doctorRepository.save(doctor1);
        doctorRepository.save(doctor2);

        List<Doctor> doctors = doctorRepository.findAll();

        assertEquals(2, doctors.size());

        assertTrue(doctors.contains(doctor1));
        assertTrue(doctors.contains(doctor2));

        // cleanup
        doctorRepository.deleteById(doctor1.getId());
        doctorRepository.deleteById(doctor2.getId());

        assertEquals(0, doctorRepository.findAll().size());
    }

    @Test
    public void saveDoctorsAndDelete() {
        doctorRepository.save(doctor1);
        doctorRepository.save(doctor2);

        doctorRepository.deleteById(doctor1.getId());
        Optional<Doctor> deletedDoctor1 = doctorRepository.findById(doctor1.getId());
        assertTrue(deletedDoctor1.isEmpty());

        Optional<Doctor> foundDoctor2 = doctorRepository.findById(doctor2.getId());
        assertTrue(foundDoctor2.isPresent());

        doctorRepository.deleteById(doctor2.getId());
        Optional<Doctor> deletedDoctor2 = doctorRepository.findById(doctor2.getId());
        assertTrue(deletedDoctor2.isEmpty());
    }

    @Test
    public void updateDoctor() {
        doctorRepository.save(doctor1);

        Doctor newDoctor = new Doctor(doctor1.getId(), "Bartoš", "bartos@hospital.cz", "333-333-333");
        doctorRepository.save(newDoctor);

        Optional<Doctor> foundDoctor = doctorRepository.findById(doctor1.getId());

        assertTrue(foundDoctor.isPresent());
        assertEquals(foundDoctor.get(), newDoctor);

        doctorRepository.deleteById(doctor1.getId());
    }

    // doctor is the owner of the bidirectional mapping
    // therefore we should be able to retrieve patient as well
    @Test
    public void saveDoctorWithPatient() {
        // how do I give doctor his patient? :DD

    }
}
