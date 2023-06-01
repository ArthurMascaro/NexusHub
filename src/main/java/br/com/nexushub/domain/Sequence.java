package br.com.nexushub.domain;

import java.util.ArrayList;
import java.util.UUID;

public class Sequence {

    private UUID id;
    private int sequenceStep;
    private int lastSequenceItemStep;
    private SequenceStatus status;
    private ArrayList<SequenceItem> sequenceItems = new ArrayList<>();

    public Sequence(UUID id, int sequenceStep, int lastSequenceItemStep, SequenceStatus status, ArrayList<SequenceItem> sequenceItems) {
        this.id = id;
        this.sequenceStep = sequenceStep;
        this.lastSequenceItemStep = lastSequenceItemStep;
        this.status = status;
        this.sequenceItems = sequenceItems;
    }

    public Sequence(UUID id, int sequenceStep, int lastSequenceItemStep, SequenceStatus status) {
        this.id = id;
        this.sequenceStep = sequenceStep;
        this.lastSequenceItemStep = lastSequenceItemStep;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public int getLastSequenceItemStep() {
        return lastSequenceItemStep;
    }

    public void setLastSequenceItemStep(int lastSequenceItemStep) {
        this.lastSequenceItemStep = lastSequenceItemStep;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getSequenceStep() {
        return sequenceStep;
    }

    public void setSequenceStep(int sequenceStep) {
        this.sequenceStep = sequenceStep;
    }

    public ArrayList<SequenceItem> getSequenceItems() {
        return sequenceItems;
    }

    public void setSequenceItems(ArrayList<SequenceItem> sequenceItems) {
        this.sequenceItems = sequenceItems;
    }

    public SequenceStatus getStatus() {
        return status;
    }

    public void setStatus(SequenceStatus status) {
        this.status = status;
    }

    public void addSequenceItem(SequenceItem sequenceItem) {
        this.sequenceItems.add(sequenceItem);
    }

    @Override
    public String toString() {
        return "Sequence{" +
                "id=" + id +
                ", sequenceNumber=" + sequenceStep +
                ", lastSequenceItemStep=" + lastSequenceItemStep +
                ", status=" + status +
                ", sequenceItems=" + sequenceItems +
                '}';
    }
}
