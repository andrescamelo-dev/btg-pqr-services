package com.btg.pqr.btgpqrservices.config;

import java.util.NoSuchElementException;

import com.btg.pqr.btgpqrservices.common.ResponseCodesEnum;
import com.btg.pqr.btgpqrservices.common.Utils;
import com.btg.pqr.btgpqrservices.dto.ResponseDTO;
import com.btg.pqr.btgpqrservices.services.PrqService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

	static final Logger LOG = LoggerFactory.getLogger(PrqService.class);

	@org.springframework.web.bind.annotation.ExceptionHandler({ IllegalArgumentException.class,
			IllegalStateException.class })
	public ResponseEntity<Object> handleMethodInternalError(Exception ex, WebRequest request) {
		ResponseDTO errors = Utils.errorResponse("Error in the transaction: [" + ex.getMessage() + "]", "InternalError",ResponseCodesEnum.INTERNAL_SERVER_ERROR.getCode());
		LOG.error("Error", ex);
		return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler({ NoSuchElementException.class,
			NullPointerException.class })
	public ResponseEntity<Object> handleMethodNoDataFound(Exception ex, WebRequest request) {
		ResponseDTO errors = Utils.errorResponse("Datos no encontrados: [" + ex.getMessage() + "]", "InternalError",ResponseCodesEnum.NOT_FOUND.getCode());
		LOG.error("Error", ex);
		return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}
}
