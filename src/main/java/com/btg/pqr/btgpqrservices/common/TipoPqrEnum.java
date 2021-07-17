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
public enum TipoPqrEnum
{
    PETICION("PET"),
    QUEJA("QUE"),
    RECLAMO("REC");

    private String code;
    TipoPqrEnum(String code)
    {
        this.code  = code;
    }

    public static TipoPqrEnum getByCode(String text) {
        for (TipoPqrEnum b : TipoPqrEnum.values()) {
            if (b.getCode().equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}
