package com.api.zorvanz.infra.errors;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler ( IllegalArgumentException.class )
    public ResponseEntity tratarError400 ( IllegalArgumentException e ) {
        return ResponseEntity.badRequest ().body ( e.getMessage () );
    }

    @ExceptionHandler ( Exception.class )
    public ResponseEntity tratarError500 ( Exception e ) {
        e.printStackTrace ();
        return ResponseEntity.internalServerError ().body ( e.getLocalizedMessage () );
    }

    @ExceptionHandler ( ResourceNotFoundException.class )
    public ResponseEntity tratarError404 ( ResourceNotFoundException e ) {
        return ResponseEntity.notFound ().build ();
    }

    @ExceptionHandler ( EntityNotFoundException.class )
    public ResponseEntity tratarError404 () {
        return ResponseEntity.notFound ().build ();
    }

    @ExceptionHandler ( MethodArgumentNotValidException.class )
    public ResponseEntity tratarError400 ( MethodArgumentNotValidException e ) {
        var errores = e.getFieldErrors ().stream ()
                .map ( DatosErrorValidacion :: new ).toList ();
        return ResponseEntity.badRequest ().body ( errores );
    }

    @ExceptionHandler ( HttpClientErrorException.Unauthorized.class )
    public ResponseEntity tratarError403 ( HttpClientErrorException.Unauthorized e ) {
        return ResponseEntity.status ( 403 ).body ( e.getMessage () );
    }

    @ExceptionHandler ( HttpClientErrorException.Forbidden.class )
    public ResponseEntity tratarError403 ( HttpClientErrorException.Forbidden e ) {
        return ResponseEntity.status ( 403 ).body ( e.getMessage () );
    }

    @ExceptionHandler ( ValidacionDeIntegridad.class )
    public ResponseEntity errorHandlerValidacionesDeIntegridad ( Exception e ) {
        return ResponseEntity.badRequest ().body ( e.getMessage () );
    }

    @ExceptionHandler ( ValidationException.class )
    public ResponseEntity errorHandlerValidacionesDeNegocio ( Exception e ) {
        return ResponseEntity.badRequest ().body ( e.getMessage () );
    }

    private record DatosErrorValidacion( String campo, String error ) {
        public DatosErrorValidacion ( FieldError error ) {
            this ( error.getField (), error.getDefaultMessage () );
        }
    }
}
