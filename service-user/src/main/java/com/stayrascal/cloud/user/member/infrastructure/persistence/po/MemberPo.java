package com.stayrascal.cloud.user.member.infrastructure.persistence.po;

import com.stayrascal.cloud.common.jpa.BasePo;

import com.stayrascal.cloud.user.member.contract.Gender;
import com.stayrascal.cloud.user.member.contract.MemberStatus;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;


@Entity
@Table(name = "MEMBER")
public class MemberPo extends BasePo {
    @Column(name = "nickname", nullable = false, length = 64)
    private String nickname;

    @Column(name = "gender", length = 32)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "email", length = 64)
    private String email;

    @Column(name = "mobile", length = 16)
    private String mobile;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 32)
    private MemberStatus status;

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
}

