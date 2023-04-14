package br.com.nexushub.domain;

import java.time.LocalTime;
import java.util.Objects;
import java.util.UUID;

public class SequenceItem {

    private UUID id;
    private LocalTime hours;
    private LocalTime studiedHours;
    private int sequenceNumber;
    private SequenceItemStatus status;
    private Subject subject;

    public SequenceItem() {
    }

    public SequenceItem(UUID id, LocalTime hours, LocalTime studiedHours, int sequenceNumber, SequenceItemStatus status, Subject subject) {
        this.id = id;
        this.hours = hours;
        this.studiedHours = studiedHours;
        this.sequenceNumber = sequenceNumber;
        this.status = status;
        this.subject = subject;
    }

    public SequenceItem(LocalTime hours, LocalTime studiedHours, int sequenceNumber, SequenceItemStatus status, Subject subject) {
        this.hours = hours;
        this.studiedHours = studiedHours;
        this.sequenceNumber = sequenceNumber;
        this.status = status;
        this.subject = subject;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalTime getHours() {
        return hours;
    }

    public void setHours(LocalTime hours) {
        this.hours = hours;
    }

    public LocalTime getStudiedHours() {
        return studiedHours;
    }

    public void setStudiedHours(LocalTime studiedHours) {
        this.studiedHours = studiedHours;
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public SequenceItemStatus getStatus() {
        return status;
    }

    public void setStatus(SequenceItemStatus status) {
        this.status = status;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "SequenceItem{" +
                "id=" + id +
                ", hours=" + hours +
                ", studiedHours=" + studiedHours +
                ", sequenceNumber=" + sequenceNumber +
                ", status=" + status +
                ", subject=" + subject +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SequenceItem sequenceItem = (SequenceItem) o;

        return Objects.equals(id, sequenceItem.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public void reset(){
        this.status = SequenceItemStatus.PENDING;
        this.studiedHours = LocalTime.of(0,0);
    }
}
