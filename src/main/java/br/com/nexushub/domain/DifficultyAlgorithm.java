package br.com.nexushub.domain;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DifficultyAlgorithm {

    public static void main(String[] args) {
        Cycle cycle = new Cycle(UUID.randomUUID(), "Ciclo 1", "Ciclo 1", 10.0);
        List<Subject> subjects = new ArrayList<>(List.of(new Subject(UUID.randomUUID(), "Português", 7, SubjectColor.BLUE),
                new Subject(UUID.randomUUID(), "Matemática", 3, SubjectColor.RED),
                new Subject(UUID.randomUUID(), "História", 9 , SubjectColor.GREEN),
                new Subject(UUID.randomUUID(), "Geografia", 3, SubjectColor.YELLOW),
                new Subject(UUID.randomUUID(), "Biologia", 4, SubjectColor.ORANGE)));
        cycle.algorithm(subjects);

        Sequence lastSequence = cycle.getSequence().stream().filter(sequence -> sequence.getStatus().equals(SequenceStatus.RUNNING)).findFirst().get();
        lastSequence.getSequenceItems().forEach(System.out::println);

        UUID id = UUID.randomUUID();

        System.out.println("\n Teste Adicionar Elemento \n");
        Subject test = new Subject(id, "Teste", 1, SubjectColor.ORANGE);
        cycle.addSubject(test);
        cycle.getSequence().stream().filter(sequence -> sequence.getStatus().equals(SequenceStatus.RUNNING)).findFirst().get().getSequenceItems().forEach(System.out::println);

        System.out.println("\n Teste Atualizar Elemento \n");
        cycle.updateSubject(new Subject(id, "Teste", 10, SubjectColor.ORANGE));
        cycle.getSequence().stream().filter(sequence -> sequence.getStatus().equals(SequenceStatus.RUNNING)).findFirst().get().getSequenceItems().forEach(System.out::println);

        System.out.println("\n Teste Adicionar hora \n");
        cycle.addHours(LocalTime.of(10, 0));
        cycle.addHours(LocalTime.of(3, 0));
        cycle.addHours(LocalTime.of(2, 0));
        cycle.addHours(LocalTime.of(1, 0));
        cycle.addHours(LocalTime.of(10, 0));
        cycle.addHours(LocalTime.of(0, 15));

        cycle.getSequence().stream().filter(sequence -> sequence.getStatus().equals(SequenceStatus.RUNNING)).findFirst().get().getSequenceItems().forEach(System.out::println);

        System.out.println("\n Teste Remover Elemento \n");
        cycle.removeSequenceSubject(test);

        System.out.println("\n Teste Proximo Passo \n");
        for (int i = 0; i < 4; i++) {
            cycle.skipStepItem();
        }
        Sequence newlastSequence = cycle.getSequence().stream().filter(sequence -> sequence.getStatus().equals(SequenceStatus.RUNNING)).findFirst().get();
        newlastSequence.getSequenceItems().forEach(System.out::println);
        System.out.println("\n numero da sequência atual:" + newlastSequence.getSequenceNumber());

        System.out.println("\n Sequencias Existentes \n");
        cycle.getSequence().forEach(System.out::println);

        cycle.currentExecutionAlgorithmAddSubjects(new ArrayList<>(List.of(new Subject(UUID.randomUUID(), "TesteLista1", 4, SubjectColor.BLACK),
                new Subject(UUID.randomUUID(), "TesteLista2", 7, SubjectColor.GRAY))));
        cycle.getSequence().stream().filter(sequence -> sequence.getStatus().equals(SequenceStatus.RUNNING)).findFirst().get().getSequenceItems().forEach(System.out::println);
        System.out.println(cycle.getSequence().stream().filter(sequence -> sequence.getStatus().equals(SequenceStatus.RUNNING)).findFirst().get().getSequenceNumber());
        cycle.getSequence().get(3).getSequenceItems().forEach(System.out::println);
        System.out.println("\n Sequencias Existentes \n");
        cycle.getSequence().forEach(System.out::println);
    }
}
