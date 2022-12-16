package com.mapoto.HotelManagement.FileMane;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class FileUploadExceptionAdv extends ResponseEntityExceptionHandler {
     @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ResponsMessage> handlesMaxSize(MaxUploadSizeExceededException exc){
         return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponsMessage("file is too large"));

     }
}
