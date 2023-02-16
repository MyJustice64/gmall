package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.BaseAttrInfo;
import com.atguigu.gmall.product.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 后台管理使用的api的控制层
 */
@RestController
@RequestMapping(value = "/admin/product")
//@CrossOrigin//开启跨城访问,此方法每个服务都要加，不方便舍弃
public class ManageController {


    @Autowired
    private ManageService manageService;


    /**
     * 查询所有的一级分类
     * @return
     */
    @GetMapping(value = "/getCategory1")
    public Result getCategory1(){
        return Result.ok(manageService.getBaseCategory1());
    }

    /**
     * 根据一级分类查询二级分类
     * @param c1Id
     * @return
     */
    @GetMapping(value = "/getCategory2/{c1Id}")
    public Result getCategory2(@PathVariable(value = "c1Id") Long c1Id){
        return Result.ok(manageService.getBaseCategory2(c1Id));
    }


    /**
     * 根据二级分类查询三级分类的信息
     * @param c2Id
     * @return
     */
    @GetMapping(value = "/getCategory3/{c2Id}")
    public Result getCategory3(@PathVariable(value = "c2Id") Long c2Id){
        return Result.ok(manageService.getBaseCategory3(c2Id));
    }

    /**
     * 保存平台属性
     * @param baseAttrInfo
     * @return
     */
    @PostMapping(value = "/saveAttrInfo")
    public Result saveAttrInfo(@RequestBody BaseAttrInfo baseAttrInfo){
        manageService.saveBaseAttrInfo(baseAttrInfo);
        return Result.ok();
    }

    /**
     * 查询平台属性列表
     * @param category1Id
     * @param category2Id
     * @param category3Id
     * @return
     */
    @GetMapping(value = "/attrInfoList/{category1Id}/{category2Id}/{category3Id}")
    public Result attrInfoList(@PathVariable(value = "category1Id") Long category1Id,
                               @PathVariable(value = "category2Id") Long category2Id,
                               @PathVariable(value = "category3Id") Long category3Id){
        return Result.ok(manageService.getBaseAttrInfo(category1Id, category2Id, category3Id));
    }

    /**
     * 查询平台属性值的列表
     * @param attrId
     * @return
     */
    @GetMapping(value = "/getAttrValueList/{attrId}")
    public Result getAttrValueList(@PathVariable(value = "attrId") Long attrId){
        return Result.ok(manageService.getBaseAttrValue(attrId));
    }

}
