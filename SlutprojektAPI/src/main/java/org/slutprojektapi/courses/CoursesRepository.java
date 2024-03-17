package org.slutprojektapi.courses;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoursesRepository extends JpaRepository<Courses,Long> {
    List<Courses> findAllByNameEquals(String name);
    List<Courses> findAllByNameContaining(String word);
    List<Courses> findAllByDescriptionContaining(String word);
}
