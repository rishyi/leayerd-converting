package lk.ijse.shop.model.ItemTm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class OrderTm {
    private String id;
    private String details;
    private Date date;
    private String c_id;

}
