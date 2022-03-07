package ru.Aidar.ParsingProffStandards.DB.domain;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "employment_group_okz", schema = "proff")
public class EmploymentGroupOkz {
    @Id
    @Column(name = "сode_okz")
    private Integer codeOkz;

    @Column(name = "name_okz", nullable = false)
    private String nameOkz;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proff_standards_id", nullable = false)
    private Standard standard;

    public EmploymentGroupOkz() {
    }

    public EmploymentGroupOkz(Integer codeOkz, String nameOkz, Standard standard) {
        this.codeOkz = codeOkz;
        this.nameOkz = nameOkz;
        this.standard = standard;
    }

    public Integer getCodeOkz() {
        return codeOkz;
    }

    public void setCodeOkz(Integer codeOkz) {
        this.codeOkz = codeOkz;
    }

    public String getNameOkz() {
        return nameOkz;
    }

    public void setNameOkz(String nameOkz) {
        this.nameOkz = nameOkz;
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
        if (!(o instanceof EmploymentGroupOkz)) return false;
        EmploymentGroupOkz that = (EmploymentGroupOkz) o;
        return Objects.equals(codeOkz, that.codeOkz) && Objects.equals(nameOkz, that.nameOkz) && Objects.equals(standard, that.standard);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codeOkz, nameOkz, standard);
    }

    @Override
    public String toString() {
        return "EmploymentGroupOkz{" +
                "codeOkz=" + codeOkz +
                ", nameOkz='" + nameOkz + '\'' +
                ", standard=" + standard +
                '}';
    }
}
