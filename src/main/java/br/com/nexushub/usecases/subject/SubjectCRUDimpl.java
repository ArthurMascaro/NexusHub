package br.com.nexushub.usecases.subject;

import br.com.nexushub.configuration.auth.jwt.IAuthenticationFacade;
import br.com.nexushub.domain.Subject;
import br.com.nexushub.domain.SubjectColor;
import br.com.nexushub.usecases.subject.gateway.SubjectDAO;
import br.com.nexushub.usecases.util.Notification;
import br.com.nexushub.usecases.util.Validator;
import br.com.nexushub.web.exception.ResourceNotFoundException;
import br.com.nexushub.web.model.subject.request.SubjectDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SubjectCRUDimpl implements SubjectCRUD {

    private final SubjectDAO subjectDAO;

    private final IAuthenticationFacade authentication;

    public SubjectCRUDimpl(SubjectDAO subjectDAO, IAuthenticationFacade authentication) {
        this.subjectDAO = subjectDAO;
        this.authentication = authentication;
    }

    @Override
    public Subject createNewSubject(SubjectDto subjectDto) {
        Subject subject = subjectDto.toSubject();
        Validator<Subject> validator = new SubjectInputRequestValidator();
        Notification notification = validator.validate(subject);

        if (notification.hasErros())
            throw new IllegalArgumentException(notification.errorMessage());

        System.out.println(authentication.getUserAuthenticatedId());

        return subjectDAO.saveNewSubject(subject);
    }

    @Override
    public Subject findSubjectById(UUID subjectId) {
        Optional<Subject> subjectOptional = subjectDAO.findSubjectById(subjectId);

        if (subjectOptional.isEmpty())
            throw new ResourceNotFoundException("Subject not found");

        return subjectOptional.get();
    }

    @Override
    public Subject updateSubjectById(UUID subjectId, SubjectDto subjectDto) {
        Subject subjectUpdate = subjectDto.toSubject();
        Optional<Subject> subjectOptional = subjectDAO.findSubjectById(subjectId);
        if (subjectOptional.isEmpty())
            throw new ResourceNotFoundException("Not found group participation with id: " + subjectId);

        Validator<Subject> validator = new SubjectInputRequestValidator();
        Notification notification = validator.validate(subjectUpdate);

        if (notification.hasErros())
            throw new IllegalArgumentException(notification.errorMessage());

        var subject = subjectOptional.get();
        subject.setName(subjectUpdate.getName());
        subject.setDifficulty(subjectUpdate.getDifficulty());
        subject.setColor(subjectUpdate.getColor());
        subjectDAO.updateSubject(subject);
        return subject;
    }

    @Override
    public List<Subject> findAllSubjects() {
        return subjectDAO.findAllSubjects();
    }

    @Override
    public List<Subject> findSubjectByUserId(UUID userId) {
        /*if (userDao.findUserById(userId).isEmpty())
            throw new ResourceNotFoundException("User not found");*/

        return subjectDAO.findAllSubjectsByUserId(userId);
    }

    @Override
    public void deleteSubjectById(UUID subjectId) {
        if(!subjectDAO.deleteSubjectById(subjectId))
            throw new IllegalArgumentException("Subject not found");
    }
}
