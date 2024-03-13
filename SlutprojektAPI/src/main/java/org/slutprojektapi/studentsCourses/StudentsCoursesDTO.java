package org.slutprojektapi.studentsCourses;

import lombok.Data;

@Data
public class StudentsCoursesDTO {
    private Long id;
    private Long studentsId;
    private String studentsName;
    private String town;
    private Long coursesId;
    private String courseName;
}
