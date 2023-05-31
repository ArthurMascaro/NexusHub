package br.com.nexushub.usecases.subject;

import br.com.nexushub.domain.Subject;
import br.com.nexushub.domain.SubjectColor;
import br.com.nexushub.web.model.subject.request.SubjectDto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface SubjectCRUD {

    Subject createNewSubject(SubjectDto subjectDto);

    Subject findSubjectById(UUID subjectId);

    Subject updateSubjectById(UUID subjectId, SubjectDto subjectDto);

    List<Subject> findAllSubjectByUserId();

    Subject deleteSubjectById(UUID subjectId);
}
