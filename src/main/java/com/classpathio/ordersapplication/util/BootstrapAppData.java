package com.classpathio.ordersapplication.util;

import com.classpathio.ordersapplication.model.Role;
import com.classpathio.ordersapplication.repository.UserJpaRepository;
import com.github.javafaker.Faker;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import java.time.ZoneId;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Configuration
@RequiredArgsConstructor
public class BootstrapAppData {

    private final UserJpaRepository userRepository;
    private final Faker faker = new Faker();

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void onApplicationReady(ApplicationReadyEvent applicationReadyEvent){
        //insert users and roles upfront when the application starts
        Set<Role> roles = createRoles();
        createUsers(roles);
    }

    private void createUsers(Set<Role> roles) {
        IntStream.range(0,10).forEach(i -> {
            userRepository.save(com.classpathio.ordersapplication.model.User.builder()
                    .username(faker.name().username())
                    .password(faker.internet().password())
                    .dob(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                    .roles(roles)
                    .build());
        });
    }

    private Set<Role> createRoles() {
        Set<String> roles = Set.of("ROLE_USER", "ROLE_ADMIN", "ROLE_MODERATOR");
        return roles.stream()
                        .map(role -> Role.builder().role(role).build())
                        .collect(Collectors.toSet());
    }


}
