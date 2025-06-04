package com.jpacourse.service.impl;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.mapper.PatientMapper;
import com.jpacourse.persistance.dao.PatientDao;
import com.jpacourse.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class PatientServiceImpl implements PatientService
{
    private final PatientDao patientDao;

    @Autowired
    public PatientServiceImpl(PatientDao patientDao1)
    {
        patientDao = patientDao1;
    }


    @Override
    public PatientTO findById(Long id) {
        return PatientMapper.mapToTO(patientDao.findOne(id));
    }

    @Override
    public void deletePatient(long id) {
        patientDao.delete(id);
    }
}
