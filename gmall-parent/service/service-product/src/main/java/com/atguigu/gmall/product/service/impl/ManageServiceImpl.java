package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.model.product.*;
import com.atguigu.gmall.product.mapper.*;
import com.atguigu.gmall.product.service.ManageService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 后台管理页面使用的服务层的实现类
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ManageServiceImpl implements ManageService {

    @Resource
    private BaseCategory1Mapper baseCategory1Mapper;

    /**
     * 查询所有的一级分类
     *
     * @return
     */
    @Override
    public List<BaseCategory1> getBaseCategory1() {
        return baseCategory1Mapper.selectList(null);
    }

    @Resource
    private BaseCategory2Mapper baseCategory2Mapper;
    /**
     * 根据一级分类查询二级分类
     *
     * @param c1Id
     * @return
     */
    @Override
    public List<BaseCategory2> getBaseCategory2(Long c1Id) {
        return baseCategory2Mapper.selectList(
                new LambdaQueryWrapper<BaseCategory2>()
                        .eq(BaseCategory2::getCategory1Id, c1Id));
    }

    @Resource
    private BaseCategory3Mapper baseCategory3Mapper;
    /**
     * 根据二级分类查询三级分类
     *
     * @param c2Id
     * @return
     */
    @Override
    public List<BaseCategory3> getBaseCategory3(Long c2Id) {
        return baseCategory3Mapper.selectList(
                new LambdaQueryWrapper<BaseCategory3>()
                        .eq(BaseCategory3::getCategory2Id, c2Id));
    }

    @Resource
    private BaseAttrInfoMapper baseAttrInfoMapper;

    @Resource
    private BaseAttrValueMapper baseAttrValueMapper;
    /**
     * 保存平台属性
     *
     * @param baseAttrInfo
     */
    @Override
    public void saveBaseAttrInfo(BaseAttrInfo baseAttrInfo) {
        //参数校验
        if(baseAttrInfo == null ||
                StringUtils.isEmpty(baseAttrInfo.getAttrName())){
            throw new RuntimeException("参数错误");
        }
        //判断是修改还是新增
        if(baseAttrInfo.getId() == null){
            //新增平台属性名称表
            int insert = baseAttrInfoMapper.insert(baseAttrInfo);
            if(insert <= 0){
                throw new RuntimeException("新增平台属性名称失败!");
            }
        }else{
            //修改平台属性名称表
            int update = baseAttrInfoMapper.updateById(baseAttrInfo);
            if(update < 0){
                throw new RuntimeException("修改平台属性名称失败!");
            }
            //旧的平台属性值全删除
            int delete =
                    baseAttrValueMapper.delete(
                            new LambdaQueryWrapper<BaseAttrValue>()
                                    .eq(BaseAttrValue::getAttrId, baseAttrInfo.getId()));
            if(delete < 0){
                throw new RuntimeException("修改平台属性名称失败!");
            }
        }
        //新增成功,就能获取平台属性的id
        Long attrId = baseAttrInfo.getId();
        //将id补充道平台属性每个值的对象中去
        List<BaseAttrValue> attrValueList
                = baseAttrInfo.getAttrValueList();
        attrValueList.stream().forEach(baseAttrValue -> {
            //补充平台属性的id
            baseAttrValue.setAttrId(attrId);
            //保存到数据库
            int insert1 = baseAttrValueMapper.insert(baseAttrValue);
            if(insert1 <= 0){
                throw new RuntimeException("新增平台属性值失败!");
            }
        });
    }

    /**
     * 查询平台属性列表
     *
     * @param category1Id
     * @param category2Id
     * @param category3Id
     * @return
     */
    @Override
    public List<BaseAttrInfo> getBaseAttrInfo(Long category1Id,
                                              Long category2Id,
                                              Long category3Id) {
        return baseAttrInfoMapper
                .selectAttrInfoByCategoryId(category1Id,
                        category2Id,
                        category3Id);
    }

    /**
     * 查询平台属性值列表
     *
     * @param attrId
     * @return
     */
    @Override
    public List<BaseAttrValue> getBaseAttrValue(Long attrId) {
        return baseAttrValueMapper.selectList(
                new LambdaQueryWrapper<BaseAttrValue>()
                        .eq(BaseAttrValue::getAttrId, attrId));
    }

    @Resource
    private BaseTradeMarkMapper baseTradeMarkMapper;
    /**
     * 查询品牌列表
     *
     * @return
     */
    @Override
    public List<BaseTrademark> getBaseTrademark() {
        return baseTradeMarkMapper.selectList(null);
    }

    @Resource
    private BaseSaleAttrMapper baseSaleAttrMapper;
    /**
     * 查询所有的销售属性
     *
     * @return
     */
    @Override
    public List<BaseSaleAttr> getBaseSaleAttr() {
        return baseSaleAttrMapper.selectList(null);
    }

    @Resource
    private SpuInfoMapper spuInfoMapper;
    /**
     * 保存spu的信息
     *
     * @param spuInfo
     */
    @Override
    public void saveSpuInfo(SpuInfo spuInfo) {
        //参数校验
        if(spuInfo == null){
            throw new RuntimeException("参数错误,操作失败");
        }
        //保存spuinfo的信息,判断是新增还是修改
        if(spuInfo.getId() == null){
            //新增
            int insert = spuInfoMapper.insert(spuInfo);
            if(insert <= 0){
                throw new RuntimeException("参数错误,新增操作失败");
            }
        }else{
            //修改
            int update = spuInfoMapper.updateById(spuInfo);
            if(update < 0){
                throw new RuntimeException("参数错误,修改操作失败");
            }
            //附属表需要处理,删除图片表
            int delete = spuImageMapper.delete(
                    new LambdaQueryWrapper<SpuImage>()
                            .eq(SpuImage::getSpuId, spuInfo.getId()));
            //删除销售属性名称表
            int delete1 = spuSaleAttrMapper.delete(
                    new LambdaQueryWrapper<SpuSaleAttr>()
                            .eq(SpuSaleAttr::getSpuId, spuInfo.getId()));
            //删除销售属性值表
            int delete2 = spuSaleAttrValueMapper.delete(
                    new LambdaQueryWrapper<SpuSaleAttrValue>()
                            .eq(SpuSaleAttrValue::getSpuId, spuInfo.getId()));
            if(delete < 0 || delete1 < 0 || delete2 < 0){
                throw new RuntimeException("参数错误,修改操作失败");
            }
        }
        //操作完成以后获取spu的id
        Long spuId = spuInfo.getId();
        //保存spu的图片
        saveSpuImage(spuId, spuInfo.getSpuImageList());
        //保存spu的销售属性和值的信息
        saveSpuSaleAttr(spuId, spuInfo.getSpuSaleAttrList());
    }

    /**
     * 分页查询spu的
     *  @param page
     * @param size
     * @param category3Id
     * @return
     */
    @Override
    public IPage<SpuInfo> getSpuInfoList(Integer page, Integer size, Long category3Id) {
        return spuInfoMapper.selectPage(
                new Page<>(page, size),
                new LambdaQueryWrapper<SpuInfo>()
                        .eq(SpuInfo::getCategory3Id, category3Id));
    }

    @Resource
    private SpuSaleAttrMapper spuSaleAttrMapper;
    /**
     * 保存spu的销售属性信息
     * @param spuId
     * @param spuSaleAttrList
     */
    private void saveSpuSaleAttr(Long spuId, List<SpuSaleAttr> spuSaleAttrList) {
        spuSaleAttrList.stream().forEach(spuSaleAttr -> {
            //补全spuId
            spuSaleAttr.setSpuId(spuId);
            //保存销售属性名称
            int insert = spuSaleAttrMapper.insert(spuSaleAttr);
            if(insert <= 0){
                throw new RuntimeException("保存spu的销售属性名称信息失败");
            }
            //保存这个销售属性的值
            saveSpuSaleAttrValue(spuId, spuSaleAttr.getSpuSaleAttrValueList(), spuSaleAttr.getSaleAttrName());
        });
    }

    @Resource
    private SpuSaleAttrValueMapper spuSaleAttrValueMapper;
    /**
     * 保存销售属性的值
     * @param spuId
     * @param spuSaleAttrValueList
     * @param saleAttrName
     */
    private void saveSpuSaleAttrValue(Long spuId, List<SpuSaleAttrValue> spuSaleAttrValueList, String saleAttrName) {
        spuSaleAttrValueList.stream().forEach(spuSaleAttrValue -> {
            //补全spu的id
            spuSaleAttrValue.setSpuId(spuId);
            //补全销售属性的名字
            spuSaleAttrValue.setSaleAttrName(saleAttrName);
            //保存
            int insert = spuSaleAttrValueMapper.insert(spuSaleAttrValue);
            if(insert <= 0){
                throw new RuntimeException("保存spu的销售属性值信息失败");
            }
        });
    }

    @Resource
    private SpuImageMapper spuImageMapper;
    /**
     * 保存spu的图片
     * @param spuId
     * @param spuImageList
     */
    private void saveSpuImage(Long spuId, List<SpuImage> spuImageList) {
        spuImageList.stream().forEach(spuImage -> {
            //补全spu的id
            spuImage.setSpuId(spuId);
            //保存
            int insert = spuImageMapper.insert(spuImage);
            if(insert <= 0){
                throw new RuntimeException("保存spu的图片信息失败");
            }
        });
    }
}
