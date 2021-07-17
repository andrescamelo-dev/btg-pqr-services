package com.btg.pqr.btgpqrservices.common;

import lombok.Getter;

/**
 * <br>ErrorsCodeEnum.<br/>
 *
 * @author ACM
 * @since 17/07/2021
 * Historia de Modificaciones
 * ---------------------------
 * Autor              Fecha          Modificacion
 * ACM                17/07/2021      Creaci\u00f3n
 * -----------------  -------------- ----------------------------
 */
@Getter
public enum ErrorTypeEnum
{
    LOGICAL("LogicalError"),
    GENERAL("GeneralError");
    
    private String name;
    

    ErrorTypeEnum(String name)
    {
        this.name = name;
       
    }


    public void setName(String name) {
        this.name = name;
    }
}
