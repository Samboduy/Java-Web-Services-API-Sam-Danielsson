package org.slutprojektapi.courses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slutprojektapi.students.Students;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "courses")
@Table(name = "courses")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Courses {
    @Id
    @Column(name ="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "courseName")
    private String name;
    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "courses")
    private Set<Students> students = new HashSet<>();
}
