package cz.cvut.fit.tomanma9.tjvhospital.api.model.converter;


import cz.cvut.fit.tomanma9.tjvhospital.api.model.DoctorDto;
import cz.cvut.fit.tomanma9.tjvhospital.api.model.InnerAppointmentForDoctorDto;
import cz.cvut.fit.tomanma9.tjvhospital.api.model.InnerPatientDto;
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
                .map((Appointment appointment) -> {
                    InnerAppointmentForDoctorDto innerAppointment = new InnerAppointmentForDoctorDto();
                    innerAppointment.setAppointment_id(appointment.getId());
                    innerAppointment.setPatient_id(appointment.getPatient().getId());
                    innerAppointment.setPatient_name(appointment.getPatient().getName());
                    innerAppointment.setTime_from(appointment.getFrom());
                    return innerAppointment;
                })
                .toList());

        dto.setPatients(doctor.getPatients().stream()
                .map((Patient patient) -> {
                    InnerPatientDto innerPatientDto = new InnerPatientDto();
                    innerPatientDto.setPatient_id(patient.getId());
                    innerPatientDto.setPatient_name(patient.getName());
                    return innerPatientDto;
                })
                .toList());
        return dto;

    }
}
