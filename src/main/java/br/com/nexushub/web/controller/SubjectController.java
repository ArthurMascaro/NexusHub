package br.com.nexushub.web.controller;

import br.com.nexushub.domain.Subject;
import br.com.nexushub.domain.SubjectColor;
import br.com.nexushub.usecases.subject.SubjectCRUD;
import br.com.nexushub.web.model.subject.request.SubjectDto;
import br.com.nexushub.web.model.subject.response.SubjectResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequestMapping("/api/v1/subjects")
@RestController
public class SubjectController {

    private final SubjectCRUD subjectCRUD;

    public SubjectController(SubjectCRUD subjectCRUD) {
        this.subjectCRUD = subjectCRUD;
    }

    @PostMapping("/save")
    public ResponseEntity<SubjectResponse> createNewSubject(
            @RequestBody SubjectDto subjectDto) {
        Subject subject = subjectCRUD.createNewSubject(subjectDto);
        return ResponseEntity.ok(SubjectResponse.createFromSubject(subject));
    }

    @GetMapping("/all")
    public ResponseEntity<List<SubjectResponse>> findAllSubjects() {
        List<Subject> subjects = subjectCRUD.findAllSubjectByUserId();
        return ResponseEntity.ok(subjects.stream()
                .map(SubjectResponse::createFromSubject)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectResponse> findSubjectById(@PathVariable("id") UUID id){
        Subject subject = subjectCRUD.findSubjectById(id);
        return ResponseEntity.ok(SubjectResponse.createFromSubject(subject));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubjectResponse> updateSubjectById(@PathVariable("id") UUID id, @RequestBody SubjectDto subjectDto) {
        Subject subject = subjectCRUD.updateSubjectById(id, subjectDto);
        return ResponseEntity.ok(SubjectResponse.createFromSubject(subject));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<UUID> deleteSubjectById(@PathVariable("id") UUID id) {
        subjectCRUD.deleteSubjectById(id);
        return ResponseEntity.ok(id);
    }
}
