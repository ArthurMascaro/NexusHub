package br.com.nexushub.usecases.subject.gateway;

import br.com.nexushub.domain.Subject;
import br.com.nexushub.domain.SubjectColor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SubjectDAO {
        Subject saveNewSubject(Subject subject);

        void updateSubject(Subject subject);

        Optional<Subject> findSubjectById(UUID id);

        List<Subject> findAllSubjects();
        List<Subject> findAllSubjectsByUserId(UUID userId);

        boolean deleteSubjectById(UUID id);

        boolean subjectExistsById(UUID id);
}
