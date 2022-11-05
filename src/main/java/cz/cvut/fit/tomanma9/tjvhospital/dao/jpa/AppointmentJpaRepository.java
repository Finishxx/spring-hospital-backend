package cz.cvut.fit.tomanma9.tjvhospital.dao.jpa;

import cz.cvut.fit.tomanma9.tjvhospital.dao.AppointmentRepository;
import cz.cvut.fit.tomanma9.tjvhospital.domain.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentJpaRepository extends AppointmentRepository, JpaRepository<Appointment, Long> {
}
