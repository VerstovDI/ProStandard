package ru.prostandard.model.profstandards;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "possible_job_titles", schema = "proff")
public class PossibleJobTitle {
    @Id
    @Column(name = "id_possible_job_title", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_gwf", nullable = false)
    private GeneralizedWorkFunction generalizedWorkFunction;


    public PossibleJobTitle() {
    }


    public PossibleJobTitle(String title, GeneralizedWorkFunction generalizedWorkFunction) {
        this.title = title;
        this.generalizedWorkFunction = generalizedWorkFunction;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public GeneralizedWorkFunction getGeneralizedWorkFunctions() {
        return generalizedWorkFunction;
    }

    public void setGeneralizedWorkFunctions(GeneralizedWorkFunction generalizedWorkFunction) {
        this.generalizedWorkFunction = generalizedWorkFunction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PossibleJobTitle)) return false;
        PossibleJobTitle that = (PossibleJobTitle) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(generalizedWorkFunction, that.generalizedWorkFunction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, generalizedWorkFunction);
    }

    @Override
    public String toString() {
        return "PossibleJobTitles{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", generalizedWorkFunctions=" + generalizedWorkFunction +
                '}';
    }
}