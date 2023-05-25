package com.atguigu.gmall.product.service;

import com.atguigu.gmall.model.product.*;
import com.baomidou.mybatisplus.core.metadata.IPage;

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

    /**
     * 查询品牌列表
     * @return
     */
    public List<BaseTrademark> getBaseTrademark();

    /**
     * 查询所有的销售属性
     * @return
     */
    public List<BaseSaleAttr> getBaseSaleAttr();

    /**
     * 保存spu的信息
     * @param spuInfo
     */
    public void saveSpuInfo(SpuInfo spuInfo);

    /**
     * 分页查询spu的
     * @param page
     * @param size
     * @param category3Id
     * @return
     */
    public IPage<SpuInfo> getSpuInfoList(Integer page, Integer size, Long category3Id);
}
