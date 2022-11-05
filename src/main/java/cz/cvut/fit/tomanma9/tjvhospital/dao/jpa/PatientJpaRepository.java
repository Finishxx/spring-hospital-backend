package cz.cvut.fit.tomanma9.tjvhospital.dao.jpa;

import cz.cvut.fit.tomanma9.tjvhospital.dao.PatientRepository;
import cz.cvut.fit.tomanma9.tjvhospital.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientJpaRepository extends PatientRepository, JpaRepository<Patient, Long> {
}
