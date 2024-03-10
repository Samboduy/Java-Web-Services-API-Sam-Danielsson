package org.slutprojektapi.courses;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursesRepository extends CrudRepository<Courses,Integer> {
}
