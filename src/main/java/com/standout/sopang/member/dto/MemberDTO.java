package com.standout.sopang.member.dto;

import lombok.*;
import org.springframework.stereotype.Component;


@Getter
@Setter
@ToString
@Component("memberDTO")
public class MemberDTO {

    private String member_name;
    private String member_id;
    private String member_pw;
    private String hp1;
    private String zipcode;
    private String address;
    private String subaddress;
    private String sopang_money;
    private String joinDate;
    private String del_yn;

}

