package cz.cvut.fit.tomanma9.tjvhospital.business;

import cz.cvut.fit.tomanma9.tjvhospital.dao.AppointmentRepository;
import cz.cvut.fit.tomanma9.tjvhospital.domain.Appointment;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.Collection;
import java.util.Random;

@Service
public class AppointmentService extends AbstractLongIdCrudService<Appointment> {

    // automatically creates and manages appropriate repository bcs of name
    public AppointmentService(AppointmentRepository repository) {
        super(repository);
    }

}
