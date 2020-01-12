package com.fh.mapper;

import com.fh.model.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMapper {

    List<Product> queryProductList();

    Product getProductById(Integer productId);

    Long updateProductStock(@Param("id") Integer productId, @Param("count") Long count);
}
