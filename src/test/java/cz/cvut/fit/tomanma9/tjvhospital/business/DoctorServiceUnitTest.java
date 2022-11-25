package cz.cvut.fit.tomanma9.tjvhospital.business;

import cz.cvut.fit.tomanma9.tjvhospital.dao.AppointmentRepository;
import cz.cvut.fit.tomanma9.tjvhospital.dao.PatientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
class DoctorServiceUnitTest {

    @Autowired
    private DoctorService doctorService;

    @MockBean
    private AppointmentRepository appointmentRepository;

    @MockBean
    private PatientRepository userRepository;

    @Test
    void shouldCancelMultipleAppointments() { //think about cascading
        // preparation


        // mock


        // test
    }

    @Test
    void shouldNotCancelAnything() {

    }

    @Test
    void receivesInvalidDoctorId() {

    }

    @Test
    void receivesInvalidDate() { //idk if it is possible

    }

    @Test
    void receivesTimeBetweenTwoDates() { // idk what the response should be here

    }

    @Test
    void receivesOldDate() { //old id questionable naming (something like expired could be better?)

    }
}