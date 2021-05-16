package ru.selenide.DB.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "generalized_work_functions", schema = "proff")
public class GeneralizedWorkFunctions {
    @Id
    @Column(name = "id_gwf", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "сode_gwf", nullable = false)
    private String сodeGwf;

    @Column(name = "name_gwf", nullable = false)
    private String nameGwf;

    @Column(name = "level_of_qualification", nullable = false)
    private Integer levelOfQualification;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "code_kind_professional_activity", nullable = false)
    private Standard standard;

    public GeneralizedWorkFunctions() {
    }


    public GeneralizedWorkFunctions(Long id, String сodeGwf, String nameGwf, Integer levelOfQualification, Standard standard) {
        this.id = id;
        this.сodeGwf = сodeGwf;
        this.nameGwf = nameGwf;
        this.levelOfQualification = levelOfQualification;
        this.standard = standard;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getСodeGwf() {
        return сodeGwf;
    }

    public void setСodeGwf(String сodeGwf) {
        this.сodeGwf = сodeGwf;
    }

    public String getNameGwf() {
        return nameGwf;
    }

    public void setNameGwf(String nameGwf) {
        this.nameGwf = nameGwf;
    }

    public Integer getLevelOfQualification() {
        return levelOfQualification;
    }

    public void setLevelOfQualification(Integer levelOfQualification) {
        this.levelOfQualification = levelOfQualification;
    }

    public Standard getStandard() {
        return standard;
    }

    public void setStandard(Standard standard) {
        this.standard = standard;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GeneralizedWorkFunctions)) return false;
        GeneralizedWorkFunctions that = (GeneralizedWorkFunctions) o;
        return Objects.equals(id, that.id) && Objects.equals(сodeGwf, that.сodeGwf) && Objects.equals(nameGwf, that.nameGwf) && Objects.equals(levelOfQualification, that.levelOfQualification) && Objects.equals(standard, that.standard);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, сodeGwf, nameGwf, levelOfQualification, standard);
    }

    @Override
    public String toString() {
        return "GeneralizedWorkFunctions{" +
                "id=" + id +
                ", сodeGwf='" + сodeGwf + '\'' +
                ", nameGwf='" + nameGwf + '\'' +
                ", levelOfQualification=" + levelOfQualification +
                ", standard=" + standard +
                '}';
    }
}
