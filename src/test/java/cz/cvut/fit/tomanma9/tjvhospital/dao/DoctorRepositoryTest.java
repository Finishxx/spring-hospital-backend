package cz.cvut.fit.tomanma9.tjvhospital.dao;

import cz.cvut.fit.tomanma9.tjvhospital.domain.Doctor;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.images.builder.ImageFromDockerfile;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.swing.text.html.Option;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

// inspiration from: https://www.testcontainers.org/features/creating_images/
// HEAVY inspiration from https://ajayiyengar.com/2020/05/18/integration-testing-using-testcontainers-in-a-spring-boot-2-jpa-application/
@DataJpaTest
@AutoConfigureTestDatabase( replace = AutoConfigureTestDatabase.Replace.NONE )
@ContextConfiguration(initializers = { DoctorRepositoryTest.PropertiesInitializer.class })
@Sql({ "classpath:create-script.sql" })
public class DoctorRepositoryTest extends AbstractPostgresContainerBaseTest {

    @Autowired
    private DoctorRepository doctorRepository;

    private Doctor doctor1;
    private Doctor doctor2;

    @BeforeEach
    public void createDoctors() {
        doctor1 = new Doctor(0L, "Tomáš", "tomas@hospital.cz", "111-111-111");
        doctor2 = new Doctor(1L, "Jirka", "jirka@hospital.cz", "222-222-222L");

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
        Doctor foundDoctor2 = foundDoctorOptional1.get();

        assertEquals(doctor1, foundDoctor1);
        assertEquals(doctor2, foundDoctor2);
    }

    @Test
    public void saveDoctorsAndFindAll() {
        doctorRepository.save(doctor1);
        doctorRepository.save(doctor2);

        List<Doctor> doctors = doctorRepository.findAll();

        assertEquals(2, doctors.size());

        assertTrue(doctors.contains(doctor1));
        assertTrue(doctors.contains(doctor2));
    }

    @Test
    public void saveDoctorsAndDelete() {
        System.out.println(postgresContainer.getLogs());
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



}
