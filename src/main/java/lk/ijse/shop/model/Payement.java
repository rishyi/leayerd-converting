package lk.ijse.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Payement {

    private String id;
    private double price;
    private Date date;
    private String o_id;

}
