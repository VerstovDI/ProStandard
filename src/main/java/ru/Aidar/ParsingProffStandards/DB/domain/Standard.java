package ru.Aidar.ParsingProffStandards.DB.domain;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "proff_standards", schema = "proff")
public class Standard {
    @Id
    @Column(name = "proff_standards_id", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code_kind_professional_activity")
    private String codeKindProfessionalActivity;

    @Column(name = "date_of_approval", nullable = false)
    private Date dateOfApproval;

    @Column(name = "date_of_downloading", nullable = false)
    private Date dateOfDownloading;

    @Column(name = "name_professional_standard", nullable = false)
    private String nameProfessionalStandard;

    @Column(name = "registration_number", nullable = false)
    private Integer registrationNumber;

    @Column(name = "order_number", nullable = false)
    private String orderNumber;

    @Column(name = "kind_professional_activity", nullable = false)
    private String kindProfessionalActivity;

    @Column(name = "purpose_kind_professional_activity", nullable = false)
    private String purposeKindProfessionalActivity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = " id_resource", nullable = false)
    private Resource resource;


    public Standard() {
    }

    public Standard(String codeKindProfessionalActivity, Date dateOfApproval,
                    Date dateOfDownloading, String nameProfessionalStandard,
                    Integer registrationNumber, String orderNumber, String kindProfessionalActivity,
                    String purposeKindProfessionalActivity, Resource resource) {
        this.codeKindProfessionalActivity = codeKindProfessionalActivity;
        this.dateOfApproval = dateOfApproval;
        this.dateOfDownloading = dateOfDownloading;
        this.nameProfessionalStandard = nameProfessionalStandard;
        this.registrationNumber = registrationNumber;
        this.orderNumber = orderNumber;
        this.kindProfessionalActivity = kindProfessionalActivity;
        this.purposeKindProfessionalActivity = purposeKindProfessionalActivity;
        this.resource = resource;
    }

    public String getCodeKindProfessionalActivity() {
        return codeKindProfessionalActivity;
    }

    public void setCodeKindProfessionalActivity(String codeKindProfessionalActivity) {
        this.codeKindProfessionalActivity = codeKindProfessionalActivity;
    }

    public Date getDateOfApproval() {
        return dateOfApproval;
    }

    public void setDateOfApproval(Date dateOfApproval) {
        this.dateOfApproval = dateOfApproval;
    }

    public Date getDateOfDownloading() {
        return dateOfDownloading;
    }

    public void setDateOfDownloading(Date dateOfDownloading) {
        this.dateOfDownloading = dateOfDownloading;
    }

    public String getNameProfessionalStandard() {
        return nameProfessionalStandard;
    }

    public void setNameProfessionalStandard(String nameProfessionalStandard) {
        this.nameProfessionalStandard = nameProfessionalStandard;
    }

    public Integer getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(Integer registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getKindProfessionalActivity() {
        return kindProfessionalActivity;
    }

    public void setKindProfessionalActivity(String kindProfessionalActivity) {
        this.kindProfessionalActivity = kindProfessionalActivity;
    }

    public String getPurposeKindProfessionalActivity() {
        return purposeKindProfessionalActivity;
    }

    public void setPurposeKindProfessionalActivity(String purposeKindProfessionalActivity) {
        this.purposeKindProfessionalActivity = purposeKindProfessionalActivity;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Standard)) return false;
        Standard standard = (Standard) o;
        return Objects.equals(codeKindProfessionalActivity, standard.codeKindProfessionalActivity) && Objects.equals(dateOfApproval, standard.dateOfApproval) && Objects.equals(dateOfDownloading, standard.dateOfDownloading) && Objects.equals(nameProfessionalStandard, standard.nameProfessionalStandard) && Objects.equals(registrationNumber, standard.registrationNumber) && Objects.equals(orderNumber, standard.orderNumber) && Objects.equals(kindProfessionalActivity, standard.kindProfessionalActivity) && Objects.equals(purposeKindProfessionalActivity, standard.purposeKindProfessionalActivity) && Objects.equals(resource, standard.resource);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codeKindProfessionalActivity, dateOfApproval, dateOfDownloading, nameProfessionalStandard, registrationNumber, orderNumber, kindProfessionalActivity, purposeKindProfessionalActivity, resource);
    }

    @Override
    public String toString() {
        return "Standards{" +
                "codeKindProfessionalActivity='" + codeKindProfessionalActivity + '\'' +
                ", dateOfApproval=" + dateOfApproval +
                ", dateOfDownloading=" + dateOfDownloading +
                ", nameProfessionalStandard='" + nameProfessionalStandard + '\'' +
                ", registrationNumber=" + registrationNumber +
                ", orderNumber='" + orderNumber + '\'' +
                ", kindProfessionalActivity='" + kindProfessionalActivity + '\'' +
                ", purposeKindProfessionalActivity='" + purposeKindProfessionalActivity + '\'' +
                ", resource=" + resource +
                '}';
    }
}
