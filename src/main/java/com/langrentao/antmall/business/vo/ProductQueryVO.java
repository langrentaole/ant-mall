package com.langrentao.antmall.business.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProductQueryVO {
    @ApiModelProperty("商品id")
    private Long id;

    @ApiModelProperty("商品名称")
    private String name;

    @ApiModelProperty("商品描述")
    private String description;

    @ApiModelProperty("商品价格")
    private BigDecimal unitPrice;

    @ApiModelProperty("商品库存")
    private Integer stockQuantity;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;
}
