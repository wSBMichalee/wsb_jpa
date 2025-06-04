package com.jpacourse.persistance.dao;

import com.jpacourse.persistance.entity.PatientEntity;
import com.jpacourse.persistance.entity.VisitEntity;
import com.jpacourse.service.PatientService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PatientDaoImplTest {

    @Autowired
    private PatientDao patientDao;
    @Transactional
    @Test
    void testFindByLastName() {
        List<PatientEntity> patients = patientDao.findByLastName("Kowalski");
        assertFalse(patients.isEmpty());
        assertEquals("Kowalski", patients.get(0).getLastName());
    }
    @Transactional
    @Test
    void testFindByHeightGreaterThan() {
        List<PatientEntity> tallPatients = patientDao.findByHeightGreaterThan(170);
        assertFalse(tallPatients.isEmpty());
        assertTrue(tallPatients.get(0).getHeight() > 170);
    }
    @Transactional
    @Test
    void testFindPatientsWithMoreThanXVisits() {
        List<PatientEntity> patients = patientDao.findPatientsWithMoreThanXVisits(1);
        assertFalse(patients.isEmpty());
    }


    @Transactional
    @Test
    void testGetVisitsByPatientId() {
        List<VisitEntity> visits = patientDao.findVisitsByPatientId(1L);
        assertNotNull(visits);
        assertTrue(visits.size() > 0);
    }
}