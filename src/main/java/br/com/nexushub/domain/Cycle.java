package br.com.nexushub.domain;

import java.time.LocalTime;
import java.util.*;

public class Cycle {

    private UUID id;
    private String name;
    private String description;
    private Double amountHours;
    private int lastStep;
    private List<Sequence> sequence;

    public Cycle(UUID id, String name, String description, Double amountHours, int lastStep, ArrayList<Sequence> sequence) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.amountHours = amountHours;
        this.lastStep = lastStep;
        this.sequence = sequence;
    }

    public Cycle(UUID id, String name, String description, Double amountHours, int lastStep) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.amountHours = amountHours;
        this.lastStep = lastStep;
    }

    public Cycle(String name, String description, Double amountHours, int lastStep) {
        this.name = name;
        this.description = description;
        this.amountHours = amountHours;
        this.lastStep = lastStep;
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

    public int getLastStep() {
        return lastStep;
    }

    public void setLastStep(int lastStep) {
        this.lastStep = lastStep;
    }

    public void addSequence(Sequence sequence) {
    	this.sequence.add(sequence);
    }

    public List<Sequence> getSequence(){
        return sequence;
    }

    public void setSequence(List<Sequence> sequence) {
    	this.sequence = sequence;
    }

    public void algorithm(List<Subject> subjects){
        subjects = new ArrayList<>(subjects);
        int totalDifficulty = subjects.stream().mapToInt(Subject::getDifficulty).sum();
        double factor = amountHours / totalDifficulty;
        Collections.shuffle(subjects);
        var sequences = new ArrayList<Sequence>();
        for (int i = 0; i < subjects.size(); i++){
            long duration = Math.round(subjects.get(i).getDifficulty() * factor * 60);
            sequences.add(new Sequence(UUID.randomUUID(), LocalTime.MIN.plusMinutes(duration), LocalTime.MIN, i + 1, SequenceStatus.PENDING, this, subjects.get(i)));
        }
        setLastStep(1);
        sequences.get(0).setStatus(SequenceStatus.STUDYING);
        this.sequence = sequences;
    }

    public void nextStep(){
        for (int i = 1; i <= sequence.size(); i++) {

            if (i == sequence.size()) {
                sequence.stream().forEach(sequence -> sequence.setStatus(SequenceStatus.PENDING));
                sequence.get(0).setStatus(SequenceStatus.STUDYING);
                setLastStep(1);
                break;
            }

            Sequence currentSequence = sequence.get(i - 1);
            if (currentSequence.getStatus() == SequenceStatus.FINISHED || currentSequence.getStatus() == SequenceStatus.SKIPPED) {
                continue;
            }

            currentSequence.setStatus(SequenceStatus.FINISHED);
            setLastStep(currentSequence.getSequenceNumber() + 1);
            sequence.get(i).setStatus(SequenceStatus.STUDYING);
            break;
        }
    }

    public void addListSubjects(ArrayList<Subject> subjects){
        List<Subject> allSubjects = new ArrayList<>();
        allSubjects.addAll(subjects);
        allSubjects.addAll(sequence.stream().map(Sequence::getSubject).toList());
        algorithm(allSubjects);
    }

    public void removeSequenceSubject(Subject subject){
        sequence.removeIf(sequence -> sequence.getSubject().getId().equals(subject.getId()));
        algorithm(sequence.stream().map(Sequence::getSubject).toList());
    }

    public void addSubject(Subject subject){
        List<Subject> allSubjects = new ArrayList<>(List.of(subject));
        allSubjects.addAll(sequence.stream().map(Sequence::getSubject).toList());
        algorithm(allSubjects);
    }

    public void updateSubject(Subject subject){
        sequence.stream().filter(sequence -> sequence.getSubject().getId().equals(subject.getId())).forEach(sequence -> sequence.setSubject(subject));
        algorithm(sequence.stream().map(Sequence::getSubject).toList());
    }

    //TODO: Implementar método para pular sequência
    //TODO: Implementar método para voltar sequência
    //TODO: Implementar método para voltar passo
    //TODO: Aprimorar método para atualizar sequência (se já estiver em andamento, não mudar dados dos subjects já executados nem a ordem)

    @Override
    public String toString() {
        return "Cycle{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", amountHours=" + amountHours +
                ", lastStep=" + lastStep +
                '}';
    }
}

