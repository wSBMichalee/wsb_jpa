package com.jpacourse.mapper;

import com.jpacourse.dto.MedicalTreatmentTO;
import com.jpacourse.persistance.entity.MedicalTreatmentEntity;

public final class MedicalTreatmentMapper {
    public static MedicalTreatmentTO mapToTO(final MedicalTreatmentEntity medicalTreatmentEntity) {
        if (medicalTreatmentEntity == null) {
            return null;
        }
        final MedicalTreatmentTO medicalTreatmentTO = new MedicalTreatmentTO();
        medicalTreatmentTO.setId(medicalTreatmentEntity.getId());
        medicalTreatmentTO.setDescription(medicalTreatmentEntity.getDescription());
        medicalTreatmentTO.setType(medicalTreatmentEntity.getType());
        medicalTreatmentTO.setVisitTO(medicalTreatmentTO.getVisitTO());
        return medicalTreatmentTO;
    }

}

