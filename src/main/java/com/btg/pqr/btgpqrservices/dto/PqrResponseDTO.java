package com.btg.pqr.btgpqrservices.dto;

import java.util.Date;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class PqrResponseDTO {
    private String id;
    private String descripcion;
    private String tipo;
    private String usuarioId;
    private String respuesta;
    private Date fechaCreacion;
}
