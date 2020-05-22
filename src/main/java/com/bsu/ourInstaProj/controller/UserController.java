package com.bsu.ourInstaProj.controller;

import com.bsu.ourInstaProj.entity.User;
import com.bsu.ourInstaProj.entity.response.UserVO;
import com.bsu.ourInstaProj.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(path = {"/registration"}, method = RequestMethod.POST)
    public ResponseEntity<User> addUser(@RequestBody User newUser) {
        userService.addUser(newUser);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @RequestMapping(path = {"/profile"}, method = RequestMethod.GET)
    public ResponseEntity<UserVO> getUser() {
        UserVO userVO = userService.convertToVO(userService.findCurrentUser());
        return new ResponseEntity<>(userVO, HttpStatus.OK);
    }
}
