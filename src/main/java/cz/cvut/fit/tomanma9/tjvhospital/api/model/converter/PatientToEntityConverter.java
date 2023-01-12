package cz.cvut.fit.tomanma9.tjvhospital.api.model.converter;


import cz.cvut.fit.tomanma9.tjvhospital.api.model.PatientDto;
import cz.cvut.fit.tomanma9.tjvhospital.domain.Patient;
import org.springframework.stereotype.Component;

import java.util.function.Function;

// Functor of PatientDto -> Patient
// where apply is like operator() in c++
@Component
public class PatientToEntityConverter implements Function<PatientDto, Patient> {
    @Override
    public Patient apply(PatientDto dto) {
        // patient is not owning any relation
        return new Patient(dto.getId(), dto.getName(), dto.getBirthdate(), dto.getEmailAddress(), dto.getPhoneNumber());

    }
}
