package br.com.nexushub.usecases.subject;

import br.com.nexushub.domain.Subject;
import br.com.nexushub.domain.SubjectColor;
import br.com.nexushub.usecases.subject.gateway.SubjectDAO;
import br.com.nexushub.usecases.util.Notification;
import br.com.nexushub.usecases.util.Validator;
import br.com.nexushub.web.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
public class SubjectCRUDimpl implements SubjectCRUD {

    private final SubjectDAO subjectDAO;

    public SubjectCRUDimpl(SubjectDAO subjectDAO) {
        this.subjectDAO = subjectDAO;
    }
    @Override
    public Subject createNewSubject(String name, int difficulty, SubjectColor color) {
        Validator<Subject> validator = new SubjectInputRequestValidator();
        Notification notification = validator.validate(Subject.createWithoutId(name, difficulty, color));

        if (notification.hasErros())
            throw new IllegalArgumentException(notification.errorMessage());

        return subjectDAO.saveNewSubject(name, difficulty, color);
    }

    @Override
    public Subject findSubjectById(UUID subjectId) {
        Optional<Subject> subjectOptional = subjectDAO.findSubjectById(subjectId);

        if (subjectOptional.isEmpty())
            throw new ResourceNotFoundException("Subject not found");

        return subjectOptional.get();
    }

    @Override
    public Subject updateSubjectById(UUID subjectId, String name, int difficulty, SubjectColor color) {
        Optional<Subject> subjectOptional = subjectDAO.findSubjectById(subjectId);
        if (subjectOptional.isEmpty())
            throw new ResourceNotFoundException("Not found group participation with id: " + subjectId);

        var subject = subjectOptional.get();
        subject.setName(name);
        subject.setDifficulty(difficulty);
        subject.setColor(color);
        subjectDAO.updateSubject(subject);
        return subject;
    }

    @Override
    public ArrayList<Subject> findAllSubjects() {
        return subjectDAO.findAllSubjects();
    }

    @Override
    public void deleteSubjectById(UUID subjectId) {
        if(!subjectDAO.deleteSubjectById(subjectId))
            throw new IllegalArgumentException("Subject not found");
    }
}
