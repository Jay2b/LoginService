package com.login.service;

import com.login.model.Role;
import com.login.model.User;
import com.login.model.UserInfo;
import com.login.repository.UserInfoRepository;
import com.login.repository.RoleRepository;
import com.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    private UserInfoRepository userInfoRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       UserInfoRepository userInfoRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.userInfoRepository = userInfoRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void saveUserInfo(UserInfo userInfo) {
        userInfoRepository.save(userInfo);
    }
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);
        Role userRole = roleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return userRepository.save(user);
    }
    public List<User> listAll(){
        return (List<User>) userRepository.findAll();
    }
    public List<UserInfo> listAllInfo(){
        return (List<UserInfo>) userInfoRepository.findAll();
    }
}