package org.slutprojektapi.students;

import org.slutprojektapi.courses.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentsRepository extends JpaRepository<Students,Long> {

   List<Students> findAllByfNameEquals(String fName);
   List<Students> findAllBylNameEquals(String lName);
   List<Students> findAllByTownEquals(String town);
}
