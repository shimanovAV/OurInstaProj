package com.bsu.ourInstaProj.service;

import com.bsu.ourInstaProj.dao.UserRepository;
import com.bsu.ourInstaProj.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        Set<GrantedAuthority> authorities = new HashSet<>();

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    public boolean addUser(User newUser) {
        User userFromDB = userRepository.findByUsername(newUser.getUsername());
        if (userFromDB != null) {
            return false;
        }
        newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
        userRepository.save(newUser);
        return true;
    }

    public User login(User user) {
        User userFromDB = userRepository.findByUsername(user.getUsername());
        PasswordEncoder passwordEnocder = new BCryptPasswordEncoder();
        if (passwordEnocder.matches(user.getPassword(), userFromDB.getPassword())){
            return userFromDB;
        } else {
            return userRepository.findByUsername("aliko");
        }
    }

    public User finUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User findCurrentUser() {
        SecurityContext context = SecurityContextHolder.getContext();

        String login = context.getAuthentication().getName();
        if (!login.isEmpty()) {
            return userRepository.findByUsername(login);
        } else {
            return userRepository.findByUsername("aliko");
        }
    }
/* <form method="POST" action="/login">
    <h2>Вход в систему</h2>
    <div>
      <input name="username" type="text" placeholder="Username"
    autofocus="true"/>
      <input name="password" type="password" placeholder="Password"/>*/
   /* public User signUpUser(User user) {
        User checkedUser = userRepository.getByUsername(user.getUsername());
        if (checkedUser.getPassword().equals(user.getPassword())){
            return checkedUser;
        }
        return ;
    }*/
}
