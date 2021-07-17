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
public enum ResponseCodesEnum
{
    OK("01","200","Respuesta existosa"),
    BAD_REQUEST("02","400","Bad Request"),
    UNAUTHORIZED("03","401","Unauthorized  Request"),
    FORBIDDEN("04","403","Forbidden Request"),
    NOT_FOUND("05","404","Not found"),
    INTERNAL_SERVER_ERROR("07","500","Internal server error");

    private String code;
    private String description;
    private String codeErrorHttp;

    ResponseCodesEnum(String code, String codeErrorHttp ,String description)
    {
        this.code          = code;
        this.codeErrorHttp = codeErrorHttp;
        this.description   = description;
    }
}
