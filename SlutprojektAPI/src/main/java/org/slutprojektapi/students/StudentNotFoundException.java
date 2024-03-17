package org.slutprojektapi.students;

public class StudentNotFoundException extends RuntimeException{
     StudentNotFoundException(String message) {
        super(message);
    }
}
