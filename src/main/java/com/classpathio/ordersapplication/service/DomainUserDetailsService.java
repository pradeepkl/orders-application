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
public class DomainUserDetailsService implements UserDetailsService {

    private final UserJpaRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.userRepository.findByEmail(email)
                .map(DomainUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
