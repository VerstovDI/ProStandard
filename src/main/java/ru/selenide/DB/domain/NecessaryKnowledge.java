package ru.selenide.DB.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "necessary_knowledge", schema = "proff")
public class NecessaryKnowledge {
    @Id
    @Column(name = "id_necessary_knowledge", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_particular_work_function", nullable = false)
    private ParticularWorkFunction particularWorkFunction;

    public NecessaryKnowledge() {
    }

    public NecessaryKnowledge(Long id, String description, ParticularWorkFunction particularWorkFunction) {
        this.id = id;
        this.description = description;
        this.particularWorkFunction = particularWorkFunction;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ParticularWorkFunction getParticularWorkFunctions() {
        return particularWorkFunction;
    }

    public void setParticularWorkFunctions(ParticularWorkFunction particularWorkFunction) {
        this.particularWorkFunction = particularWorkFunction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NecessaryKnowledge)) return false;
        NecessaryKnowledge that = (NecessaryKnowledge) o;
        return Objects.equals(id, that.id) && Objects.equals(description, that.description) && Objects.equals(particularWorkFunction, that.particularWorkFunction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, particularWorkFunction);
    }

    @Override
    public String toString() {
        return "LaborActions{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", particularWorkFunctions=" + particularWorkFunction +
                '}';
    }
}
