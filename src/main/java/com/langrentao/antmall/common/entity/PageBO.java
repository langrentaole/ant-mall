package com.langrentao.antmall.common.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PageBO {
    @ApiModelProperty("当前页")
    private Long currentPage = 0L;

    @ApiModelProperty("每页个数")
    private Long pageSize = 10L;

}
