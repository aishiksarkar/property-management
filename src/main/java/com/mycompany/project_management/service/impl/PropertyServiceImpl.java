package com.mycompany.project_management.service.impl;

import com.mycompany.project_management.converter.PropertyConverter;
import com.mycompany.project_management.dto.PropertyDTO;
import com.mycompany.project_management.entity.PropertyEntity;
import com.mycompany.project_management.repository.PropertyRepository;
import com.mycompany.project_management.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//Here Controller can be used
//Its Singleton annotation as it is memory efficient
//Only 1 bean will be used through out the application
@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyConverter propertyConverter;

    @Autowired
    private PropertyRepository propertyRepository;
    @Override
    public PropertyDTO saveProperty(PropertyDTO propertyDTO) {
//        PropertyEntity pe=new PropertyEntity();
//        //Adapter Design Patterns
//        pe.setAddress(propertyDTO.getAddress());
//        pe.setDescription(propertyDTO.getDescription());
//        pe.setPrice(propertyDTO.getPrice());
//        pe.setTitle(propertyDTO.getTitle());
//        pe.setOwnerEmail(propertyDTO.getOwnerEmail());
//        pe.setOwnerName(propertyDTO.getOwnerName());
        PropertyEntity pe=propertyConverter.convertDTOEntity(propertyDTO);
        pe=propertyRepository.save(pe);
        propertyDTO=propertyConverter.convertDTOObject(pe);
        return propertyDTO;
    }

    @Override
    public List<PropertyDTO> getAllProperties()
    {
        List<PropertyEntity> propertyList= (List<PropertyEntity>) propertyRepository.findAll();
        List<PropertyDTO> propertyDTOS=new ArrayList<>();
        for(PropertyEntity pe:propertyList)
        {
            PropertyDTO propertyDTO=propertyConverter.convertDTOObject(pe);
            propertyDTOS.add(propertyDTO);
        }
        return  propertyDTOS;
    }

    @Override
    public PropertyDTO updateProperty(PropertyDTO propertyDTO,Long propertyId)
    {
        Optional<PropertyEntity> propertyById=propertyRepository.findById(propertyId);
        if(propertyById.isPresent())
        {
            PropertyEntity pe=propertyById.get();
            pe.setAddress(propertyDTO.getAddress());
            pe.setDescription(propertyDTO.getDescription());
            pe.setPrice(propertyDTO.getPrice());
            pe.setTitle(propertyDTO.getTitle());

            propertyRepository.save(pe);
            propertyDTO=propertyConverter.convertDTOObject(pe);
            return propertyDTO;
        }
        return null;
    }

    public PropertyDTO deleteProperty(Long propertyId)
    {
        Optional<PropertyEntity> propertyEntity= propertyRepository.findById(propertyId);
        if(propertyEntity.isPresent()) {
            PropertyDTO propertyDTO=propertyConverter.convertDTOObject(propertyEntity.get());
            propertyRepository.deleteById(propertyId);

            return  propertyDTO;
        }

        return null;
    }

    public PropertyDTO updatePropertyPrice(Long propertyId,Double price)
    {
        PropertyDTO propertyDTO=null;
        Optional<PropertyEntity> pe=propertyRepository.findById(propertyId);
        if(pe.isPresent())
        {
            PropertyEntity propertyEntity=pe.get();
            propertyEntity.setPrice(price);
            propertyRepository.save(propertyEntity);
            propertyDTO=propertyConverter.convertDTOObject(propertyEntity);
            return propertyDTO;
        }
        return propertyDTO;
    }

    public PropertyDTO updatePropertyDescription(Long propertyId,String desc)
    {
        PropertyDTO propertyDTO=null;
        Optional<PropertyEntity> pe=propertyRepository.findById(propertyId);
        if(pe.isPresent())
        {
            PropertyEntity propertyEntity=pe.get();
            propertyEntity.setDescription(desc);
            propertyDTO=propertyConverter.convertDTOObject(propertyEntity);
            return propertyDTO;
        }
        return propertyDTO;
    }
}
