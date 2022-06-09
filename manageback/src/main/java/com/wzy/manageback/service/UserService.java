package com.wzy.manageback.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wzy.manageback.entity.User;
import com.wzy.manageback.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@service是把service注入到springboot容器中
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public int save (User user) {

        if(user.getId() == null) {  //user没有id，则表示是新增
            return userMapper.insert(user);
        }else {  //否则为更新
            return userMapper.update(user);
        }

    }

}
