package cz.cvut.fit.tomanma9.tjvhospital.dao;

import cz.cvut.fit.tomanma9.tjvhospital.domain.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends CrudRepository<Patient, Long> {
}
