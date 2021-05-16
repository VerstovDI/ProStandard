package ru.selenide.DB.domain;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "employment_group_okved", schema = "proff")
public class EmploymentGroupOkved {
    @Id
    @Column(name="сode_okved")
    private String codeOkved ;

    @Column(name="name_okved",nullable=false)
    private String  nameOkved;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="code_kind_professional_activity",nullable=false)
    private Standard  standard;


    public EmploymentGroupOkved() {
    }

    public EmploymentGroupOkved(String codeOkved, String nameOkved, Standard standard) {
        this.codeOkved = codeOkved;
        this.nameOkved = nameOkved;
        this.standard = standard;
    }

    public String getCodeOkved() {
        return codeOkved;
    }

    public void setCodeOkved(String codeOkved) {
        this.codeOkved = codeOkved;
    }

    public String getNameOkved() {
        return nameOkved;
    }

    public void setNameOkved(String nameOkved) {
        this.nameOkved = nameOkved;
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
        if (!(o instanceof EmploymentGroupOkved)) return false;
        EmploymentGroupOkved that = (EmploymentGroupOkved) o;
        return Objects.equals(codeOkved, that.codeOkved) && Objects.equals(nameOkved, that.nameOkved) && Objects.equals(standard, that.standard);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codeOkved, nameOkved, standard);
    }

    @Override
    public String toString() {
        return "EmploymentGroupOkved{" +
                "codeOkved=" + codeOkved +
                ", nameOkved='" + nameOkved + '\'' +
                ", standard=" + standard +
                '}';
    }
}
