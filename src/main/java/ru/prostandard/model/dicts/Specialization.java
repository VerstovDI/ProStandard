package ru.prostandard.model.dicts;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "dict_specialization", schema = "proff")
public class Specialization implements Serializable {

    @Id
    @Column(name = "spec_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "spec_code")
    private String specializationCode;

    @Column(name = "spec_description")
    private String specializationDescription;

    public Specialization() {
    }

    public Specialization(Long id, String specializationCode, String specializationDescription) {
        this.id = id;
        this.specializationCode = specializationCode;
        this.specializationDescription = specializationDescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpecializationCode() {
        return specializationCode;
    }

    public void setSpecializationCode(String specializationCode) {
        this.specializationCode = specializationCode;
    }

    public String getSpecializationDescription() {
        return specializationDescription;
    }

    public void setSpecializationDescription(String specializationDescription) {
        this.specializationDescription = specializationDescription;
    }

    @Override
    public String toString() {
        return "Specialization{" +
                "id=" + id +
                ", specializationCode='" + specializationCode + '\'' +
                ", specializationDescription='" + specializationDescription + '\'' +
                '}';
    }
}
