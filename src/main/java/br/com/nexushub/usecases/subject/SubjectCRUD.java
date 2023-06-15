package br.com.nexushub.usecases.subject;

import br.com.nexushub.domain.Subject;
import br.com.nexushub.web.model.subject.request.SubjectRequest;

import java.util.List;
import java.util.UUID;

public interface SubjectCRUD {

    Subject createNewSubject(SubjectRequest subjectRequest);

    Subject findSubjectById(UUID subjectId);

    Subject updateSubjectById(UUID subjectId, SubjectRequest subjectRequest);

    List<Subject> findAllSubjectByUserId();

    Subject deleteSubjectById(UUID subjectId);
}
