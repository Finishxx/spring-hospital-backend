package cz.cvut.fit.tomanma9.tjvhospital.api.model.converter;


import cz.cvut.fit.tomanma9.tjvhospital.api.model.DoctorDto;
import cz.cvut.fit.tomanma9.tjvhospital.api.model.InnerPatientDto;
import cz.cvut.fit.tomanma9.tjvhospital.domain.Doctor;
import cz.cvut.fit.tomanma9.tjvhospital.domain.Patient;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

// Functor of DoctorDto -> Doctor
// where apply is like operator() in c++
@Component
public class DoctorToEntityConverter implements Function<DoctorDto, Doctor> {

    @Override
    public Doctor apply(DoctorDto doctorDto) {
        // doctor is owning doctor-patient relation, therefore we need to update it
        Doctor doctor = new Doctor(doctorDto.getId(), doctorDto.getName(), doctorDto.getEmailAddress(), doctorDto.getPhoneNumber());
        List<Long> patientIds = doctorDto.getPatients().stream()
                                    .map(InnerPatientDto::getPatient_id).toList();
        for (Long patientId : patientIds) {
            Patient addedPatient = new Patient();
            addedPatient.setId(patientId);
            doctor.addPatient(addedPatient); // hope this is enough
        }
        return doctor;
    }
}
