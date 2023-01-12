package cz.cvut.fit.tomanma9.tjvhospital.api.model.converter;


import cz.cvut.fit.tomanma9.tjvhospital.api.model.*;
import cz.cvut.fit.tomanma9.tjvhospital.domain.Appointment;
import cz.cvut.fit.tomanma9.tjvhospital.domain.Doctor;
import cz.cvut.fit.tomanma9.tjvhospital.domain.Patient;
import org.springframework.stereotype.Component;

import java.util.function.Function;

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
                .map((Appointment appointment) -> {
                    InnerAppointmentForPatientDto innerAppointment = new InnerAppointmentForPatientDto();
                    innerAppointment.setAppointment_id(appointment.getId());
                    innerAppointment.setDoctor_id(appointment.getDoctor().getId());
                    innerAppointment.setDoctor_name(appointment.getDoctor().getName());
                    innerAppointment.setTime_from(appointment.getFrom());
                    return innerAppointment;
                })
                .toList());

        dto.setDoctors(patient.getDoctors().stream()
                .map((Doctor doctor) -> {
                    InnerDoctorDto innerDoctorDto = new InnerDoctorDto();
                    innerDoctorDto.setDoctor_id(doctor.getId());
                    innerDoctorDto.setDoctor_name(doctor.getName());
                    return innerDoctorDto;
                })
                .toList());

        return dto;
    }
}
