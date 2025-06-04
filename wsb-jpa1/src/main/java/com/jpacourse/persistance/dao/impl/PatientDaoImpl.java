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
    public void addVisitToPatient( Long patientId,  Long doctorId, LocalDateTime visitDate, String description) {

        PatientEntity patient = findOne(patientId);
        DoctorEntity doctor = doctorDao.findOne(doctorId);


        VisitEntity visitEntity = new VisitEntity();
        visitEntity.setPatient(patient);
        visitEntity.setDoctor(doctor);
        visitEntity.setDescription(description);
        visitEntity.setVisitDate(visitDate);


        patient.getVisits().add(visitEntity);
        doctor.getVisits().add(visitEntity);
        entityManager.merge(patient);

    }
    public List<PatientEntity> findbyLastName (String pLastName){
        return entityManager.createQuery("select pat from PatientEntity pat" +
                " where pat.lastName like :param1",PatientEntity.class).setParameter("param1","%"+pLastName+"%").getResultList();

    }

    public List<VisitEntity> findPatientbyId (Long id){
        return entityManager.createQuery("select vis from VisitEntity vis" +
                " where vis.patient =:param1",VisitEntity.class).setParameter("param1", id).getResultList();
    }

    public List<PatientEntity> findByMoreVisits(Long visNumber){
        return entityManager.createQuery("select pat from PatientEntity pat" +
                " join pat.visitEntityCollection vis group by vis having count(vis)>:param1" ,PatientEntity.class).setParameter("param1",visNumber).getResultList();
    }

    public List<PatientEntity> findByOlderThan(long age){
        return entityManager.createQuery("select pat from PatientEntity pat" +
                " where pat.age>:param1",PatientEntity.class).setParameter("param1",age).getResultList();
    }

}
