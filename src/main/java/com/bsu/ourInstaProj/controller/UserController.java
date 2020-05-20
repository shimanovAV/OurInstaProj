package com.bsu.ourInstaProj.controller;

import com.bsu.ourInstaProj.entity.Board;
import com.bsu.ourInstaProj.entity.User;
import com.bsu.ourInstaProj.entity.response.BoardResponse;
import com.bsu.ourInstaProj.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(path = {"/signup"}, method = RequestMethod.POST)
    public ResponseEntity<User> addUser(@RequestBody User newUser) {
        User user = userService.addUser(newUser);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /*@RequestMapping(path = {"/login"}, method = RequestMethod.POST)
    public ResponseEntity<User> signUpUser(@RequestBody User user) {
        User response = userService.signUpUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }*/

    @RequestMapping(path = {"/user/{userId}/boards"}, method = RequestMethod.GET)
    public ResponseEntity<BoardResponse> getBoardUsers(final @PathVariable Long userId) {
        List<Board> boards = userService.getAllBoards(userId);
        return new ResponseEntity<>(new BoardResponse(boards), HttpStatus.OK);
    }

}
