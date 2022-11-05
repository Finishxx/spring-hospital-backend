package cz.cvut.fit.tomanma9.tjvhospital.dao;

import cz.cvut.fit.tomanma9.tjvhospital.domain.Appointment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface AppointmentRepository extends CrudRepository<Appointment, Long> {
}
