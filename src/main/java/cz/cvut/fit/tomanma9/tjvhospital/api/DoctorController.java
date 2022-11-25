package cz.cvut.fit.tomanma9.tjvhospital.api;

import cz.cvut.fit.tomanma9.tjvhospital.api.model.DoctorDto;
import cz.cvut.fit.tomanma9.tjvhospital.api.model.converter.DoctorToDtoConverter;
import cz.cvut.fit.tomanma9.tjvhospital.api.model.converter.DoctorToEntityConverter;
import cz.cvut.fit.tomanma9.tjvhospital.business.AbstractCrudService;
import cz.cvut.fit.tomanma9.tjvhospital.business.DoctorService;
import cz.cvut.fit.tomanma9.tjvhospital.domain.Doctor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// When spring receives HTTP request for /doctors, he then
@RestController
@RequestMapping("/doctors")
public class DoctorController extends AbstractCrudController<Doctor, DoctorDto, Long> {

    public DoctorController(DoctorService service, DoctorToDtoConverter doctorToDtoConverter, DoctorToEntityConverter doctorToEntityConverter) {
        super(service, doctorToDtoConverter, doctorToEntityConverter);
    }
}
