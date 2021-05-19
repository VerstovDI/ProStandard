package ru.selenide.DB.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "particular_work_functions", schema = "proff")
public class ParticularWorkFunction {
    @Id
    @Column(name = " id_particular_work_function", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "сode_wf", nullable = false)
    private String сodeWf;

    @Column(name = "name_wf", nullable = false)
    private String nameWf;

    @Column(name = "sub_qualification", nullable = false)
    private Integer subQualification;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_gwf", nullable = false)
    private GeneralizedWorkFunction generalizedWorkFunction;

    public ParticularWorkFunction() {
    }

    public ParticularWorkFunction( String сodeWf, String nameWf, Integer subQualification, GeneralizedWorkFunction generalizedWorkFunction) {
        this.сodeWf = сodeWf;
        this.nameWf = nameWf;
        this.subQualification = subQualification;
        this.generalizedWorkFunction = generalizedWorkFunction;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getСodeWf() {
        return сodeWf;
    }

    public void setСodeWf(String сodeWf) {
        this.сodeWf = сodeWf;
    }

    public String getNameWf() {
        return nameWf;
    }

    public void setNameWf(String nameWf) {
        this.nameWf = nameWf;
    }

    public Integer getSubQualification() {
        return subQualification;
    }

    public void setSubQualification(Integer subQualification) {
        this.subQualification = subQualification;
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
        if (!(o instanceof ParticularWorkFunction)) return false;
        ParticularWorkFunction that = (ParticularWorkFunction) o;
        return Objects.equals(id, that.id) && Objects.equals(сodeWf, that.сodeWf) && Objects.equals(nameWf, that.nameWf) && Objects.equals(subQualification, that.subQualification) && Objects.equals(generalizedWorkFunction, that.generalizedWorkFunction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, сodeWf, nameWf, subQualification, generalizedWorkFunction);
    }

    @Override
    public String toString() {
        return "ParticularWorkFunctions{" +
                "id=" + id +
                ", сodeWf='" + сodeWf + '\'' +
                ", nameWf='" + nameWf + '\'' +
                ", subQualification=" + subQualification +
                ", generalizedWorkFunctions=" + generalizedWorkFunction +
                '}';
    }
}
