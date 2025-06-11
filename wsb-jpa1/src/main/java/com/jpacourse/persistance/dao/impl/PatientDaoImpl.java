package com.jpacourse.persistance.dao.impl;

import com.jpacourse.persistance.dao.DoctorDao;
import com.jpacourse.persistance.dao.PatientDao;
import com.jpacourse.persistance.entity.DoctorEntity;
import com.jpacourse.persistance.entity.PatientEntity;
import com.jpacourse.persistance.entity.VisitEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class PatientDaoImpl extends AbstractDao <PatientEntity,Long> implements PatientDao {

    @Autowired
    private DoctorDao doctorDao;

    @Override
    public void addVisitToPatient(Long patientId, Long doctorId, LocalDateTime date, String description) {
        PatientEntity patient = entityManager.find(PatientEntity.class, patientId);
        DoctorEntity doctor = entityManager.find(DoctorEntity.class, doctorId);

        VisitEntity visit = new VisitEntity();
        visit.setPatient(patient);
        visit.setDoctor(doctor);
        visit.setVisitDate(date);
        visit.setDescription(description);

        entityManager.persist(visit);
    }


    @Override
    public List<PatientEntity> findByLastName(String lastName) {
        return entityManager.createQuery(
                        "SELECT p FROM PatientEntity p WHERE p.lastName = :lastName", PatientEntity.class)
                .setParameter("lastName", lastName)
                .getResultList();
    }



    @Override
    public List<PatientEntity> findPatientsWithMoreThanXVisits(long count) {
        return entityManager.createQuery(
                        "SELECT p FROM PatientEntity p WHERE SIZE(p.visits) > :count", PatientEntity.class)
                .setParameter("count", (int) count)
                .getResultList();
    }

    @Override
    public List<PatientEntity> findByHeightGreaterThan(int height) {
        return entityManager.createQuery(
                        "SELECT p FROM PatientEntity p WHERE p.height > :height", PatientEntity.class)
                .setParameter("height", height)
                .getResultList();
    }

    @Override
    public List<VisitEntity> findVisitsByPatientId(long l) {
        return entityManager.createQuery(
                        "SELECT v FROM VisitEntity v WHERE v.patient.id = :id", VisitEntity.class)
                .setParameter("id", l)
                .getResultList();
    }

}
