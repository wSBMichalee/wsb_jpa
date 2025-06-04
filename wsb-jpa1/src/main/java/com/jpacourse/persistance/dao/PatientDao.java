package com.jpacourse.persistance.dao;

import com.jpacourse.persistance.entity.PatientEntity;
import com.jpacourse.persistance.entity.VisitEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface PatientDao extends Dao<PatientEntity, Long> {
    void addVisitToPatient(Long patientId, Long doctorId, LocalDateTime date,String describtion);

    List<PatientEntity> findByLastName(String lastName);


    List<PatientEntity> findPatientsWithMoreThanXVisits(long count);

    List<PatientEntity> findByHeightGreaterThan(int height);

    List<VisitEntity> findVisitsByPatientId(long l);
}
