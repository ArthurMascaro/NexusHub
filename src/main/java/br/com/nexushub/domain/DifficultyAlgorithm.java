package br.com.nexushub.domain;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class DifficultyAlgorithm {

    public static void main(String[] args) {
        Cycle cycle = new Cycle(UUID.randomUUID(), "Ciclo 1", "Ciclo 1", 20.0, 0);
        List<Subject> subjects = new ArrayList<>(List.of(new Subject(UUID.randomUUID(), "Português", 7, SubjectColor.BLUE),
                new Subject(UUID.randomUUID(), "Matemática", 2, SubjectColor.RED),
                new Subject(UUID.randomUUID(), "História", 9 , SubjectColor.GREEN),
                new Subject(UUID.randomUUID(), "Geografia", 3, SubjectColor.YELLOW),
                new Subject(UUID.randomUUID(), "Biologia", 4, SubjectColor.ORANGE)));
        List<Sequence> sequence = new ArrayList<>();
        sequence = algorithm(subjects, cycle);
        sequence = nextStep(sequence);
        sequence = nextStep(sequence);
        sequence = nextStep(sequence);
        sequence = nextStep(sequence);
        sequence = nextStep(sequence);
        sequence = nextStep(sequence);
        sequence.forEach(System.out::println);

        Subject teste = new Subject("Quimica", 1, SubjectColor.YELLOW);
        sequence = updateSequence(sequence, (List.of(teste)));
        System.out.println("NOVA \n");
        sequence.forEach(System.out::println);


        System.out.println("Removida \n");
        sequence = removeSubject(sequence, teste);
        sequence = removeSubject(sequence, subjects.get(0));
        sequence.forEach(System.out::println);
    }

    public static List<Sequence> algorithm(List<Subject> subject, Cycle cycle){
        int totalDifficulty = subject.stream().mapToInt(Subject::getDifficulty).sum();
        double value = (double) cycle.getAmountHours() / totalDifficulty;
        List<Sequence> sequences = new ArrayList<>();
        ArrayList<Subject> subjects = new ArrayList<>(subject);
        Collections.shuffle(subjects);
        int totalHours = 0;
        for (int i = 0; i < subjects.size(); i++) {
            long duration = Math.round(value * subjects.get(i).getDifficulty() * 60);
            totalHours += duration;
            sequences.add(new Sequence(UUID.randomUUID(), LocalTime.MIN.plusMinutes(duration), LocalTime.MIN, i+1, SequenceStatus.PENDING, cycle, subjects.get(i)));
        }
        cycle.setLastStep(1);
        sequences.get(0).setStatus(SequenceStatus.STUDYING);
        return sequences;
    }

    public static List<Sequence> nextStep(List<Sequence> sequences){
        for (int i = 1; i <= sequences.size(); i++) {
            if (i == sequences.size()){
                sequences.stream().forEach(sequence -> sequence.setStatus(SequenceStatus.PENDING));
                sequences.get(0).setStatus(SequenceStatus.STUDYING);
                sequences.get(0).getCycle().setLastStep(1);
                break;
            }
            Sequence sequence = sequences.get(i-1);
            if (sequence.getStatus() == SequenceStatus.FINISHED || sequence.getStatus() == SequenceStatus.SKIPPED){
                continue;
            }
            sequence.setStatus(SequenceStatus.FINISHED);
            sequence.getCycle().setLastStep(sequence.getSequenceNumber()+1);
            sequences.get(i).setStatus(SequenceStatus.STUDYING);
            break;
        }
        return sequences;
    }

    public static List<Sequence> updateSequence(List<Sequence> sequences, List<Subject> subjects) {
        List<Subject> allSubjects = new ArrayList<>();
        allSubjects.addAll(subjects);
        allSubjects.addAll(sequences.stream().map(Sequence::getSubject).toList());
        return algorithm(allSubjects, sequences.get(0).getCycle());
    }

    public static List<Sequence> removeSubject(List<Sequence> sequences, Subject subject) {
        sequences.removeIf(sequence -> sequence.getSubject().equals(subject));
        return algorithm(sequences.stream().map(Sequence::getSubject).toList(), sequences.get(0).getCycle());
    }

    public static List<Sequence> addSubject(List<Sequence> sequences, Subject subject) {
        sequences.add(new Sequence(UUID.randomUUID(), LocalTime.MIN, LocalTime.MIN, sequences.size()+1, SequenceStatus.PENDING, sequences.get(0).getCycle(), subject));
        return algorithm(sequences.stream().map(Sequence::getSubject).toList(), sequences.get(0).getCycle());
    }

    //TODO se sequência já existir e estiver em andamento, permanenecer em andamento apenas atualizando/removendo/adicionando a matéria à sequeência (Mantendo horas estudadas, status, etc)

}
