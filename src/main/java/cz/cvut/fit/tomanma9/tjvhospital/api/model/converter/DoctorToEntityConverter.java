package cz.cvut.fit.tomanma9.tjvhospital.api.model.converter;


import cz.cvut.fit.tomanma9.tjvhospital.api.model.DoctorDto;
import cz.cvut.fit.tomanma9.tjvhospital.api.model.InnerPatientDto;
import cz.cvut.fit.tomanma9.tjvhospital.dao.PatientRepository;
import cz.cvut.fit.tomanma9.tjvhospital.domain.Doctor;
import cz.cvut.fit.tomanma9.tjvhospital.domain.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

// Functor of DoctorDto -> Doctor
// where apply is like operator() in c++
@Component
public class DoctorToEntityConverter implements Function<DoctorDto, Doctor> {

    @Autowired
    PatientRepository patientRepository;

    @Override
    public Doctor apply(DoctorDto doctorDto) {
        // doctor is owning doctor-patient relation, therefore we need to update it
        Doctor doctor = new Doctor(doctorDto.getId(), doctorDto.getName(), doctorDto.getEmailAddress(), doctorDto.getPhoneNumber());

        List<Long> patientIds = new ArrayList<>();
        if (doctorDto.getPatients() != null)
            patientIds = doctorDto.getPatients().stream()
                                    .map(InnerPatientDto::getPatient_id).toList();

        for (Long patientId : patientIds) {
            Optional<Patient> addedPatient = patientRepository.findById(patientId);
            if (addedPatient.isEmpty())
                throw new NotFoundException("Could not find patient with given ID");
            doctor.addPatient(addedPatient.get());
        }
        return doctor;
    }
}
