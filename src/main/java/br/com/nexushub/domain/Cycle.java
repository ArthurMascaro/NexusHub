package br.com.nexushub.domain;

import java.time.LocalTime;
import java.util.*;

public class Cycle {

    private UUID id;
    private String name;
    private String description;
    private Double amountHours;
    private List<Sequence> sequences = new ArrayList<>();

    public Cycle(UUID id, String name, String description, Double amountHours, ArrayList<Sequence> sequenceItem) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.amountHours = amountHours;
        this.sequences = sequenceItem;
    }

    public Cycle(UUID id, String name, String description, Double amountHours) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.amountHours = amountHours;
    }

    public Cycle(String name, String description, Double amountHours) {
        this.name = name;
        this.description = description;
        this.amountHours = amountHours;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmountHours() {
        return amountHours;
    }

    public void setAmountHours(Double amountHours) {
        this.amountHours = amountHours;
    }

    public void addSequence(Sequence sequence) {
    	this.sequences.add(sequence);
    }

    public List<Sequence> getSequence(){
        return sequences;
    }

    public void setSequence(List<Sequence> sequenceItem) {
    	this.sequences = sequenceItem;
    }

    public void algorithm(List<Subject> subjects){
        subjects = new ArrayList<>(subjects);
        int totalDifficulty = subjects.stream().mapToInt(Subject::getDifficulty).sum();
        double factor = amountHours / totalDifficulty;
        Collections.shuffle(subjects);
        var sequence = new Sequence(UUID.randomUUID(), sequences.size()+1, 1, SequenceStatus.RUNNING);
        for (int i = 0; i < subjects.size(); i++){
            long duration = Math.round(subjects.get(i).getDifficulty() * factor * 60);
            var sequenceItem = new SequenceItem(UUID.randomUUID(), LocalTime.MIN.plusMinutes(duration), LocalTime.MIN, i + 1, SequenceItemStatus.PENDING, subjects.get(i));
            if (i == 0)
                sequenceItem.setStatus(SequenceItemStatus.STUDYING);
            sequence.addSequenceItem(sequenceItem);
        }
        addSequence(sequence);
    }

    public void currentExecutionAlgorithmAddSubjects(List<Subject> subjects){
        subjects = new ArrayList<>(subjects);
        ArrayList<Subject> allSubjects = new ArrayList<>();
        allSubjects.addAll(subjects);
        if (sequences.stream().filter(sequence -> sequence.getStatus().equals(SequenceStatus.RUNNING)).findFirst().isPresent()){
            var lastSequence = sequences.stream().filter(sequence -> sequence.getStatus().equals(SequenceStatus.RUNNING)).findFirst().get();
            var lastSequenceItems = lastSequence.getSequenceItems();
            allSubjects.addAll(lastSequenceItems.stream().map(SequenceItem::getSubject).toList());
        }
        Collections.shuffle(allSubjects);

        int totalDifficulty = allSubjects.stream().mapToInt(Subject::getDifficulty).sum();
        double factor = amountHours / totalDifficulty;

        var lastSequence = sequences.stream().filter(sequence -> sequence.getStatus().equals(SequenceStatus.RUNNING)).findFirst().get();
        var lastSequenceItems = lastSequence.getSequenceItems();
        var newSequence = new Sequence(UUID.randomUUID(), sequences.size()+1, 1, SequenceStatus.RUNNING);

        for (int i = 0; i < allSubjects.size(); i++){
            Subject currentSubject = allSubjects.get(i);
            long duration = Math.round(currentSubject.getDifficulty() * factor * 60);
            if (lastSequenceItems.stream().anyMatch(sequenceItem -> sequenceItem.getSubject().getId().equals(currentSubject.getId()))){
                int index = lastSequenceItems.indexOf(lastSequenceItems.stream().filter(sequenceItem -> sequenceItem.getSubject().getId().equals(currentSubject.getId())).findFirst().get());
                var SubjectItem = lastSequenceItems.get(index);
                var current = new SequenceItem(SubjectItem.getId(), LocalTime.MIN.plusMinutes(duration), LocalTime.MIN, i+1, SequenceItemStatus.PENDING, currentSubject);
                newSequence.addSequenceItem(current);
            } else {
                newSequence.addSequenceItem(new SequenceItem(UUID.randomUUID(), LocalTime.MIN.plusMinutes(duration), LocalTime.MIN, i + 1, SequenceItemStatus.PENDING, currentSubject));
            }
        }
        lastSequence.setStatus(SequenceStatus.FINISHED);
        lastSequence.getSequenceItems().stream().filter(sequenceItem -> !sequenceItem.getStatus().equals(SequenceItemStatus.FINISHED)).forEach(sequenceItem -> sequenceItem.setStatus(SequenceItemStatus.SKIPPED));
        newSequence.getSequenceItems().forEach(SequenceItem::reset);
        newSequence.getSequenceItems().get(0).setStatus(SequenceItemStatus.STUDYING);
        this.sequences.add(newSequence);
    }

    public void addHours(LocalTime studiedHours){
        var lastSequence = sequences.stream().filter(sequence -> sequence.getStatus().equals(SequenceStatus.RUNNING)).findFirst().get();
        var lastSequenceItems = lastSequence.getSequenceItems();
        var currentSequenceItem = lastSequenceItems.stream().filter(sequenceItem -> sequenceItem.getStatus().equals(SequenceItemStatus.STUDYING)).findFirst().get();
        long currentStudyTime = currentSequenceItem.getStudiedHours().toNanoOfDay();
        long studiedTime = studiedHours.toNanoOfDay();
        long timeNeededForStudy = currentSequenceItem.getHours().toNanoOfDay();
        if (currentStudyTime + studiedTime >= timeNeededForStudy){
            currentSequenceItem.setStatus(SequenceItemStatus.FINISHED);
            currentSequenceItem.setStudiedHours(currentSequenceItem.getHours());
            if (lastSequenceItems.indexOf(currentSequenceItem) + 1 < lastSequenceItems.size()){
                SequenceItem nextSequenceItem = lastSequenceItems.get(lastSequenceItems.indexOf(lastSequenceItems.stream().filter(sequenceItem -> sequenceItem.getStatus().equals(SequenceItemStatus.PENDING)).findFirst().get()));
                nextSequenceItem.setStatus(SequenceItemStatus.STUDYING);
                nextSequenceItem.setStudiedHours(LocalTime.MIN);
            } else {
                lastSequence.setStatus(SequenceStatus.FINISHED);
                var newSequence = new Sequence(UUID.randomUUID(), lastSequence.getSequenceNumber() + 1, 1, SequenceStatus.RUNNING);
                lastSequenceItems.stream().forEach(sequenceItem -> sequenceItem.reset());
                lastSequenceItems.get(0).setStatus(SequenceItemStatus.STUDYING);
                newSequence.setSequenceItems(lastSequenceItems);
                sequences.add(newSequence);
            }
        } else {
            currentSequenceItem.setStudiedHours(currentSequenceItem.getStudiedHours().plusSeconds(studiedHours.getSecond()).plusMinutes(studiedHours.getMinute()).plusHours(studiedHours.getHour()));
        }
    }

    public void skipStepItem(){
        var lastSequence = sequences.stream().filter(sequence -> sequence.getStatus().equals(SequenceStatus.RUNNING)).findFirst().get();
        var lastSequenceItems = lastSequence.getSequenceItems();
        for (int i = 1; i <= lastSequenceItems.size(); i++) {
            SequenceItem currentSequenceItem = lastSequenceItems.get(i - 1);
            if (i == lastSequenceItems.size()) {
                lastSequence.setStatus(SequenceStatus.FINISHED);
                currentSequenceItem.setStatus(SequenceItemStatus.FINISHED);
                currentSequenceItem.setStudiedHours(currentSequenceItem.getHours());
                var newSequence = new Sequence(UUID.randomUUID(), lastSequence.getSequenceNumber() + 1, 1, SequenceStatus.RUNNING);
                lastSequenceItems.stream().forEach(sequenceItem -> sequenceItem.reset());
                lastSequenceItems.get(0).setStatus(SequenceItemStatus.STUDYING);
                newSequence.setSequenceItems(lastSequenceItems);
                sequences.add(newSequence);
                break;
            }

            if (currentSequenceItem.getStatus() == SequenceItemStatus.FINISHED || currentSequenceItem.getStatus() == SequenceItemStatus.SKIPPED) {
                continue;
            }

            currentSequenceItem.setStatus(SequenceItemStatus.FINISHED);
            currentSequenceItem.setStudiedHours(currentSequenceItem.getHours());
            SequenceItem nextSequenceItem = lastSequenceItems.get(lastSequenceItems.indexOf(lastSequenceItems.stream().filter(sequenceItem -> sequenceItem.getStatus().equals(SequenceItemStatus.PENDING)).findFirst().get()));
            lastSequence.setLastSequenceItemStep(nextSequenceItem.getSequenceNumber());
            nextSequenceItem.setStatus(SequenceItemStatus.STUDYING);
            break;
        }
    }

    public void addListSubjects(ArrayList<Subject> subjects){
        List<Subject> allSubjects = new ArrayList<>();
        allSubjects.addAll(subjects);
        if (sequences.size() > 0)
            currentExecutionAlgorithmAddSubjects(allSubjects);
        else
            algorithm(allSubjects);
    }

    public void removeSequenceSubject(Subject subject){
        Sequence lastSequence = sequences.stream()
                .filter(sequence -> sequence.getStatus().equals(SequenceStatus.RUNNING)).
                findFirst().get();
        lastSequence.getSequenceItems().removeIf(sequenceItem -> sequenceItem.getSubject().getId().equals(subject.getId()));
        lastSequence.setStatus(SequenceStatus.FINISHED);
        lastSequence.getSequenceItems().stream().filter(sequenceItem -> !sequenceItem.getStatus().equals(SequenceItemStatus.FINISHED)).forEach(sequenceItem -> sequenceItem.setStatus(SequenceItemStatus.SKIPPED));
        algorithm(lastSequence.getSequenceItems().stream().map(SequenceItem::getSubject).toList());
    }

    public void addSubject(Subject subject){
        addListSubjects(new ArrayList<>(List.of(subject)));
    }

    public void updateSubject(Subject subject){
        Sequence lastSequence = sequences.stream()
                .filter(sequence -> sequence.getStatus().equals(SequenceStatus.RUNNING)).
                findFirst().get();
        lastSequence.getSequenceItems().stream().filter(sequenceItem -> sequenceItem.getSubject().getId().equals(subject.getId())).forEach(sequenceItem -> sequenceItem.setSubject(subject));
        lastSequence.getSequenceItems().stream().filter(sequenceItem -> !sequenceItem.getStatus().equals(SequenceItemStatus.FINISHED)).forEach(sequenceItem -> sequenceItem.setStatus(SequenceItemStatus.SKIPPED));
        lastSequence.setStatus(SequenceStatus.FINISHED);
        algorithm(lastSequence.getSequenceItems().stream().map(SequenceItem::getSubject).toList());
    }

    //TODO: Implementar método para pular sequência
    //TODO: Criar AddHours para um Subject especifico
    //TODO: DEBUGGAR TUDO


    @Override
    public String toString() {
        return "Cycle{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", amountHours=" + amountHours +
                ", sequences=" + sequences +
                '}';
    }
}

