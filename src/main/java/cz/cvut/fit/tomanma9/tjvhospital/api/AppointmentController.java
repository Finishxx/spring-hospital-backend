package cz.cvut.fit.tomanma9.tjvhospital.api;

import cz.cvut.fit.tomanma9.tjvhospital.business.AbstractCrudService;
import cz.cvut.fit.tomanma9.tjvhospital.domain.Appointment;

public class AppointmentController extends AbstractCrudController<Appointment, Long> {
    public AppointmentController(AbstractCrudService<Appointment, Long> service) {
        super(service);
    }
}
