package cz.cvut.fit.tomanma9.tjvhospital.api.controller;

import cz.cvut.fit.tomanma9.tjvhospital.api.model.AppointmentDto;
import cz.cvut.fit.tomanma9.tjvhospital.api.model.InnerDoctorDto;
import cz.cvut.fit.tomanma9.tjvhospital.api.model.InnerPatientDto;
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
        super( // imho the class system is better, but we tried
                service,
                appointment -> {
                    AppointmentDto dto = new AppointmentDto();

                    dto.setId(appointment.getId());

                    InnerDoctorDto innerDoctorDto = new InnerDoctorDto();
                    innerDoctorDto.setDoctor_id(appointment.getDoctor().getId());
                    innerDoctorDto.setDoctor_name(appointment.getDoctor().getName());
                    dto.setDoctor(innerDoctorDto);

                    InnerPatientDto innerPatientDto = new InnerPatientDto();
                    innerPatientDto.setPatient_id(appointment.getPatient().getId());
                    innerPatientDto.setPatient_name(appointment.getPatient().getName());
                    dto.setPatient(innerPatientDto);
                    dto.setFrom(appointment.getFrom());
                    dto.setTo(appointment.getTo());

                    return dto;
                },
                dto -> {
                    // nullpointers are OK
                    return new Appointment(dto.getId(),
                            patientService.readById(dto.getPatient().getPatient_id()).get(),
                            doctorService.readById(dto.getDoctor().getDoctor_id()).get(),
                            dto.getFrom(),
                            dto.getTo());
                });
    }
}
