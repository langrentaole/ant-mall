package com.langrentao.antmall.business.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
public class ProductAddBO {

    @NotBlank(message = "商品名称不能为空")
    @ApiModelProperty("商品名称")
    private String name;

    @ApiModelProperty("商品描述")
    private String description;

    @ApiModelProperty("商品价格")
    private BigDecimal unitPrice;

    @ApiModelProperty("商品库存")
    private Integer stockQuantity;
}
