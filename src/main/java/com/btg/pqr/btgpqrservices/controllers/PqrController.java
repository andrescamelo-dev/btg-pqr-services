package com.btg.pqr.btgpqrservices.controllers;

import com.btg.pqr.btgpqrservices.BtgPqrServicesApplication;
import com.btg.pqr.btgpqrservices.dto.PqrRequestDTO;
import com.btg.pqr.btgpqrservices.dto.ReclamoRequestDTO;
import com.btg.pqr.btgpqrservices.dto.ResponseDTO;
import com.btg.pqr.btgpqrservices.services.PrqService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(BtgPqrServicesApplication.PATH_SERVICE)
public class PqrController {
    @Autowired
    PrqService prqService;

    @ApiOperation(value = "Operacion que crea una peticion o queja", response = ResponseDTO.class)
    @PostMapping(value = "/crearPeticionQueja", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ResponseDTO crearPeticionQueja(@RequestBody PqrRequestDTO pqrDTO)
    {
      return prqService.crearPeticionQueja(pqrDTO);
    }

    @ApiOperation(value = "Operacion que crea un reclamo", response = ResponseDTO.class)
    @PostMapping(value = "/crearReclamo", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ResponseDTO crearReclamo(@RequestBody ReclamoRequestDTO pqrDTO)
    {
      return prqService.crearReclamo(pqrDTO);
    }

    @ApiOperation(value = "Operacion que consulta todas las PQRs", response = ResponseEntity.class)
    @GetMapping(value = "/consultaPQR", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public Object consultaPQR()
    {
      return prqService.consultaPRQ();
    }

    @ApiOperation(value = "Operacion que consulta un PQR por identificador", response = ResponseEntity.class)
    @GetMapping(value = "/consultaPQR/{pqrId}", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public Object consultaPQR(@PathVariable("pqrId") String pqrId)
    {
      return prqService.consultaPRQPorId(pqrId);
    }

    @ApiOperation(value = "Operacion que consulta un reclamo por identificador", response = ResponseEntity.class)
    @GetMapping(value = "/consultaReclamo/{pqrId}", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public Object consultaReclamo(@PathVariable("pqrId") String pqrId)
    {
      return prqService.consultaReclamo(pqrId);
    }
}
