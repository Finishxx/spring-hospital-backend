package cz.cvut.fit.tomanma9.tjvhospital.api;

import cz.cvut.fit.tomanma9.tjvhospital.business.AbstractCrudService;
import cz.cvut.fit.tomanma9.tjvhospital.domain.Patient;

public class PatientController extends AbstractCrudController<Patient, Long>{
    public PatientController(AbstractCrudService<Patient, Long> service) {
        super(service);
    }
}
