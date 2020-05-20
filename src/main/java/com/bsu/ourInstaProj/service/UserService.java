package com.bsu.ourInstaProj.service;

import com.bsu.ourInstaProj.dao.UserRepository;
import com.bsu.ourInstaProj.entity.Board;
import com.bsu.ourInstaProj.entity.User;

import java.util.List;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User newUser) {
        //add to newBoard id of currentUser
        return userRepository.save(newUser);
    }

   /* public User signUpUser(User user) {
        User checkedUser = userRepository.getByUsername(user.getUsername());
        if (checkedUser.getPassword().equals(user.getPassword())){
            return checkedUser;
        }
        return ;
    }*/

    public List<Board> getAllBoards(Long userId) {
        return userRepository.getById(userId).getBoards();
    }
}
