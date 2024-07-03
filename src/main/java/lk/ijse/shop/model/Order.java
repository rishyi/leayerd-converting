package lk.ijse.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class Order {
        private String id;
        private String details;
        private Date date;
        private String c_id;


}
