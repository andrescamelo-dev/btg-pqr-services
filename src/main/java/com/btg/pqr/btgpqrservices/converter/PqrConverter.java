package com.btg.pqr.btgpqrservices.converter;

import com.btg.pqr.btgpqrservices.dto.PqrRequestDTO;
import com.btg.pqr.btgpqrservices.dto.ReclamoRequestDTO;
import com.btg.pqr.btgpqrservices.model.Pqr;
import com.btg.pqr.btgpqrservices.model.Reclamo;

public class PqrConverter {
    

   public static Pqr convertPeticionQuejaDtoToPrqModel(PqrRequestDTO pqrDTO){
     return Pqr.builder().descripcion(pqrDTO.getDescripcion()).tipo(pqrDTO.getTipo()).usuarioId(pqrDTO.getUsuarioId()).build();
   }

   public static Pqr convertReclamoDtoToPrqModel(ReclamoRequestDTO pqrDTO){
       Reclamo reclamo = Reclamo.builder().pqrId(pqrDTO.getPrqIdAsociado()).build();
    return Pqr.builder().descripcion(pqrDTO.getDescripcion()).tipo(pqrDTO.getTipo()).usuarioId(pqrDTO.getUsuarioId()).reclamo(reclamo).build();
  }
}
