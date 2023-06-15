package br.com.nexushub.usecases.subject;

import br.com.nexushub.configuration.auth.jwt.IAuthenticationFacade;
import br.com.nexushub.domain.Subject;
import br.com.nexushub.usecases.account.gateway.ApplicationUserDAO;
import br.com.nexushub.usecases.subject.gateway.SubjectDAO;
import br.com.nexushub.usecases.util.Notification;
import br.com.nexushub.usecases.util.Validator;
import br.com.nexushub.web.exception.ResourceNotFoundException;
import br.com.nexushub.web.model.subject.request.SubjectRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SubjectCRUDimpl implements SubjectCRUD {

    private final SubjectDAO subjectDAO;

    private final ApplicationUserDAO applicationUserDAO;

    private final IAuthenticationFacade authentication;

    public SubjectCRUDimpl(SubjectDAO subjectDAO, ApplicationUserDAO applicationUserDAO, IAuthenticationFacade authentication) {
        this.subjectDAO = subjectDAO;
        this.applicationUserDAO = applicationUserDAO;
        this.authentication = authentication;
    }

    @Override
    public Subject createNewSubject(SubjectRequest subjectRequest) {
        Subject subject = subjectRequest.toSubject();

        subject.setOwnerId(authentication.getUserAuthenticatedId());

        return subjectDAO.saveNewSubject(subject);
    }

    @Override
    public Subject findSubjectById(UUID subjectId) {
        return subjectDAO.findSubjectById(subjectId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found subject with id: " + subjectId));
    }

    @Override
    public Subject updateSubjectById(UUID subjectId, SubjectRequest subjectRequest) {

        Subject subjectUpdate = subjectRequest.toSubject();

        Subject subject = subjectDAO.findSubjectById(subjectId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found subject with id: " + subjectId));

        if (!subject.getOwnerId().equals(authentication.getUserAuthenticatedId()))
            throw new RuntimeException("You dont have permission to udpate this subject");

        //TODO: Criar exceção melhor

        subjectUpdate.setId(subjectId);
        return subjectDAO.updateSubject(subjectUpdate);
    }

    @Override
    public List<Subject> findAllSubjectByUserId() {
        return subjectDAO.findAllSubjectsByUserId(authentication.getUserAuthenticatedId());
    }

    @Override
    public Subject deleteSubjectById(UUID subjectId) {
        var subject = subjectDAO.findSubjectById(subjectId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found subject with id: " + subjectId));

        if (!subject.getOwnerId().equals(authentication.getUserAuthenticatedId()))
            throw new RuntimeException("You dont have permission to delete this subject");

        //TODO: exceção melhor

        return subjectDAO.deleteSubjectById(subjectId);
    }
}
