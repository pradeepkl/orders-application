package com.classpathio.ordersapplication.controller;

import com.classpathio.ordersapplication.model.Order;
import com.classpathio.ordersapplication.repository.OrderRepository;
import com.classpathio.ordersapplication.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderRestController {

    private final OrderService orderService;

    @GetMapping
    public Set<Order> fetchOrders(){
        return Set.copyOf(this.orderService.fetchOrders());
    }


    @PostMapping
    public Order saveOrder(@RequestBody Order order){
        return this.orderService.saveOrder(order);
    }

    @GetMapping("/{id}")
    public Order fetchOrderById(@PathVariable Long id){
        return this.orderService.fetchOrderById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id){
        this.orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable Long id, Order order){
       return this.orderService.updateOrder(id, order);
    }
}
