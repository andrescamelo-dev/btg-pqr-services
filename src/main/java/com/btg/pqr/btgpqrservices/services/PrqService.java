package com.btg.pqr.btgpqrservices.services;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import com.btg.pqr.btgpqrservices.common.ErrorTypeEnum;
import com.btg.pqr.btgpqrservices.common.ResponseCodesEnum;
import com.btg.pqr.btgpqrservices.common.TipoPqrEnum;
import com.btg.pqr.btgpqrservices.common.Utils;
import com.btg.pqr.btgpqrservices.converter.PqrConverter;
import com.btg.pqr.btgpqrservices.dto.PqrRequestDTO;
import com.btg.pqr.btgpqrservices.dto.PqrResponseDTO;
import com.btg.pqr.btgpqrservices.dto.ReclamoRequestDTO;
import com.btg.pqr.btgpqrservices.dto.ReclamoResponseDTO;
import com.btg.pqr.btgpqrservices.dto.ResponseDTO;
import com.btg.pqr.btgpqrservices.model.Pqr;
import com.btg.pqr.btgpqrservices.model.Usuario;
import com.btg.pqr.btgpqrservices.repository.PrqRepository;
import com.btg.pqr.btgpqrservices.repository.UsuarioRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("PrqService")
public class PrqService {
    
    private static final Logger LOG = (Logger) LoggerFactory.getLogger(PrqService.class);
    @Autowired
    private PrqRepository pqrRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
     
     public ResponseDTO crearPQR(Pqr prqModel) {
        ResponseDTO result = new ResponseDTO();
        try {

            Optional<Usuario> usuarioExiste = usuarioRepository.findById(prqModel.getUsuarioId());
            if (!usuarioExiste.isPresent()) {
                return Utils.errorResponse("El identificador de usuario no existe", ErrorTypeEnum.LOGICAL.getName(),
                ResponseCodesEnum.BAD_REQUEST.getCode());
            }

            // Valiadmos que los tipos de PQR sean validos
            if (TipoPqrEnum.getByCode(prqModel.getTipo()) == null) {
                String mensaje = String.format("Tipo de PQR no valido -> Validos (%s, %s, %s)" , TipoPqrEnum.PETICION.getCode(),TipoPqrEnum.QUEJA.getCode(),TipoPqrEnum.RECLAMO.getCode());
                return Utils.errorResponse(mensaje, ErrorTypeEnum.LOGICAL.getName(),
                ResponseCodesEnum.BAD_REQUEST.getCode());
            }
             prqModel.setFechaCreacion(new Date());
             pqrRepository.save(prqModel);
             result.setCode(ResponseCodesEnum.OK.getCode());
             result.setMessage(ResponseCodesEnum.OK.getDescription());
             result.setType(Utils.MESSAGE_OK);
         } catch (Exception e) {
            LOG.error(Utils.MESSAGE_GENERAL_ERROR , e);
            result = Utils.errorResponse(Utils.MESSAGE_GENERAL_ERROR+ e.getMessage(), ErrorTypeEnum.GENERAL.getName(),
            ResponseCodesEnum.INTERNAL_SERVER_ERROR.getCode());
         }
       return result;
     }
     
     public ResponseDTO crearPeticionQueja(PqrRequestDTO pqrDTO) {
        ResponseDTO result = new ResponseDTO();
        try {
             Pqr prqModel = PqrConverter.convertPeticionQuejaDtoToPrqModel(pqrDTO);
             result =  crearPQR(prqModel);
         } catch (Exception e) {
            LOG.error(Utils.MESSAGE_GENERAL_ERROR , e);
            result = Utils.errorResponse(Utils.MESSAGE_GENERAL_ERROR+ e.getMessage(), ErrorTypeEnum.GENERAL.getName(),
            ResponseCodesEnum.INTERNAL_SERVER_ERROR.getCode());
         }
       return result;
     }


     public ResponseDTO crearReclamo(ReclamoRequestDTO reclamoDTO) {
        ResponseDTO result = new ResponseDTO();
        try {

            String tipoPrq = reclamoDTO.getTipo();
            // si el tipo pqr no es un reclamo, la solicitud no es valida 
            if (!(tipoPrq.equals(TipoPqrEnum.RECLAMO.getCode()))) { 
                return Utils.errorResponse("El tipo de PQR tiene que ser de tipo RECLAMO: -> ".concat(TipoPqrEnum.RECLAMO.getCode()), ErrorTypeEnum.LOGICAL.getName(),
                ResponseCodesEnum.BAD_REQUEST.getCode());
            }

            String tipoPrqAsociado = reclamoDTO.getTipoPrqIdAsociado();
            // si el tipo pqr asociado no es una peticion o queja, la solicitud no es valida 
            if (!(tipoPrqAsociado.equals(TipoPqrEnum.PETICION.getCode()) ||  tipoPrqAsociado.equals(TipoPqrEnum.QUEJA.getCode()))) { 
                return Utils.errorResponse("El tipo de PQR asociada tiene que ser de tipo PETICION O QUEJA: -> ".concat(TipoPqrEnum.PETICION.getCode()).concat(" ").concat(TipoPqrEnum.QUEJA.getCode()), ErrorTypeEnum.LOGICAL.getName(),
                ResponseCodesEnum.BAD_REQUEST.getCode());
            }

            Optional<Pqr> pqrAsociadoBDOptional = pqrRepository.findById(reclamoDTO.getPrqIdAsociado());
            // si el id pqr asociado no exite 
            if (!pqrAsociadoBDOptional.isPresent()) {
                return Utils.errorResponse("El id de PQR asociado es no valido", ErrorTypeEnum.LOGICAL.getName(),
                ResponseCodesEnum.BAD_REQUEST.getCode());
            }else{
               Pqr pqrAsociadoBD = pqrAsociadoBDOptional.get();
               Date fechaActual = new Date();
               Integer diasDiferencia = Math.abs(Utils.retornaDiasDiferenciaDias(fechaActual, pqrAsociadoBD.getFechaCreacion()));
               // la id pqr no fue creada hace mas de NUMERO_DIAS_RECLAMO dias 
               if (diasDiferencia < Utils.NUMERO_DIAS_RECLAMO) {
                return Utils.errorResponse(String.format("El id de PQR asociado tiene que tener mas de %s dias de creado", Utils.NUMERO_DIAS_RECLAMO), ErrorTypeEnum.LOGICAL.getName(),
                ResponseCodesEnum.BAD_REQUEST.getCode());
               }
            }

             Pqr prqModel = PqrConverter.convertReclamoDtoToPrqModel(reclamoDTO);
             result = crearPQR(prqModel);
         } catch (Exception e) {
            LOG.error(Utils.MESSAGE_GENERAL_ERROR , e);
            result = Utils.errorResponse(Utils.MESSAGE_GENERAL_ERROR+ e.getMessage(), ErrorTypeEnum.GENERAL.getName(),
            ResponseCodesEnum.INTERNAL_SERVER_ERROR.getCode());
         }
       return result;
     }

