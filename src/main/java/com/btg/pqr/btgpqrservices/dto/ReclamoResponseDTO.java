package com.btg.pqr.btgpqrservices.dto;

import java.util.Date;

public class ReclamoResponseDTO extends PqrResponseDTO {
    private PqrResponseDTO pqrOrigen;

    public ReclamoResponseDTO(String id, String descripcion, String tipo, String usuarioId, String respuesta,
            Date fechaCreacion) {
        super(id, descripcion, tipo, usuarioId, respuesta, fechaCreacion);
    }

    public PqrResponseDTO getPqrOrigen() {
        return pqrOrigen;
    }

    public void setPqrOrigen(PqrResponseDTO pqrOrigen) {
        this.pqrOrigen = pqrOrigen;
    }

    
}
