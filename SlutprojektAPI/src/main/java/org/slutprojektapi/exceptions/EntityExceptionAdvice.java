package org.slutprojektapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
@ControllerAdvice
public class EntityExceptionAdvice {
    @ResponseBody
    @ExceptionHandler(EntityNotFoundException.class)
    ResponseEntity<EntityExceptionDTO> EntityNotFoundHandler(EntityNotFoundException ex) {
        EntityExceptionDTO dto = new EntityExceptionDTO();
        dto.setStatus(HttpStatus.NOT_FOUND);
        dto.setMessage(ex.getMessage());
        return new ResponseEntity<>(dto,HttpStatus.NOT_FOUND);
    }
}
