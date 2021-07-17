package com.btg.pqr.btgpqrservices.common;



import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import com.btg.pqr.btgpqrservices.dto.ResponseDTO;

public class Utils {
    public static String MESSAGE_GENERAL_ERROR = "General Error ";
    public static String MESSAGE_OK = "OK";
    public static int NUMERO_DIAS_RECLAMO = 5;

    
    public static ResponseDTO errorResponse(String message, String typeError,String code){
        ResponseDTO errors = new ResponseDTO();
        errors.setMessage(message);
        errors.setCode(code);
        errors.setType(typeError);
        return errors;
    }


    public static int retornaDiasDiferenciaDias(Date fecha1,Date fecha2) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
        String fechaInicioCadena = sdf.format(fecha1);
        String fechaFinCadena = sdf.format(fecha2);
        Date firstDate = sdf.parse(fechaInicioCadena) ;
        Date secondDate = sdf.parse(fechaFinCadena);
        long diff = secondDate.getTime() - firstDate.getTime();
        TimeUnit time = TimeUnit.DAYS; 
        Long diffrence = time.convert(diff, TimeUnit.MILLISECONDS);
        return diffrence.intValue();
    }

}
