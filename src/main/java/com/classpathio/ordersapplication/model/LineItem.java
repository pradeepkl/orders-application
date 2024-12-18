package com.classpathio.ordersapplication.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@Getter
@EqualsAndHashCode(exclude = "order")
@ToString(exclude = "order")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="line_items")
public class LineItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Setter
    private String itemName;
    @Setter
    private double price;
    @Setter
    private int quantity;

    @Setter
    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonBackReference
    private Order order;
}
