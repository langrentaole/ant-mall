package com.langrentao.antmall.business.bo;

import com.langrentao.antmall.common.entity.PageBO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductQueryBO extends PageBO {
    @ApiModelProperty("商品名称")
    private String name;

    @ApiModelProperty("商品描述")
    private String description;
}
