package com.bsu.ourInstaProj.service;

import com.bsu.ourInstaProj.dao.UserRepository;
import com.bsu.ourInstaProj.entity.User;

import com.bsu.ourInstaProj.entity.response.UserVO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    private ModelMapper modelMapper = new ModelMapper();


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

    @Transactional
    public UserVO finUserByUsername(String username) {
        return convertToVO(userRepository.findByUsername(username));
    }

    @Transactional
    public User findCurrentUser() {
        SecurityContext context = SecurityContextHolder.getContext();

        String login = context.getAuthentication().getName();
        if (!login.isEmpty()) {
            return userRepository.findByUsername(login);
        } else {
            return userRepository.findByUsername("aliko");
        }
    }

    public UserVO convertToVO(User user) {
        UserVO userVO = modelMapper.map(user, UserVO.class);
        return userVO;
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
