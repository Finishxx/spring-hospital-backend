package cz.cvut.fit.tomanma9.tjvhospital.api.model.converter;


import cz.cvut.fit.tomanma9.tjvhospital.api.model.DoctorDto;
import cz.cvut.fit.tomanma9.tjvhospital.domain.Doctor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

// Functor of DoctorDto -> Doctor
// where apply is like operator() in c++
@Component
public class DoctorToEntityConverter implements Function<DoctorDto, Doctor> {

    @Override
    public Doctor apply(DoctorDto doctorDto) {
        return new Doctor(doctorDto.getId(), doctorDto.getName(), doctorDto.getEmailAddress(), doctorDto.getPhoneNumber());
    }
}
