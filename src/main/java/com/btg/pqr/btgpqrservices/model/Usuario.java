package com.btg.pqr.btgpqrservices.model;

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
@Document("usuario")
public class Usuario
{
    @Id
    ObjectId id;

    @Field("identificacion")
    private String identificacion;
   
    @Field("nombre")
    private String nombre;

    @Field("apellido")
    private String apellido;


}
