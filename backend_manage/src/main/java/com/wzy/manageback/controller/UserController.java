package com.wzy.manageback.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
    private UserService userService;


    //requestbody作用是前台传入的json对象可以转换成user对象
    //@PostMapping("/insert")接口路径
    //插入信息或者更新信息
    @PostMapping("/insert")
    public boolean save(@RequestBody  User user){
       return userService.saveUser(user);
    }


    //查看全部信息
    @GetMapping("/searchAll")
    public List<User> searchAll(){

        return userService.list() ;
    }

    //删除按id
    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable Integer id){
        return userService.removeById(id);
    }

    //批量删除
    @PostMapping("/deleteMuch")
    public boolean deleteMuch(@RequestBody List<Integer> ids){  //类似传[1,2,3]这样的数据
        return userService.removeBatchByIds(ids);
    }

    //分页查看信息
    //@RequestParam相当于接收接口路径的  eg: /user/searchByPage/ ?pageNum=1&pageSize=10
    //limit第一个参数=（pageNum-1）*pageSize
    //    @GetMapping("/searchByPage")
    //    public Map<String, Object> findPage(@RequestParam Integer pageNum,
    //                                        @RequestParam Integer pageSize,
    //                                        @RequestParam String username){
    //
    //        pageNum = (pageNum-1) * pageSize;
    //        List<User> data = userMapper.selectPage(pageNum, pageSize,username);
    //        Integer total = userMapper.selectTotal();
    //        Map<String , Object> res = new HashMap<>();
    //        res.put("data",data);
    //        res.put("total",total);
    //        return res;
    //
    //    }

    //分页查询 mybatis-plus方式实现
    //(defaultValue = "")默认值为空
    @GetMapping("/searchByPage")
    public IPage<User> findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                                @RequestParam(defaultValue = "") String username,
                                @RequestParam(defaultValue = "") String email,
                                @RequestParam(defaultValue = "") String address){

        IPage<User> page = new Page<>(pageNum, pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        if (!"".equals(username)){
            queryWrapper.like("username",username);
        }

        if (!"".equals(email)){
            queryWrapper.like("email",email);
        }

        if (!"".equals(address)){
            queryWrapper.like("address",address);
        }

        queryWrapper.orderByDesc("id");

        IPage<User> userIPage = userService.page(page,queryWrapper);
        return userIPage;

    }
}
