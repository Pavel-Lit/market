package ru.geekbrains.march.market.api;

import java.math.BigDecimal;
import java.util.List;

public class OrderDto {
    private Long id;
    private List<OrderItemDto> list;
    private BigDecimal totalPrice;

    public OrderDto() {
    }

    public OrderDto(Long id, List<OrderItemDto> list, BigDecimal totalPrice) {
        this.id = id;
        this.list = list;
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OrderItemDto> getList() {
        return list;
    }

    public void setList(List<OrderItemDto> list) {
        this.list = list;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
