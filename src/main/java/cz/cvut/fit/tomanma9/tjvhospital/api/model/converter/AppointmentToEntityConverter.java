package cz.cvut.fit.tomanma9.tjvhospital.api.model.converter;


import cz.cvut.fit.tomanma9.tjvhospital.api.model.AppointmentDto;
import cz.cvut.fit.tomanma9.tjvhospital.domain.Appointment;
import org.springframework.stereotype.Component;

import java.util.function.Function;

// Functor of AppointmentDto -> Appointment
// where apply is like operator() in c++
@Component
public class AppointmentToEntityConverter implements Function<AppointmentDto, Appointment> {

    @Override
    public Appointment apply(AppointmentDto appointmentDto) {
        return null;
    }
}
