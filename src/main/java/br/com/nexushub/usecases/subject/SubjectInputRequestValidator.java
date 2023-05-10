package br.com.nexushub.usecases.subject;

import br.com.nexushub.domain.Subject;
import br.com.nexushub.usecases.util.Notification;
import br.com.nexushub.usecases.util.Validator;

public class SubjectInputRequestValidator extends Validator<Subject> {
    @Override
    public Notification validate(Subject subject) {
        Notification notification = new Notification();

        if (subject == null){
            notification.addError("Subject is null", new IllegalArgumentException("Subject is Null"));
        }

        if(nullOrEmpty(subject.getName()))
            notification.addError("Name is null or empty", new IllegalArgumentException());

        if(subject.getDifficulty() < 0 || subject.getDifficulty() > 10)
            notification.addError("Difficulty is invalid", new IllegalArgumentException());

        if (subject.getColor() == null)
            notification.addError("Color is null", new IllegalArgumentException());

        return notification;
    }
}
