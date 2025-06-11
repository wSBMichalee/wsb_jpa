package com.jpacourse.persistance.dao;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.persistance.dao.impl.PatientDaoImpl;
import com.jpacourse.persistance.entity.DoctorEntity;
import com.jpacourse.persistance.entity.PatientEntity;
import com.jpacourse.persistance.entity.VisitEntity;
import com.jpacourse.service.PatientService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

    @Transactional
    @Test
    void testAddVisitToPatient() {
        Long patientId = 1L;
        Long doctorId = 2L;
        LocalDateTime date = LocalDateTime.of(2025, 7, 1, 10, 0);
        String description = "Badanie EKG";


        patientDao.addVisitToPatient(patientId, doctorId, date, description);


        List<VisitEntity> visits = patientDao.findVisitsByPatientId(patientId);

        assertFalse(visits.isEmpty());
        VisitEntity lastVisit = visits.get(visits.size() - 1);

        assertEquals(patientId, lastVisit.getPatient().getId());
        assertEquals(doctorId, lastVisit.getDoctor().getId());
        assertEquals(date, lastVisit.getVisitDate());
        assertEquals(description, lastVisit.getDescription());
    }
}