package com.mycompany.project_management.service;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.mycompany.project_management.dto.PropertyDTO;
import com.mycompany.project_management.entity.PropertyEntity;

import java.util.List;

public interface PropertyService {
    PropertyDTO saveProperty(PropertyDTO propertyDTO);

    List<PropertyDTO> getAllProperties();

    PropertyDTO updateProperty(PropertyDTO propertyDTO,Long propertyId);

    PropertyDTO deleteProperty(Long propertyId);

    PropertyDTO updatePropertyPrice(Long propertyId,Double price);
    PropertyDTO updatePropertyDescription(Long propertyId,String description);
}
