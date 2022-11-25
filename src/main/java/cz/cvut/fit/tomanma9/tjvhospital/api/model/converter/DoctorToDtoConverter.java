package cz.cvut.fit.tomanma9.tjvhospital.api.model.converter;


import cz.cvut.fit.tomanma9.tjvhospital.api.model.DoctorDto;
import cz.cvut.fit.tomanma9.tjvhospital.domain.Appointment;
import cz.cvut.fit.tomanma9.tjvhospital.domain.Doctor;
import cz.cvut.fit.tomanma9.tjvhospital.domain.Patient;
import org.springframework.stereotype.Component;

import java.util.function.Function;

// Functor of Doctor -> DoctorDto
// where apply is like operator() in c++
@Component
public class DoctorToDtoConverter implements Function<Doctor, DoctorDto> {
    @Override
    public DoctorDto apply(Doctor doctor) {
        DoctorDto dto = new DoctorDto();

        dto.setId(doctor.getId());
        dto.setName(doctor.getName());
        dto.setEmailAddress(doctor.getEmailAddress());
        dto.setPhoneNumber(doctor.getPhoneNumber());

        dto.setAppointments(doctor.getAppointments().stream()
                .map(Appointment::getId) // thank you idea for this method-reference-thingy!
                .toList());

        dto.setPatients(doctor.getPatients().stream()
                .map(Patient::getId)
                .toList());
        return dto;

    }
}
