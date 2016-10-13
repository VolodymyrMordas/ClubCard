package com.clubcard.entities;

import com.clubcard.generic.entity.GenericEntity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by volodymyrmordas on 10/27/15.
 */
@Entity
@javax.persistence.Table(name = "m_company", schema = "public", catalog = "clubcard")
public class Company extends GenericEntity<Long> implements Serializable {
    private Long id;

    @Id
    @javax.persistence.Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String name;

    @Basic
    @javax.persistence.Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String slug;

    @Basic
    @javax.persistence.Column(name = "slug")
    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    private String email;

    @Basic
    @javax.persistence.Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String url;

    @Basic
    @javax.persistence.Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String phone;

    @Basic
    @javax.persistence.Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    private String contactDetails;

    @Basic
    @javax.persistence.Column(name = "contact_details")
    public String getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }

    private String key;

    @Basic
    @javax.persistence.Column(name = "key")
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    private Integer countryId;

    @Basic
    @javax.persistence.Column(name = "country_id")
    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    private Integer cityId;

    @Basic
    @javax.persistence.Column(name = "city_id")
    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    private Integer postcodeId;

    @Basic
    @javax.persistence.Column(name = "postcode_id")
    public Integer getPostcodeId() {
        return postcodeId;
    }

    public void setPostcodeId(Integer postcodeId) {
        this.postcodeId = postcodeId;
    }

    private String addressline1;

    @Basic
    @javax.persistence.Column(name = "addressline1")
    public String getAddressline1() {
        return addressline1;
    }

    public void setAddressline1(String addressline1) {
        this.addressline1 = addressline1;
    }

    private String addressline2;

    @Basic
    @javax.persistence.Column(name = "addressline2")
    public String getAddressline2() {
        return addressline2;
    }

    public void setAddressline2(String addressline2) {
        this.addressline2 = addressline2;
    }

    private Short status;

    @Basic
    @javax.persistence.Column(name = "status")
    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    private String description;

    @Basic
    @javax.persistence.Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private Timestamp createdAt;

    @Basic
    @javax.persistence.Column(name = "created_at")
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    private Timestamp updatedAt;

    @Basic
    @javax.persistence.Column(name = "updated_at")
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    private short type;

    @Basic
    @javax.persistence.Column(name = "type")
    public short getType() {
        return type;
    }

    public void setType(short type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Company company = (Company) o;

        if (id != company.id) return false;
        if (type != company.type) return false;
        if (name != null ? !name.equals(company.name) : company.name != null) return false;
        if (slug != null ? !slug.equals(company.slug) : company.slug != null) return false;
        if (email != null ? !email.equals(company.email) : company.email != null) return false;
        if (url != null ? !url.equals(company.url) : company.url != null) return false;
        if (phone != null ? !phone.equals(company.phone) : company.phone != null) return false;
        if (contactDetails != null ? !contactDetails.equals(company.contactDetails) : company.contactDetails != null)
            return false;
        if (key != null ? !key.equals(company.key) : company.key != null) return false;
        if (countryId != null ? !countryId.equals(company.countryId) : company.countryId != null) return false;
        if (cityId != null ? !cityId.equals(company.cityId) : company.cityId != null) return false;
        if (postcodeId != null ? !postcodeId.equals(company.postcodeId) : company.postcodeId != null) return false;
        if (addressline1 != null ? !addressline1.equals(company.addressline1) : company.addressline1 != null)
            return false;
        if (addressline2 != null ? !addressline2.equals(company.addressline2) : company.addressline2 != null)
            return false;
        if (status != null ? !status.equals(company.status) : company.status != null) return false;
        if (description != null ? !description.equals(company.description) : company.description != null) return false;
        if (createdAt != null ? !createdAt.equals(company.createdAt) : company.createdAt != null) return false;
        if (updatedAt != null ? !updatedAt.equals(company.updatedAt) : company.updatedAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (slug != null ? slug.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (contactDetails != null ? contactDetails.hashCode() : 0);
        result = 31 * result + (key != null ? key.hashCode() : 0);
        result = 31 * result + (countryId != null ? countryId.hashCode() : 0);
        result = 31 * result + (cityId != null ? cityId.hashCode() : 0);
        result = 31 * result + (postcodeId != null ? postcodeId.hashCode() : 0);
        result = 31 * result + (addressline1 != null ? addressline1.hashCode() : 0);
        result = 31 * result + (addressline2 != null ? addressline2.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        result = 31 * result + (int) type;
        return result;
    }
}
