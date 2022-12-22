package cz.cvut.fit.tomanma9.tjvhospital.dao;

import cz.cvut.fit.tomanma9.tjvhospital.domain.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
