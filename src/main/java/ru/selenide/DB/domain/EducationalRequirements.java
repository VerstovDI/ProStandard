package ru.selenide.DB.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "educational_requirements", schema = "proff")
public class EducationalRequirements {
    @Id
    @Column(name = " id_educational_requirement", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "educational_requirement", nullable = false)
    private String educationalRequirement;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_gwf", nullable = false)
    private GeneralizedWorkFunctions generalizedWorkFunctions;

    public EducationalRequirements() {
    }

    public EducationalRequirements(Long id, String educationalRequirement, GeneralizedWorkFunctions generalizedWorkFunctions) {
        this.id = id;
        this.educationalRequirement = educationalRequirement;
        this.generalizedWorkFunctions = generalizedWorkFunctions;
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

    public GeneralizedWorkFunctions getGeneralizedWorkFunctions() {
        return generalizedWorkFunctions;
    }

    public void setGeneralizedWorkFunctions(GeneralizedWorkFunctions generalizedWorkFunctions) {
        this.generalizedWorkFunctions = generalizedWorkFunctions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EducationalRequirements)) return false;
        EducationalRequirements that = (EducationalRequirements) o;
        return Objects.equals(id, that.id) && Objects.equals(educationalRequirement, that.educationalRequirement) && Objects.equals(generalizedWorkFunctions, that.generalizedWorkFunctions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, educationalRequirement, generalizedWorkFunctions);
    }

    @Override
    public String toString() {
        return "EducationalRequirements{" +
                "id=" + id +
                ", educationalRequirement='" + educationalRequirement + '\'' +
                ", generalizedWorkFunctions=" + generalizedWorkFunctions +
                '}';
    }
}
