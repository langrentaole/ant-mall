package com.langrentao.antmall.business.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.langrentao.antmall.business.bo.ProductAddBO;
import com.langrentao.antmall.business.bo.ProductEditBO;
import com.langrentao.antmall.business.bo.ProductQueryBO;
import com.langrentao.antmall.business.product.entity.Product;
import com.langrentao.antmall.business.vo.ProductQueryVO;
import com.langrentao.antmall.common.entity.PageVO;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface ProductService extends IService<Product> {
    void add(ProductAddBO productAddBO);

    void edit(ProductEditBO productEditBO);

    void delete(List<Long> idList);

    PageVO<ProductQueryVO> page(ProductQueryBO productQueryBO);
}
