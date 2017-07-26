package com.stayrascal.cloud.user.member.domain.entity;

import com.stayrascal.cloud.user.member.contract.Gender;
import com.stayrascal.cloud.user.member.contract.MemberStatus;

import org.joda.time.DateTime;

import java.util.Date;

public class Member {
    private String id;

    private String nickname;

    private Gender gender;

    private Date birthday;

    private String email;

    private String mobile;

    private MemberStatus status;

    private Date timeCreated;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public MemberStatus getStatus() {
        return status;
    }

    public void setStatus(MemberStatus status) {
        this.status = status;
    }

    public Date getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }

    public int getAge() {
        int birthYear = new DateTime(getBirthday()).getYear();
        int currentYear = DateTime.now().getYear();
        return currentYear - birthYear;
    }
}
