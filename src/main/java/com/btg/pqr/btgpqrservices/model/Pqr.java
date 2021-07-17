package com.btg.pqr.btgpqrservices.model;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Builder;
import lombok.Data;

/**
 * Pqr <br>
 *
 * @author ACM
 *
 * @date 16/07/2021
 * @version 1.0
 */
@Builder
@Data
@Document("prq")
public class Pqr
{
    @Id
    ObjectId id;

    @Field("descripcion")
    private String descripcion;
   
    @Field("tipo_prq")
    private String tipo;

    @Field("usuario_id")
    private String usuarioId;

    @Field("respuesta")
    private String respuesta;

    @Field("reclamo")
    private Reclamo reclamo;


    @Field("fecha_creacion")
    private Date fechaCreacion;
   

}
