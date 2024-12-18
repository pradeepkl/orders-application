package com.classpathio.ordersapplication.util;

import com.classpathio.ordersapplication.model.LineItem;
import com.classpathio.ordersapplication.model.Order;
import com.classpathio.ordersapplication.model.Role;
import com.classpathio.ordersapplication.model.User;
import com.classpathio.ordersapplication.repository.OrderRepository;
import com.classpathio.ordersapplication.repository.UserJpaRepository;
import com.github.javafaker.Faker;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.ZoneId;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Configuration
@RequiredArgsConstructor
public class BootstrapAppData {

    private final UserJpaRepository userRepository;
    private final OrderRepository orderRepository;
    private final PasswordEncoder passwordEncoder;

    private final Faker faker = new Faker();
    private static final String rawPassword = "password";

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void onApplicationReady(ApplicationReadyEvent applicationReadyEvent){
        //insert users and roles upfront when the application starts
        Set<Role> roles = createRoles();
        createUsers(roles);
    }

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void bootstrapOrders(ApplicationReadyEvent applicationReadyEvent){
        //insert orders upfront when the application starts
        createOrders();
    }

    private void createOrders() {
        IntStream.range(0,10).forEach(i -> {
            Order order = Order.builder()
                                .orderDate(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                                .customerName(faker.name().fullName())
                                .build();
            IntStream.range(0, faker.number().numberBetween(2,3)).forEach(index -> {
                order.addLineItem(LineItem.builder()
                        .itemName(faker.commerce().productName())
                        .quantity(faker.number().numberBetween(1,5))
                        .price(faker.number().randomDouble(2, 10, 100))
                        .build());
                double totalOrderPrice = order.getLineItems().stream()
                        .mapToDouble(lineItem -> lineItem.getPrice() * lineItem.getQuantity())
                        .sum();
                order.setTotalAmount(totalOrderPrice);
                this.orderRepository.save(order);
            });
        });
    }

    private void createUsers(Set<Role> roles) {
        IntStream.range(0,10).forEach(i -> {
            User user =  User.builder()
                    .username(faker.name().username())
                    .email(faker.internet().emailAddress())
                    .password(this.passwordEncoder.encode(rawPassword)) //encode the password using BCryptPasswordEncoder before saving
                    .dob(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                    .roles(roles)
                    .build();
            userRepository.save(user);
        });
    }

    private Set<Role> createRoles() {
        Set<String> roles = Set.of("ROLE_USER");
        return roles.stream()
                        .map(role -> Role.builder().role(role).build())
                        .collect(Collectors.toSet());
    }


}
