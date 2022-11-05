package cz.cvut.fit.tomanma9.tjvhospital.business;

import cz.cvut.fit.tomanma9.tjvhospital.dao.AppointmentRepository;
import cz.cvut.fit.tomanma9.tjvhospital.domain.Appointment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService extends AbstractCrudService<Appointment, Long> {

    // automatically creates and manages appropriate repository bcs of name
    protected AppointmentService(AppointmentRepository repository) {
        super(repository);
    }
}
