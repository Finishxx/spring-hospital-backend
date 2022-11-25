package cz.cvut.fit.tomanma9.tjvhospital.api.model.converter;


import cz.cvut.fit.tomanma9.tjvhospital.api.model.PatientDto;
import cz.cvut.fit.tomanma9.tjvhospital.domain.Appointment;
import cz.cvut.fit.tomanma9.tjvhospital.domain.Doctor;
import cz.cvut.fit.tomanma9.tjvhospital.domain.Patient;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.stream.Collectors;

// Functor of Patient -> PatientDto
// where apply is like operator() in c++
@Component
public class PatientToDtoConverter implements Function<Patient, PatientDto> {
    @Override
    public PatientDto apply(Patient patient) {

        PatientDto dto = new PatientDto();

        dto.setId(patient.getId());
        dto.setName(patient.getName());
        dto.setEmailAddress(patient.getEmailAddress());
        dto.setPhoneNumber(patient.getPhoneNumber());
        dto.setBirthdate(patient.getBirthdate());

        dto.setAppointments(patient.getAppointments().stream()
                .map(Appointment::getId)
                .toList());
        dto.setDoctors(patient.getDoctors().stream()
                .map(Doctor::getId)
                .toList());

        return dto;
    }
}
