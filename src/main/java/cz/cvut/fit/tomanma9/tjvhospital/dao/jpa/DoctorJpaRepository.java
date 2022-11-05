package cz.cvut.fit.tomanma9.tjvhospital.dao.jpa;

import cz.cvut.fit.tomanma9.tjvhospital.dao.DoctorRepository;
import cz.cvut.fit.tomanma9.tjvhospital.domain.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorJpaRepository extends DoctorRepository, JpaRepository<Doctor, Long> {
}
