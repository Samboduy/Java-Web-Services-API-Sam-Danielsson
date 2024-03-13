package org.slutprojektapi.students;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slutprojektapi.courses.Courses;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "students")
@Table(name = "students")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Students {
    @Id
    @Column(name ="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "fName")
    private String fName;
    @Column(name = "lName")
    private String lName;
    @Column(name = "town")
    private String town;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name = "students_courses",
    joinColumns = @JoinColumn(name="students_id"),
    inverseJoinColumns = @JoinColumn(name = "courses_id"))
    private Set<Courses> courses = new HashSet<>();
}
