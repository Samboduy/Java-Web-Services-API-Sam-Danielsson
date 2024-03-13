package org.slutprojektapi.studentsCourses;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slutprojektapi.courses.Courses;
import org.slutprojektapi.students.Students;

@Entity(name = "students_courses")
@Table(name = "students_courses")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentsCourses {
    @Id
    @Column(name ="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "students_id",
    referencedColumnName = "id")
    private Students students;
    @ManyToOne
    @JoinColumn(name = "courses_id",
    referencedColumnName = "id")
    private Courses courses;

}
