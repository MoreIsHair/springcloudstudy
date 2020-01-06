package com.yy.user.controller;

import com.yy.common.core.vo.UserVo;
import com.yy.user.api.feign.module.Menu;
import com.yy.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author YY
 * @date 2020/1/6
 * @description
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/v1/user/findUserByIdentifier/{identifier}")
    public UserVo findUserByIdentifier(@PathVariable("identifier") String identifier){
       return null;
    }


    @GetMapping("/v1/menu/findMenuByRole/{role}")
    public List<Menu> findMenuByRole(@PathVariable("role") String role){
        return null;
    }

}
