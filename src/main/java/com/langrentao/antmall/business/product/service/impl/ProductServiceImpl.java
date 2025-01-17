package com.langrentao.antmall.business.product.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.langrentao.antmall.business.bo.ProductAddBO;
import com.langrentao.antmall.business.bo.ProductEditBO;
import com.langrentao.antmall.business.bo.ProductQueryBO;
import com.langrentao.antmall.business.product.entity.Product;
import com.langrentao.antmall.business.product.mapper.ProductMapper;
import com.langrentao.antmall.business.product.service.ProductService;
import com.langrentao.antmall.business.vo.ProductQueryVO;
import com.langrentao.antmall.common.entity.PageVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Override
    public void add(ProductAddBO productAddBO) {
        Product product = new Product();
        BeanUtils.copyProperties(productAddBO, product);
        save(product);
    }

    @Override
    public void edit(ProductEditBO productEditBO) {
        Product product = new Product();
        BeanUtils.copyProperties(productEditBO, product);
        updateById(product);
    }

    @Override
    public PageVO<ProductQueryVO> page(ProductQueryBO productQueryBO) {
        Page<Product> pageRequest = new Page<>(productQueryBO.getCurrentPage(), productQueryBO.getPageSize());

        Page<Product> page = lambdaQuery()
                .eq(StringUtils.hasText(productQueryBO.getName()), Product::getName, productQueryBO.getName())
                .like(StringUtils.hasText(productQueryBO.getDescription()), Product::getDescription, productQueryBO.getDescription())
                .orderByAsc(Product::getCreateTime)
                .page(pageRequest);

        PageVO<ProductQueryVO> pageVO = new PageVO<>();
        pageVO.setCurrentPage(page.getCurrent());
        pageVO.setPageSize(page.getSize());
        pageVO.setTotalSize(page.getTotal());

        List<Product> productList = page.getRecords();
        List<ProductQueryVO> productQueryVOList = new ArrayList<>();

        for (Product product : productList) {
            ProductQueryVO productQueryVO = new ProductQueryVO();
            BeanUtils.copyProperties(product, productQueryVO);
            productQueryVOList.add(productQueryVO);
        }

        pageVO.setDataList(productQueryVOList);

        return pageVO;
    }

    @Override
    public void delete(List<Long> idList) {
        removeBatchByIds(idList);
    }
}
