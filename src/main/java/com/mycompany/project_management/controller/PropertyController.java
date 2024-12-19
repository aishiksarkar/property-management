package com.mycompany.project_management.controller;

import com.mycompany.project_management.converter.PropertyConverter;
import com.mycompany.project_management.dto.PropertyDTO;
import com.mycompany.project_management.entity.PropertyEntity;
import com.mycompany.project_management.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1")
public class PropertyController {

    @Value("${pms.dummy:}")
    private String dummy;//Colon just makes sure if that property is not present there will be no error

    @Autowired
    private PropertyConverter propertyConverter;

    @Autowired //Dependency Injection so that Interface is used by an object that the object is pointing to
    //The Place we are doing autowiring must be a bean Configuration Controller or any other thing but must be a bean
    //If Not AutoWired then the object will be null
    //After Autowiring only the bean will be created and injected
    private PropertyService propertyService; //Interface // Now after Autowiring the PropertyService Object will be created and bean instance as singlteon will be created

    //RESTFUL API is just mapping of a uri to a java class function
    @GetMapping("/hello")
    public  String sayHello()
    {
        return "Hello";
    }

    @PostMapping("/properties")
    public ResponseEntity<PropertyDTO> saveProperty(@RequestBody PropertyDTO propertyDTO)
    {
        PropertyDTO propertyDTO1=propertyService.saveProperty(propertyDTO);
        System.out.println(propertyDTO1);
        return new ResponseEntity<PropertyDTO>(propertyDTO1,CREATED);
    }
    @GetMapping("/properties/all")
    public ResponseEntity<List<PropertyDTO>> getAllProperties()
    {
        System.out.println("Value of dummy is "+dummy);
        List<PropertyDTO> propertyEntityList=propertyService.getAllProperties();
       ResponseEntity<List<PropertyDTO>> propertyListAsOutput=new ResponseEntity<>(propertyEntityList,HttpStatus.FOUND);
       return propertyListAsOutput;
    }

    @PutMapping("/properties")
    public ResponseEntity<PropertyDTO> updateProperty(@RequestBody PropertyDTO propertyDTO,@RequestParam("Id") Long propertyId)
    {
         propertyDTO=propertyService.updateProperty(propertyDTO,propertyId);
         if(propertyDTO==null)
             return new ResponseEntity<PropertyDTO>(propertyDTO,HttpStatus.NOT_FOUND);
        ResponseEntity<PropertyDTO> updatedProperty=new ResponseEntity<PropertyDTO>(propertyDTO,HttpStatus.FOUND);
        return updatedProperty;
    }

    @DeleteMapping("/properties")
    public ResponseEntity<PropertyDTO> deleteProperty(@RequestParam("Id")Long propertyId)
    {
         PropertyDTO propertyDTO=propertyService.deleteProperty(propertyId);
        ResponseEntity deletedItem=new ResponseEntity<>(propertyDTO, HttpStatusCode.valueOf(204));
        ResponseEntity notFound=new ResponseEntity<>(propertyDTO, HttpStatusCode.valueOf(404));
        if(propertyDTO==null)
            return notFound;
        else
            return  deletedItem;

    }

    @PatchMapping("/properties/update/desc")
    public ResponseEntity<PropertyDTO> updatePropertyDescription(@RequestBody PropertyDTO propertyDTO,@RequestParam("Id")Long propertyId)
    {
        String desc=propertyDTO.getDescription();
        propertyDTO=propertyService.updatePropertyDescription(propertyId,desc);
        if(propertyDTO==null)
        {

        }
        return new ResponseEntity<>(propertyDTO,HttpStatusCode.valueOf(200));
    }

    @PatchMapping("/properties/update/price")
    public ResponseEntity<PropertyDTO> updatePropertyPrice(@RequestBody PropertyDTO propertyDTO,@RequestParam("Id")Long propertyId)
    {
        Double price=propertyDTO.getPrice();
        propertyDTO=propertyService.updatePropertyPrice(propertyId,price);
        if(propertyDTO==null)
        {

        }
        return new ResponseEntity<>(propertyDTO,HttpStatusCode.valueOf(200));

    }

}
