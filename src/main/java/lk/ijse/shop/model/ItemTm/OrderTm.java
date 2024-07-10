package lk.ijse.shop.model.ItemTm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class OrderTm {
    private String id;
    private String details;
    private Date date;
    private String c_id;

}
