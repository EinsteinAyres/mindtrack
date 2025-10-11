package br.com.ayrs.mindtrack.study.repository;

import br.com.ayrs.mindtrack.study.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

    Optional<Subject> findById(Long id);

}
