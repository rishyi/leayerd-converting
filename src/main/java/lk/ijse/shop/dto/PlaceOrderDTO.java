package lk.ijse.shop.dto;

import lk.ijse.shop.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class PlaceOrderDTO {
    private Order order;
    private List<OrderDetail> odList;

}
