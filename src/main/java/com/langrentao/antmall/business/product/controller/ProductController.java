package com.langrentao.antmall.business.product.controller;

import com.langrentao.antmall.business.bo.ProductAddBO;
import com.langrentao.antmall.business.bo.ProductEditBO;
import com.langrentao.antmall.business.bo.ProductQueryBO;
import com.langrentao.antmall.business.product.service.ProductService;
import com.langrentao.antmall.business.vo.ProductQueryVO;
import com.langrentao.antmall.common.entity.PageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Api(tags = "商品管理")
@RestController
@RequestMapping("/product")
@Validated
public class ProductController {

    @Autowired
    private ProductService productService;

    @ApiOperation("添加商品")
    @PostMapping("/add")
    public void add(@Valid @RequestBody ProductAddBO productAddBO) {
        productService.add(productAddBO);
    }

    @ApiOperation("修改商品")
    @PostMapping("/edit")
    public void edit(@Valid @RequestBody ProductEditBO productEditBO) {
        productService.edit(productEditBO);
    }

    @ApiOperation("查询商品")
    @GetMapping("/page")
    public PageVO<ProductQueryVO> page(ProductQueryBO productQueryBO) {
        return productService.page(productQueryBO);
    }

    @ApiOperation("删除商品")
    @PostMapping("/delete")
    public void delete(@NotEmpty @RequestBody List<Long> idList) {
        productService.delete(idList);
    }
}
