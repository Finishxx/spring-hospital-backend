package cz.cvut.fit.tomanma9.tjvhospital.dao;

import cz.cvut.fit.tomanma9.tjvhospital.domain.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    // find all doctors, who do not have at least "lazy" appointments scheduled
    @Query("select d from doctor d where d.appointments.size <= :lazy")
    Collection<Doctor> findAllByAppointmentsExists(@Param("lazy") int lazy);

}
