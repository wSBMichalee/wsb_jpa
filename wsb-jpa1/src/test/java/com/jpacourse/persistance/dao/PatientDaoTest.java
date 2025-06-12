package com.jpacourse.persistance.dao;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.persistance.entity.PatientEntity;
import com.jpacourse.persistance.entity.VisitEntity;
import com.jpacourse.service.PatientService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
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


    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Test
    public void testAddVisitToPatient() {
        entityManager.createQuery("DELETE FROM VisitEntity").executeUpdate();
        Long patientId = 1L;
        Long doctorId = 2L;
        LocalDateTime visitDate = LocalDateTime.of(2023, 7, 1, 10, 0);
        String description = "Testowa wizyta";


        patientDao.addVisitToPatient(patientId, doctorId, visitDate, description);

        entityManager.flush();
        entityManager.clear();

        PatientEntity patient = entityManager.find(PatientEntity.class, patientId);
        assertNotNull(patient, "Pacjent powinien istnieć");

        boolean visitFound = patient.getVisits().stream()
                .anyMatch(v -> visitDate.equals(v.getVisitDate()) && description.equals(v.getDescription()));

        assertTrue(visitFound, "Nowa wizyta została poprawnie dodana");


        VisitEntity visit = patient.getVisits().stream()
                .filter(v -> visitDate.equals(v.getVisitDate()) && description.equals(v.getDescription()))
                .findFirst()
                .orElse(null);

        assertNotNull(visit, "Wizyta istnieje");
        assertEquals(patientId, visit.getPatient().getId());
        assertEquals(doctorId, visit.getDoctor().getId());
    }
    //Podczas implementacji testu dodawania wizyty addVisitToPatient napotkaliśmy błąd naruszenia ograniczenia klucza głównego.
    // Problem wynikał z błędnej konfiguracji generowania @Id w encji VisitEntity oraz wielokrotnego wstawiania tych samych danych w testach bez czyszczenia bazy.
    // Rozwiązaniem było poprawne oznaczenie id jako generowanego oraz zapewnienie czystości danych testowych.
}