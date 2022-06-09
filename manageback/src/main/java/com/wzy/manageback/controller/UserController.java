package com.wzy.manageback.controller;

import com.wzy.manageback.entity.User;
import com.wzy.manageback.mapper.UserMapper;
import com.wzy.manageback.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.nio.cs.US_ASCII;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//访问路径/user拼接下面的getmapping上的地址 eg:/user/searchAll
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;


    //requestbody作用是前台传入的json对象可以转换成user对象
    //@PostMapping("/insert")接口路径
    //插入信息或者更新信息
    @PostMapping("/insert")
    public Integer save(@RequestBody  User user){
       return userService.save(user);
    }


    //查看全部信息
    @GetMapping("/searchAll")
    public List<User> searchAll(){

        return userMapper.findAll() ;
    }

    //删除按id
    @DeleteMapping("/delete/{id}")
    public Integer delete(@PathVariable Integer id){
        return userMapper.deleteById(id);
    }

    //分页查看信息
    //@RequestParam相当于接收接口路径的  eg: /user/searchByPage/ ?pageNum=1&pageSize=10
    //limit第一个参数=（pageNum-1）*pageSize
    @GetMapping("/searchByPage")
    public Map<String, Object> findPage(@RequestParam Integer pageNum,
                                        @RequestParam Integer pageSize,
                                        @RequestParam String username){

        pageNum = (pageNum-1) * pageSize;
        List<User> data = userMapper.selectPage(pageNum, pageSize,username);
        Integer total = userMapper.selectTotal();
        Map<String , Object> res = new HashMap<>();
        res.put("data",data);
        res.put("total",total);
        return res;

    }

}
