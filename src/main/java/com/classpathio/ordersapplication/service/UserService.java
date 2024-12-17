package com.classpathio.ordersapplication.service;

import com.classpathio.ordersapplication.model.User;
import com.classpathio.ordersapplication.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserJpaRepository userRepository;

    public User saveUser(User user){
        return this.userRepository.save(user);
    }

    public Set<User> fetchUsers(){
        return Set.copyOf(this.userRepository.findAll());
    }

    public void deleteUser(User user){
        this.userRepository.findById(user.getId()).ifPresent(this::deleteUser);
    }

}
