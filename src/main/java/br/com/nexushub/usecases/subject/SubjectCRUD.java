package br.com.nexushub.usecases.subject;

import br.com.nexushub.domain.Subject;
import br.com.nexushub.domain.SubjectColor;

import java.util.ArrayList;
import java.util.UUID;

public interface SubjectCRUD {

    Subject createNewSubject(String name, int difficulty, SubjectColor color);

    Subject findSubjectById(UUID subjectId);

    Subject updateSubjectById(UUID subjectId, String name, int difficulty, SubjectColor color);

    ArrayList<Subject> findAllSubjects();

    void deleteSubjectById(UUID subjectId);
}
