package cz.cvut.fit.tomanma9.tjvhospital.business;

import cz.cvut.fit.tomanma9.tjvhospital.dao.AppointmentRepository;
import cz.cvut.fit.tomanma9.tjvhospital.dao.DoctorRepository;
import cz.cvut.fit.tomanma9.tjvhospital.domain.Appointment;
import cz.cvut.fit.tomanma9.tjvhospital.domain.Doctor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService extends AbstractCrudService<Doctor, Long> {

    AppointmentService appointmentService;

    // automatically creates and manages appropriate repository bcs of name
    public DoctorService(DoctorRepository repository, AppointmentService appointmentService) {
        super(repository);
        this.appointmentService = appointmentService;
    }

    public void cancelAllAppointmentsByDate(Long doctorId, LocalDate date) {
        Optional<Doctor> doctorOptional = repository.findById(doctorId);
        if (doctorOptional.isEmpty())
            return;
        Doctor doctor = doctorOptional.get();
        List<Long> appointments = doctor.getAppointments().stream()
                .filter( appointment -> appointment.getFrom().toLocalDate().equals(date) )
                .map(Appointment::getId) // thank you IDEA <3
                .toList();
        // Doctor and patient will be updated because Appointment is the owning structure of Doctor and Patient
        appointmentService.deleteAllyById(appointments);
    }
}
