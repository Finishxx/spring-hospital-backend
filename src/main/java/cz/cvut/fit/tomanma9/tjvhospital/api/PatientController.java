package cz.cvut.fit.tomanma9.tjvhospital.api;

import cz.cvut.fit.tomanma9.tjvhospital.api.model.PatientDto;
import cz.cvut.fit.tomanma9.tjvhospital.api.model.converter.PatientToDtoConverter;
import cz.cvut.fit.tomanma9.tjvhospital.api.model.converter.PatientToEntityConverter;
import cz.cvut.fit.tomanma9.tjvhospital.business.AbstractCrudService;
import cz.cvut.fit.tomanma9.tjvhospital.business.PatientService;
import cz.cvut.fit.tomanma9.tjvhospital.domain.Patient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patients")
public class PatientController extends AbstractCrudController<Patient, PatientDto, Long>{
    public PatientController(PatientService service, PatientToDtoConverter patientToDtoConverter, PatientToEntityConverter patientToEntityConverter) {
        super(service, patientToDtoConverter, patientToEntityConverter);
    }
}
