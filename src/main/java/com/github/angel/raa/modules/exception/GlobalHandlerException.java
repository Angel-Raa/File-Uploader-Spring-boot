package com.github.angel.raa.modules.exception;

import com.github.angel.raa.modules.utils.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.FileNotFoundException;
import java.io.IOException;

@ControllerAdvice
public class GlobalHandlerException extends ResponseEntityExceptionHandler {
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<Response> handlerMaxSizeException(MaxUploadSizeExceededException error){
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new Response(error.getMessage(), 417));
    }
    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<Response> handleFileNotFoundException (FileNotFoundException exc){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("El archivo solicitado no se ha encontrado o no esta disponible", 404));
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<Response> handleIOException (IOException exc){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(exc.getMessage(), 500));
    }

}
