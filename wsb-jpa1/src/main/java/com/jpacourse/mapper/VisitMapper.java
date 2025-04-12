package com.jpacourse.mapper;

import com.jpacourse.dto.VisitTO;
import com.jpacourse.persistance.entity.MedicalTreatmentEntity;
import com.jpacourse.persistance.entity.VisitEntity;

import java.util.List;
import java.util.stream.Collectors;

public class VisitMapper {

    public static VisitTO mapToTO(final VisitEntity visitEntity) {
        if (visitEntity == null) {
            return null;
        }

        final VisitTO visitTO = new VisitTO();
        visitTO.setId(visitEntity.getId());
        visitTO.setVisitDate(visitEntity.getVisitDate());
        visitTO.setDescription(visitEntity.getDescription());

        if (visitEntity.getDoctor() != null) {
            visitTO.setDoctorFirstName(visitEntity.getDoctor().getFirstName());
            visitTO.setDoctorLastName(visitEntity.getDoctor().getLastName());
        }

        if (visitEntity.getTreatments() != null) {
            List<String> treatmentTypes = visitEntity.getTreatments().stream()
                    .map(MedicalTreatmentEntity::getTreatmentType)
                    .collect(Collectors.toList());
            visitTO.setTreatmentTypes(treatmentTypes);
        }

        return visitTO;
    }
}