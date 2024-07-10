package lk.ijse.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class SupplierDTO {
    private String id;
    private String name;
    private String telephone;
    private String description;

}
