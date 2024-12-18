package com.classpathio.ordersapplication.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode(exclude = {"lineItems", "totalAmount"})
@ToString(exclude = "lineItems")

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Setter
    private String customerName;
    @Setter
    private double totalAmount;
    @Setter
    private LocalDate orderDate;

    @Setter
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<LineItem> lineItems;

    //scallfold-generate
    public void addLineItem(LineItem lineItem) {
        if(this.lineItems == null) {
            this.lineItems = new java.util.HashSet<>();
        }
        this.lineItems.add(lineItem);
        lineItem.setOrder(this);
    }

    public void removeLineItem(LineItem lineItem) {
        this.lineItems.remove(lineItem);
        lineItem.setOrder(null);
    }
}
