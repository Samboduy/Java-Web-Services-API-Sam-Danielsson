package org.slutprojektapi.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
public class EntityExceptionDTO {
    private HttpStatus status;
    private String message;
}
