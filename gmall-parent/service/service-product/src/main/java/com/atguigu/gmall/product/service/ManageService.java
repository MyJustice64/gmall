package com.atguigu.gmall.product.service;

import com.atguigu.gmall.model.product.*;

import java.util.List;

/**
 * 后台管理页面使用的服务层
 */
public interface ManageService {

    /**
     * 查询所有的一级分类
     * @return
     */
    public List<BaseCategory1> getBaseCategory1();

    /**
     * 根据一级分类查询二级分类
     * @param c1Id
     * @return
     */
    public List<BaseCategory2> getBaseCategory2(Long c1Id);

    /**
     * 根据二级分类查询三级分类
     * @param c2Id
     * @return
     */
    public List<BaseCategory3> getBaseCategory3(Long c2Id);

    /**
     * 保存平台属性
     * @param baseAttrInfo
     */
    public void saveBaseAttrInfo(BaseAttrInfo baseAttrInfo);

    /**
     * 查询平台属性列表
     * @param category1Id
     * @param category2Id
     * @param category3Id
     * @return
     */
    public List<BaseAttrInfo> getBaseAttrInfo(Long category1Id,
                                              Long category2Id,
                                              Long category3Id);

    /**
     * 查询平台属性值列表
     * @param attrId
     * @return
     */
    public List<BaseAttrValue> getBaseAttrValue(Long attrId);
}
