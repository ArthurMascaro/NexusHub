package br.com.nexushub.usecases.subject.gateway;

import br.com.nexushub.domain.Subject;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SubjectDAO {
        Subject saveNewSubject(Subject subject);

        Subject updateSubject(Subject subject);

        Optional<Subject> findSubjectById(UUID id);

        List<Subject> findAllSubjectsByUserId(UUID userId);

        Subject deleteSubjectById(UUID id);

        boolean subjectExistsById(UUID id);
}