    public Object consultaPRQ(){
       List<PqrResponseDTO> result = new ArrayList<>();
        try {
            List<Pqr> findAll = pqrRepository.findAll();
            findAll.forEach(x -> {
                PqrResponseDTO pqrResp = PqrResponseDTO.builder().id(x.getId().toString()).descripcion(x.getDescripcion()).fechaCreacion(x.getFechaCreacion()).respuesta(x.getRespuesta()).tipo(x.getTipo()).usuarioId(x.getUsuarioId()).build();
                result.add(pqrResp);
            });
        } catch (Exception e) {
            LOG.error(Utils.MESSAGE_GENERAL_ERROR , e);
            return Utils.errorResponse(Utils.MESSAGE_GENERAL_ERROR+ e.getMessage(), ErrorTypeEnum.GENERAL.getName(),
            ResponseCodesEnum.INTERNAL_SERVER_ERROR.getCode());
        }
        return result;
    }

    public Object consultaPRQPorId(String pqrId){
        Object result = null;
         try {
             Optional<Pqr> findByIdOptional = pqrRepository.findById(pqrId);
             if (findByIdOptional.isPresent()) {
                Pqr pqrBd = findByIdOptional.get();
                result = PqrResponseDTO.builder().id(pqrBd.getId().toString()).descripcion(pqrBd.getDescripcion()).fechaCreacion(pqrBd.getFechaCreacion()).respuesta(pqrBd.getRespuesta()).tipo(pqrBd.getTipo()).usuarioId(pqrBd.getUsuarioId()).build();
             }else{
                result = PqrResponseDTO.builder().build();
             }
         } catch (Exception e) {
             LOG.error(Utils.MESSAGE_GENERAL_ERROR , e);
             result = Utils.errorResponse(Utils.MESSAGE_GENERAL_ERROR+ e.getMessage(), ErrorTypeEnum.GENERAL.getName(),
             ResponseCodesEnum.INTERNAL_SERVER_ERROR.getCode());
         }
         return result;
     }
 
     public Object consultaReclamo(String reclamoId){
        Object result = null;
         try {
             Optional<Pqr> reclamoIdOptional = pqrRepository.findById(reclamoId);
             if (reclamoIdOptional.isPresent()) {
                Pqr pqrBd = reclamoIdOptional.get();

                if (!pqrBd.getTipo().equals(TipoPqrEnum.RECLAMO.getCode())) {
                    return  Utils.errorResponse("El id no pertenece a un PQR tipo Reclamo -> ".concat(pqrBd.getTipo()), ErrorTypeEnum.LOGICAL.getName(),
                    ResponseCodesEnum.BAD_REQUEST.getCode());
                }
                ReclamoResponseDTO reclamo = new ReclamoResponseDTO(pqrBd.getId().toString(), pqrBd.getDescripcion(), pqrBd.getTipo(), pqrBd.getUsuarioId(), pqrBd.getRespuesta(), pqrBd.getFechaCreacion());
                Optional<Pqr> pqrOrigenOptional = pqrRepository.findById(pqrBd.getReclamo().getPqrId());
                 if (pqrOrigenOptional.isPresent()) {
                    Pqr pqrOrigenBd = pqrOrigenOptional.get();
                    PqrResponseDTO pqrOrigen = PqrResponseDTO.builder().id(pqrOrigenBd.getId().toString()).descripcion(pqrOrigenBd.getDescripcion()).fechaCreacion(pqrOrigenBd.getFechaCreacion()).respuesta(pqrOrigenBd.getRespuesta()).tipo(pqrOrigenBd.getTipo()).usuarioId(pqrOrigenBd.getUsuarioId()).build();
                    reclamo.setPqrOrigen(pqrOrigen);
                    result = reclamo;
                 }
             }else{
                result = Utils.errorResponse("El id del Reclamo no existe ", ErrorTypeEnum.LOGICAL.getName(),
                ResponseCodesEnum.BAD_REQUEST.getCode());
             }
         } catch (Exception e) {
             LOG.error(Utils.MESSAGE_GENERAL_ERROR , e);
             result = Utils.errorResponse(Utils.MESSAGE_GENERAL_ERROR+ e.getMessage(), ErrorTypeEnum.GENERAL.getName(),
             ResponseCodesEnum.INTERNAL_SERVER_ERROR.getCode());
         }
         return result;
     }

}
