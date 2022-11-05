package cz.cvut.fit.tomanma9.tjvhospital.business;

import cz.cvut.fit.tomanma9.tjvhospital.dao.DoctorRepository;
import cz.cvut.fit.tomanma9.tjvhospital.domain.Doctor;
import org.springframework.data.repository.CrudRepository;

public class DoctorService extends AbstractCrudService<Doctor, Long> {

    // automatically creates and manages appropriate repository bcs of name
    public DoctorService(DoctorRepository repository, AppointmentService appointmentService) {
        super(repository);
    }
}
