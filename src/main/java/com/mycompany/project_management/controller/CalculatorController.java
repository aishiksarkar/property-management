package com.mycompany.project_management.controller;

import com.mycompany.project_management.dto.CalculatorDTO;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/calculator")//class level Mapping of a url to a controller class
public class CalculatorController {

    @GetMapping("/add/{num3}")//Method level mapping of a url to a controller function
    //http://localhost:8080/api/v1/calculator/add?num11=6.7&num222=1.3
    public Double add(@RequestParam("num1") Double num1, @RequestParam("num2") Double num2,@PathVariable("num3") Double num3) {
        return num1 + num2+num3;
    }

    @GetMapping("/sub/{num1}/{num2}")//Map the values of url to Java Variables by Path Variables method
    public Double subtract(@PathVariable("num1") Double num1, @PathVariable("num2") Double num2)
    {
        Double result=null;
        if(num1>num2)
        {
            result = num1-num2;
        }
        else {
            result = num2-num1;
        }
        return result;
    }

    @PostMapping("/mul")
    public ResponseEntity<CalculatorDTO> multiply(@RequestBody CalculatorDTO calculatorDTO)
    {
        Double result=calculatorDTO.getNum1()*calculatorDTO.getNum2()*calculatorDTO.getNum3()*calculatorDTO.getNum4();
        ResponseEntity<CalculatorDTO> responseEntity=new ResponseEntity<CalculatorDTO>(calculatorDTO, HttpStatus.CREATED);
        return responseEntity;
    }

}
