package br.com.nexushub.domain;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

public class DifficultyAlgorithm {

    public static void main(String[] args) {
        Cycle cycle = new Cycle(UUID.randomUUID(), "Ciclo 1", "Ciclo 1", 10.0, 0);
        List<Subject> subjects = new ArrayList<>(List.of(new Subject(UUID.randomUUID(), "Português", 7, SubjectColor.BLUE),
                new Subject(UUID.randomUUID(), "Matemática", 2, SubjectColor.RED),
                new Subject(UUID.randomUUID(), "História", 9 , SubjectColor.GREEN),
                new Subject(UUID.randomUUID(), "Geografia", 3, SubjectColor.YELLOW),
                new Subject(UUID.randomUUID(), "Biologia", 4, SubjectColor.ORANGE)));
        cycle.algorithm(subjects);
        cycle.getSequence().forEach(System.out::println);

        UUID id = UUID.randomUUID();

        System.out.println("\n Teste Adicionar Elemento \n");
        Subject test = new Subject(id, "Teste", 1, SubjectColor.ORANGE);
        cycle.addSubject(test);
        cycle.getSequence().forEach(System.out::println);

        System.out.println("\n Teste Atualizar Elemento \n");
        cycle.updateSubject(new Subject(id, "Teste", 10, SubjectColor.ORANGE));
        cycle.getSequence().forEach(System.out::println);

        System.out.println("\n Teste Remover Elemento \n");
        cycle.removeSequenceSubject(test);
        cycle.getSequence().forEach(System.out::println);

        System.out.println("\n Teste Proximo Passo \n");
        for (int i = 0; i < 4; i++) {
            cycle.nextStep();
        }
        cycle.getSequence().forEach(System.out::println);

        System.out.println("\n Teste Adicionar Lista de Elementos \n");
        cycle.currentExecutionAlgorithmAddSubjects(new ArrayList<>(List.of(new Subject(UUID.randomUUID(), "TesteLista1", 4, SubjectColor.BLACK),
                new Subject(UUID.randomUUID(), "TesteLista2", 7, SubjectColor.GRAY))));
        cycle.getSequence().forEach(System.out::println);
    }
}
