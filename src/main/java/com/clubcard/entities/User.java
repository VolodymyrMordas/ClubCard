package com.clubcard.entities;

import com.clubcard.generic.entity.GenericEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@NamedQueries({
        @NamedQuery(name = "User.findByAuthKey", query = "select u from User u where u.authKey = :authKey"),
        @NamedQuery(name = "User.findBySocnetType", query = "select u from User u where u.socnetUid = :socnetUid and u.socnetType = :socnetType")
})
@javax.persistence.Table(name = "m_user", schema = "public", catalog = "clubcard")
public class User extends GenericEntity<Long> implements Serializable {

    public static Short S_NEW_USER = 0;
    public static Short S_REGISTERED = 1;
    public static Short S_DELETED = 2;


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @javax.persistence.Column(name = "first_name")
    @JsonProperty(value = "first_name")
    private String firstName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonProperty(value = "last_name")
    @javax.persistence.Column(name = "last_name")
    private String lastName;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    private Short sex;

    @Basic
    @javax.persistence.Column(name = "sex")
    public Short getSex() {
        return sex;
    }

    public void setSex(Short sex) {
        this.sex = sex;
    }

    @Temporal(value = TemporalType.DATE)
    private Date birthday;

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @JsonProperty(value = "socnet_uid")
    @javax.persistence.Column(name = "socnet_uid")
    private String socnetUid;

    public String getSocnetUid() {
        return socnetUid;
    }

    public void setSocnetUid(String socnetUid) {
        this.socnetUid = socnetUid;
    }

    @JsonProperty(value = "socnet_type")
    @javax.persistence.Column(name = "socnet_type")
    private Short socnetType;

    public Short getSocnetType() {
        return socnetType;
    }

    public void setSocnetType(Short socnetType) {
        this.socnetType = socnetType;
    }

    private String email;

    @javax.persistence.Column(name = "email")
    @NotNull
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    @JsonProperty(value = "login_ip")
    @javax.persistence.Column(name = "login_ip")
    private String loginIp;

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    @javax.persistence.Column(name = "login_date")
    @JsonProperty(value = "login_date")
    private Timestamp loginDate;

    public Timestamp getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Timestamp loginDate) {
        this.loginDate = loginDate;
    }

    private String salt;

    @Basic
    @javax.persistence.Column(name = "salt")
//    @JsonIgnore
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    private String password;

    @Basic
    @javax.persistence.Column(name = "password")
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @javax.persistence.Column(name = "auth_key")
    @JsonProperty(value = "auth_key")
    private String authKey;

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }

    private Double lng;

    @Basic
    @javax.persistence.Column(name = "lng")
    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    private Double lat;

    @Basic
    @javax.persistence.Column(name = "lat")
    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
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

    @JsonProperty(value = "created_at")
    @javax.persistence.Column(name = "created_at")
    private Timestamp createdAt;

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @javax.persistence.Column(name = "updated_at")
    @JsonProperty(value = "updated_at")
    private Timestamp updatedAt;

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @javax.persistence.Column(name = "logout_date")
    @JsonProperty(value = "logout_date")
    private Timestamp logoutDate;

    public Timestamp getLogoutDate() {
        return logoutDate;
    }

    public void setLogoutDate(Timestamp logoutDate) {
        this.logoutDate = logoutDate;
    }

    @PrePersist
    public void prePersist() {
        this.setLoginDate(new Timestamp((new Date()).getTime()));
        this.setCreatedAt(new Timestamp((new Date()).getTime()));
        this.setUpdatedAt(new Timestamp((new Date()).getTime()));
    }

    @PreUpdate
    public void preUpdate() {
        this.setUpdatedAt(new Timestamp((new Date()).getTime()));
    }

    public static String generatePasswordHash(String salt, String passwd){
        String passwordHash = null;

        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update((passwd + salt).getBytes("UTF-8"));
            passwordHash = new BigInteger(1, crypt.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return passwordHash;
    }

    public boolean checkPassword(String password){
        boolean correct = false;

        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update((password + getSalt()).getBytes("UTF-8"));
            String passwordHash = new BigInteger(1, crypt.digest()).toString(16);
            if(passwordHash.equals(this.getPassword())){
                correct = true;
            }
        }  catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return correct;
    }
}
