package ru.selenide.DB.domain;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "possible_job_titles", schema = "proff")
public class PossibleJobTitles {
    @Id
    @Column(name = "id_possible_job_title", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_gwf", nullable = false)
    private GeneralizedWorkFunctions generalizedWorkFunctions;


    public PossibleJobTitles() {
    }


    public PossibleJobTitles(Long id, String title, GeneralizedWorkFunctions generalizedWorkFunctions) {
        this.id = id;
        this.title = title;
        this.generalizedWorkFunctions = generalizedWorkFunctions;
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

    public GeneralizedWorkFunctions getGeneralizedWorkFunctions() {
        return generalizedWorkFunctions;
    }

    public void setGeneralizedWorkFunctions(GeneralizedWorkFunctions generalizedWorkFunctions) {
        this.generalizedWorkFunctions = generalizedWorkFunctions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PossibleJobTitles)) return false;
        PossibleJobTitles that = (PossibleJobTitles) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(generalizedWorkFunctions, that.generalizedWorkFunctions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, generalizedWorkFunctions);
    }

    @Override
    public String toString() {
        return "PossibleJobTitles{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", generalizedWorkFunctions=" + generalizedWorkFunctions +
                '}';
    }
}
