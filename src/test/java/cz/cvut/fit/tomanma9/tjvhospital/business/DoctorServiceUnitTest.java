package cz.cvut.fit.tomanma9.tjvhospital.business;

import cz.cvut.fit.tomanma9.tjvhospital.dao.AppointmentRepository;
import cz.cvut.fit.tomanma9.tjvhospital.dao.DoctorRepository;
import cz.cvut.fit.tomanma9.tjvhospital.dao.PatientRepository;
import cz.cvut.fit.tomanma9.tjvhospital.domain.Doctor;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
class DoctorServiceUnitTest {
    // testing all CRUD

    @Mock
    private DoctorService doctorService;

    @Mock
    private DoctorRepository doctorRepository;
    @Test
    void create() {
    }

}