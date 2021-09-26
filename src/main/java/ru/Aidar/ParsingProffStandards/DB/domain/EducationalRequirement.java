package ru.Aidar.ParsingProffStandards.DB.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "educational_requirements", schema = "proff")
public class EducationalRequirement {
    @Id
    @Column(name = " id_educational_requirement", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "educational_requirement", nullable = false)
    private String educationalRequirement;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_gwf", nullable = false)
    private GeneralizedWorkFunction generalizedWorkFunction;

    public EducationalRequirement() {
    }

    public EducationalRequirement(String educationalRequirement, GeneralizedWorkFunction generalizedWorkFunction) {
        this.educationalRequirement = educationalRequirement;
        this.generalizedWorkFunction = generalizedWorkFunction;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEducationalRequirement() {
        return educationalRequirement;
    }

    public void setEducationalRequirement(String educationalRequirement) {
        this.educationalRequirement = educationalRequirement;
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
        if (!(o instanceof EducationalRequirement)) return false;
        EducationalRequirement that = (EducationalRequirement) o;
        return Objects.equals(id, that.id) && Objects.equals(educationalRequirement, that.educationalRequirement) && Objects.equals(generalizedWorkFunction, that.generalizedWorkFunction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, educationalRequirement, generalizedWorkFunction);
    }

    @Override
    public String toString() {
        return "EducationalRequirements{" +
                "id=" + id +
                ", educationalRequirement='" + educationalRequirement + '\'' +
                ", generalizedWorkFunctions=" + generalizedWorkFunction +
                '}';
    }
}
