package cz.cvut.fit.tomanma9.tjvhospital.api;

import cz.cvut.fit.tomanma9.tjvhospital.api.model.AppointmentDto;
import cz.cvut.fit.tomanma9.tjvhospital.business.AbstractCrudService;
import cz.cvut.fit.tomanma9.tjvhospital.business.AppointmentService;
import cz.cvut.fit.tomanma9.tjvhospital.business.DoctorService;
import cz.cvut.fit.tomanma9.tjvhospital.business.PatientService;
import cz.cvut.fit.tomanma9.tjvhospital.domain.Appointment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appointments")
public class AppointmentController extends AbstractCrudController<Appointment, AppointmentDto, Long> {
    public AppointmentController(AppointmentService service, PatientService patientService, DoctorService doctorService) {
        super(
                service,
                appointment -> {
                    AppointmentDto dto = new AppointmentDto();

                    dto.setId(appointment.getId());
                    dto.setPatient(appointment.getPacient().getId());
                    dto.setDoctor(appointment.getDoctor().getId());
                    dto.setFrom(appointment.getFrom());
                    dto.setTo(appointment.getTo());

                    return dto;
                },
                dto -> {
                    // nullpointers are OK
                    return new Appointment(dto.getId(),
                            patientService.readById(dto.getPatient()).get(),
                            doctorService.readById(dto.getDoctor()).get(),
                            dto.getFrom(),
                            dto.getTo());
                });
    }
}
