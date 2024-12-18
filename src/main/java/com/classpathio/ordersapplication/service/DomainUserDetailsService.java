package com.classpathio.ordersapplication.service;

import com.classpathio.ordersapplication.model.DomainUserDetails;
import com.classpathio.ordersapplication.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
/**
 * this is the class that will bridge the UserJpaRepository and the UserDetails
 * UserDetailsService - Domain object of Spring Security
 * User - Domain object of the application
 */

public class DomainUserDetailsService implements UserDetailsService {

    private final UserJpaRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("User email :: " + email);
        return this.userRepository.findByEmail(email)
                .map(DomainUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
