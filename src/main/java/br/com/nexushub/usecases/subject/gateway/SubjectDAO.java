package br.com.nexushub.usecases.subject.gateway;

import br.com.nexushub.domain.Subject;
import br.com.nexushub.domain.SubjectColor;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public interface SubjectDAO {
        Subject saveNewSubject(String name, int difficulty, SubjectColor color);

        void updateSubject(Subject subject);

        Optional<Subject> findSubjectById(UUID id);

        ArrayList<Subject> findAllSubjects();

        boolean deleteSubjectById(UUID id);

        boolean subjectExistsById(UUID id);
}
