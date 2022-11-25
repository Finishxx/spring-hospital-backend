package cz.cvut.fit.tomanma9.tjvhospital.api;

import cz.cvut.fit.tomanma9.tjvhospital.business.AbstractCrudService;
import cz.cvut.fit.tomanma9.tjvhospital.domain.Doctor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// When spring receives HTTP request for /doctors, he then
@RestController
@RequestMapping("/doctors")
public class DoctorController extends AbstractCrudController<Doctor, Long> {

    public DoctorController(AbstractCrudService<Doctor, Long> service) {
        super(service);
    }
}
