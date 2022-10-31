package com.infybuzz.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class PurchaseOrderDto {

    private Long orderId;
    private Integer productId;
    private Integer price;
    private Integer userId;

}
