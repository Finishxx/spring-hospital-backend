package cz.cvut.fit.tomanma9.tjvhospital.business;

import cz.cvut.fit.tomanma9.tjvhospital.dao.PatientRepository;
import cz.cvut.fit.tomanma9.tjvhospital.domain.Patient;
import org.springframework.data.repository.CrudRepository;

public class PatientService extends AbstractCrudService<Patient, Long> {

    // automatically creates and manages appropriate repository bcs of name
    protected PatientService(PatientRepository repository) {
        super(repository);
    }
}
