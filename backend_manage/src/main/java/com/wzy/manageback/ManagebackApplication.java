package com.wzy.manageback;

import com.wzy.manageback.entity.User;
import com.wzy.manageback.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;



@SpringBootApplication
public class ManagebackApplication {


    public static void main(String[] args) {
        SpringApplication.run(ManagebackApplication.class, args);
    }


}
