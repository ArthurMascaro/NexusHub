package br.com.nexushub.domain;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Objects;
import java.util.UUID;

public class Sequence {

    private UUID id;
    private LocalTime hours;
    private LocalTime studiedHours;
    private int sequenceNumber;
    private SequenceStatus status;
    private Cycle cycle;
    private Subject subject;

    public Sequence(UUID id, LocalTime hours, LocalTime studiedHours, int sequenceNumber, SequenceStatus status, Cycle cycle, Subject subject) {
        this.id = id;
        this.hours = hours;
        this.studiedHours = studiedHours;
        this.sequenceNumber = sequenceNumber;
        this.status = status;
        this.cycle = cycle;
        this.subject = subject;
    }

    public Sequence(LocalTime hours, LocalTime studiedHours, int sequenceNumber, SequenceStatus status, Cycle cycle, Subject subject) {
        this.hours = hours;
        this.studiedHours = studiedHours;
        this.sequenceNumber = sequenceNumber;
        this.status = status;
        this.cycle = cycle;
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

    public SequenceStatus getStatus() {
        return status;
    }

    public void setStatus(SequenceStatus status) {
        this.status = status;
    }

    public Cycle getCycle() {
        return cycle;
    }

    public void setCycle(Cycle cycle) {
        this.cycle = cycle;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Sequence{" +
                "id=" + id +
                ", hours=" + hours +
                ", studiedHours=" + studiedHours +
                ", sequenceNumber=" + sequenceNumber +
                ", status=" + status +
                ", cycle=" + cycle +
                ", subject=" + subject +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sequence sequence = (Sequence) o;

        return Objects.equals(id, sequence.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
