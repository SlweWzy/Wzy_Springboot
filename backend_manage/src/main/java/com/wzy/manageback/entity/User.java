package com.wzy.manageback.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
@TableName(value = "sys_user")  //使用mybatis-plus默认用数据库的表名查找表，通过@TableName(value = "sys_user")指定存放user用户的表
public class User {

    @TableId(value = "id")  //主键指定一个tableid （mybatis-plus）
    private Integer id;

    private String username;

    //Json忽视password
    @JsonIgnore
    private String password;

    @TableField(value = "nickname")  //指定数据库的字段名称
    private String nickname;

    private String email;
    private String phone;
    private String address;
}
