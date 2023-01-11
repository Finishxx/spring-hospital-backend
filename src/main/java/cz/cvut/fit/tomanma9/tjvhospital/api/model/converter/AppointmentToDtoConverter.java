package cz.cvut.fit.tomanma9.tjvhospital.api.model.converter;

import cz.cvut.fit.tomanma9.tjvhospital.api.model.AppointmentDto;
import cz.cvut.fit.tomanma9.tjvhospital.domain.Appointment;
import org.springframework.stereotype.Component;

import java.util.function.Function;

// Functor of Appointment -> AppointmentDto
// where apply is like operator() in c++
@Component
public class AppointmentToDtoConverter implements Function<Appointment, AppointmentDto> {

    @Override
    public AppointmentDto apply(Appointment appointment) {
        AppointmentDto dto = new AppointmentDto();

        dto.setId(appointment.getId());
        dto.setPatient(appointment.getPatient().getId());
        dto.setDoctor(appointment.getDoctor().getId());
        dto.setFrom(appointment.getFrom());
        dto.setTo(appointment.getTo());

        return dto;
    }
}
