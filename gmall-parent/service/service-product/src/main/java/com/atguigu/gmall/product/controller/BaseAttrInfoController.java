package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.BaseAttrInfo;
import com.atguigu.gmall.product.service.BaseAttrInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 平台属性的控制层
 */
@RestController//返回一个对象(json字符串)
@RequestMapping(value = "/api/baseAttrInfo")
public class BaseAttrInfoController {

    @Autowired
    private BaseAttrInfoService baseAttrInfoService;


    /**
     * 主键查询
     * 传递参数:
     *  1.什么注解都不写--->相当于@RequestParam
     *      /api/baseAttrInfo/getBaseAttrInfo?id=
     *  2.路径传递值:@PathVariable
     *      /api/baseAttrInfo/getBaseAttrInfo/1
     * @param id
     * @return
     */
    @GetMapping(value = "/getBaseAttrInfo/{id}")
    public Result getBaseAttrInfo(@PathVariable(value = "id") Long id){
        return Result.ok(baseAttrInfoService.getBaseAttrInfo(id));
    }

    /**
     * 查询全部的数据
     * @return
     */
    @GetMapping(value = "/getAll")
    public Result getAll(){
        return Result.ok(baseAttrInfoService.getAll());
    }

    /**
     * 新增
     * @param baseAttrInfo
     * @return
     */
    @PostMapping//参数一般是放在body中,RequestBody从请求的body(请求体)获取数据
    public Result add(@RequestBody BaseAttrInfo baseAttrInfo){
        baseAttrInfoService.add(baseAttrInfo);
        return Result.ok();
    }

    /**
     * 修改
     * @param baseAttrInfo
     * @return
     */
    @PutMapping//参数一般是放在body中,RequestBody从请求的body(请求体)获取数据
    public Result update(@RequestBody BaseAttrInfo baseAttrInfo){
        baseAttrInfoService.update(baseAttrInfo);
        return Result.ok();
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable(value = "id") Long id){
        baseAttrInfoService.delete(id);
        return Result.ok();
    }

    /**
     * 条件搜索: 单元测试
     * @param baseAttrInfo
     * @return
     */
    @PostMapping(value = "/search")
    public Result search(@RequestBody BaseAttrInfo baseAttrInfo){
        return Result.ok(baseAttrInfoService.search(baseAttrInfo));
    }

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    @GetMapping(value = "/{page}/{size}")
    public Result page(@PathVariable(value = "page") Integer page,
                       @PathVariable(value = "size") Integer size){
        return Result.ok(baseAttrInfoService.page(page, size));
    }

    /**
     * 分页条件查询
     * @param baseAttrInfo
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}")
    public Result search(@RequestBody BaseAttrInfo baseAttrInfo,
                         @PathVariable(value = "page") Integer page,
                         @PathVariable(value = "size") Integer size){
        return Result.ok(baseAttrInfoService.search(baseAttrInfo, page, size));
    }
}
