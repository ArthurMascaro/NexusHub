package br.com.nexushub.domain;

import java.util.ArrayList;
import java.util.UUID;

public class Sequence {

    private UUID id;
    private int sequenceNumber;
    private int lastSequenceItemStep;
    private SequenceStatus status;
    private ArrayList<SequenceItem> sequenceItems = new ArrayList<>();

    public Sequence(UUID id, int sequenceNumber, int lastSequenceItemStep, SequenceStatus status, ArrayList<SequenceItem> sequenceItems) {
        this.id = id;
        this.sequenceNumber = sequenceNumber;
        this.lastSequenceItemStep = lastSequenceItemStep;
        this.status = status;
        this.sequenceItems = sequenceItems;
    }

    public Sequence(UUID id, int sequenceNumber, int lastSequenceItemStep, SequenceStatus status) {
        this.id = id;
        this.sequenceNumber = sequenceNumber;
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

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
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
                ", sequenceNumber=" + sequenceNumber +
                ", lastSequenceItemStep=" + lastSequenceItemStep +
                ", status=" + status +
                ", sequenceItems=" + sequenceItems +
                '}';
    }
}
