package com.changgou.goods.service;

import com.changgou.goods.pojo.Brand;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ Author     ：CodeLiu
 * @ Date       ：Created in 2020/09/08 20:50
 * @ Description：
 * @ Modified By：
 */
public interface BrandService {

    /**
     * 查询所有商品
     * @return
     */
    List<Brand> findAll();

    /**
     * 根据id查询商品
     * @param id
     * @return
     */
    Brand findById(Integer id);

    /**
     * 添加商品
     * @param brand
     */
    void addBrand(Brand brand);
    /**
     * 根据id修改商品
     */
    void updateBrand(Brand brand);

    /**
     * 根据id删除商品
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 根据条件筛选商品
     * @param brand
     * @return
     */
    List<Brand> findList(Brand brand);

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Brand> findPage(Integer page,Integer size);
    /**
     * 条件分页查询
     */
    PageInfo<Brand> findPage(Integer page,Integer size,Brand brand);
}
