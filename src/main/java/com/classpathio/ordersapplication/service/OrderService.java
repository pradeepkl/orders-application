package com.classpathio.ordersapplication.service;

import com.classpathio.ordersapplication.model.Order;
import com.classpathio.ordersapplication.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Order saveOrder(Order order){
        return this.orderRepository.save(order);
    }

    public Set<Order> fetchOrders(){
        return Set.copyOf(this.orderRepository.findAll());
    }

    public void deleteOrder(long orderId){
        this.orderRepository.findById(orderId).ifPresent(this.orderRepository::delete);
    }

    public Order fetchOrderById(Long id){
        return this.orderRepository.findById(id).orElse(null);
    }

    @Transactional
    public Order updateOrder(long id, Order order){
        Order existingOrder = this.orderRepository.findById(id).orElse(null);
        if(existingOrder == null){
            return null;
        }
        return this.orderRepository.save(order);
    }

}
