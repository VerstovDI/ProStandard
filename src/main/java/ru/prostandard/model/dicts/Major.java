package ru.prostandard.model.dicts;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "dict_major", schema = "proff")
public class Major implements Serializable {

    @Id
    @Column(name = "major_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "major_description")
    private String majorDescription;

    public Major() {

    }

    public Major(Long id, String majorDescription) {
        this.id = id;
        this.majorDescription = majorDescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMajorDescription() {
        return majorDescription;
    }

    public void setMajorDescription(String majorDescription) {
        this.majorDescription = majorDescription;
    }

    @Override
    public String toString() {
        return "Major{" +
                "id=" + id +
                ", majorDescription='" + majorDescription + '\'' +
                '}';
    }
}
