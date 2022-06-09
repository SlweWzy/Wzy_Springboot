package com.wzy.manageback.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class User {

    private Integer id;
    private String username;

    //Json忽视password
    @JsonIgnore
    private String password;

    private String nickname;
    private String email;
    private String phone;
    private String address;
}
