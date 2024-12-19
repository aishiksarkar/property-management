package com.mycompany.project_management.converter;

import com.mycompany.project_management.dto.PropertyDTO;
import com.mycompany.project_management.entity.PropertyEntity;
import org.springframework.stereotype.Component;

@Component
public class PropertyConverter {
    //DTO to Entity
    public PropertyEntity convertDTOEntity(PropertyDTO propertyDTO) {
        PropertyEntity pe=new PropertyEntity();
        //Adapter Design Patterns
        pe.setAddress(propertyDTO.getAddress());
        pe.setDescription(propertyDTO.getDescription());
        pe.setPrice(propertyDTO.getPrice());
        pe.setTitle(propertyDTO.getTitle());
        pe.setOwnerEmail(propertyDTO.getOwnerEmail());
        pe.setOwnerName(propertyDTO.getOwnerName());
        pe.setDate(propertyDTO.getDate());
        return pe;
    }

    public PropertyDTO convertDTOObject(PropertyEntity pe) {
       PropertyDTO propertyDTO=new PropertyDTO();
       propertyDTO.setId(pe.getId());
        propertyDTO.setAddress(pe.getAddress());
        propertyDTO.setDescription(pe.getDescription());
        propertyDTO.setPrice(pe.getPrice());
        propertyDTO.setTitle(pe.getTitle());
        propertyDTO.setOwnerEmail(pe.getOwnerEmail());
        propertyDTO.setOwnerName(pe.getOwnerName());
        propertyDTO.setDate(pe.getDate());
        return propertyDTO;
    }
}
