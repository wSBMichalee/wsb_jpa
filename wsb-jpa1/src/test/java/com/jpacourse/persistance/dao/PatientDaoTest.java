package com.jpacourse.persistance.dao;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.persistance.entity.PatientEntity;
import com.jpacourse.persistance.entity.VisitEntity;
import com.jpacourse.service.PatientService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PatientDaoTest {

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

    @Autowired
    private PatientService patientService;
    @Transactional
    @Test
    public void whenFindById_existingId_thenReturnPatientTO() {
        Long existingId = 1L;

        PatientTO patient = patientService.findById(existingId);

        assertNotNull(patient, "Pacjent nie powinien być null");
        assertEquals("Jan", patient.getFirstName());
        assertEquals("Kowalski", patient.getLastName());
        assertEquals("P001", patient.getPatientNumber());
        assertEquals("+48 123 456 789", patient.getTelephoneNumber());
        assertEquals(LocalDate.of(1980, 1, 1), patient.getDateOfBirth());
        assertEquals(145, patient.getHeight());
    }
}