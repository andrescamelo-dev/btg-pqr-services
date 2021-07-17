package com.btg.pqr.btgpqrservices.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
@Document("reclamo")
public class Reclamo {
 
    @Field("pqr_id")
    private String pqrId;

}
