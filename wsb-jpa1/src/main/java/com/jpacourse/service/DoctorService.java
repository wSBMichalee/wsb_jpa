package com.jpacourse.service;

import com.jpacourse.dto.DoctorTO;
import com.jpacourse.dto.PatientTO;
import com.jpacourse.persistance.entity.DoctorEntity;

public interface DoctorService {

    DoctorTO findById(final Long id);
    void deletePatient (long id);
}


