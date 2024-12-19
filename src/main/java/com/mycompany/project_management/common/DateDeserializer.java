package com.mycompany.project_management.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.format.datetime.DateFormatter;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class DateDeserializer extends JsonDeserializer<Date> {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-d'T'HH:mm:ss[.SSS][XXX]");
    private static final DateFormatter dateFormar1=new DateFormatter("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {

        String date = jsonParser.getText();
        System.out.println("We get  "+ date);
        boolean flag=false;
        Date ans_date=jsonParser.readValueAs(Date.class);

        if(flag==false)
        {
            ObjectMapper objectMapper=new ObjectMapper();
            Date dateToBeReturned= objectMapper.convertValue(date,Date.class);
            System.out.println("Date is"+ dateToBeReturned);
            System.out.println("Date 2 is"+ ans_date);
//            return dateToBeReturned;
            return ans_date;
        }
        Date ans=null;
        try {
            if(date.contains("Z")||date.contains("+"))
            {
                OffsetDateTime offsetDateTime=OffsetDateTime.parse(date);
                return Date.from(offsetDateTime.toInstant());
            }
            else
            {
                LocalDateTime localDateTime=LocalDateTime.parse(date, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                return Date.from(localDateTime.toInstant(ZoneOffset.UTC));
            }
        } catch (DateTimeParseException e) {
            throw new RuntimeException(e);
        }

//        try {
//            ans=dateFormat.parse(date);
//        } catch (ParseException e) {
//            throw new RuntimeException(e);
//        }
//        return ans;
    }
}
