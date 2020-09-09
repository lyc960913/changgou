package com.changgou.goods.controller;

import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.goods.pojo.Brand;
import com.changgou.goods.service.BrandService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ Author     ：CodeLiu
 * @ Date       ：Created in 2020/09/08 20:52
 * @ Description：
 * @ Modified By：
 */
@RestController
@RequestMapping("/brand")
@CrossOrigin//处理跨域问题
public class BrandController {
    @Autowired
    private BrandService brandService;
    @GetMapping
    public Result<List<Brand>> findAll(){
        List<Brand> brandList = brandService.findAll();
        return new Result<List<Brand>>(true, StatusCode.OK,"查询成功",brandList);
    }
    @GetMapping("/{id}")
    public Result<Brand> findById(@PathVariable Integer id){
        Brand brand = brandService.findById(id);
        return new Result<Brand>(true, StatusCode.OK,"根据id查询商品成功",brand);
    }
    @PostMapping()
    public Result addBrand(@RequestBody Brand brand){
        brandService.addBrand(brand);
        return new Result(true, StatusCode.OK,"添加商品成功");

    }
    @PutMapping("/{id}")
    public Result update(@PathVariable(name = "id") Integer id,@RequestBody Brand brand){
        brand.setId(id);
        brandService.updateBrand(brand);
        return new Result(true, StatusCode.OK,"修改商品成功");
    }
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable(name = "id")Integer id){
        brandService.deleteById(id);
        return new Result(true, StatusCode.OK,"删除商品成功");
    }
    @PostMapping("/search")
    public Result<List<Brand>> findList(@RequestBody Brand brand){
        List<Brand> brandList = brandService.findList(brand);
        return new Result<List<Brand>>(true, StatusCode.OK,"根据条件查询成功",brandList);
    }
    @GetMapping("/search/{page}/{size}")
    public Result<PageInfo<Brand>> findPage(
            @PathVariable(value = "page") Integer page,
            @PathVariable(value = "size") Integer size
    ){
        PageInfo<Brand> pageInfo = brandService.findPage(page, size);
        return new Result<PageInfo<Brand>>(true,StatusCode.OK,"分页查询商品",pageInfo);
    }
    @PostMapping("/search/{page}/{size}")
    public Result<PageInfo<Brand>> findPage(
            @PathVariable(value = "page") Integer page,
            @PathVariable(value = "size") Integer size,
            @RequestBody Brand brand
    ){
        PageInfo<Brand> pageInfo = brandService.findPage(page, size,brand);
        return new Result<PageInfo<Brand>>(true,StatusCode.OK,"分页查询商品",pageInfo);
    }
}
