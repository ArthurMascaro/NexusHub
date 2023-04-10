package br.com.nexushub.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

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

