package com.jpacourse.persistance.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "VISIT")
public class VisitEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "visit_date")
	private LocalDateTime visitDate;

	@Column(name = "description")
	private String description;

	@ManyToOne
	@JoinColumn(name = "patient_id")
	private PatientEntity patient;

	@ManyToOne
	@JoinColumn(name = "doctor_id")
	private DoctorEntity doctor;

	@ManyToMany
	@JoinTable(
			name = "VISIT_TREATMENT",
			joinColumns = @JoinColumn(name = "visit_id"),
			inverseJoinColumns = @JoinColumn(name = "treatment_id")
	)
	private Set<MedicalTreatmentEntity> treatments = new HashSet<>();


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(LocalDateTime visitDate) {
		this.visitDate = visitDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public PatientEntity getPatient() {
		return patient;
	}

	public void setPatient(PatientEntity patient) {
		this.patient = patient;
	}

	public DoctorEntity getDoctor() {
		return doctor;
	}

	public void setDoctor(DoctorEntity doctor) {
		this.doctor = doctor;
	}

	public Set<MedicalTreatmentEntity> getTreatments() {
		return treatments;
	}

	public void setTreatments(Set<MedicalTreatmentEntity> treatments) {
		this.treatments = treatments;
	}
}