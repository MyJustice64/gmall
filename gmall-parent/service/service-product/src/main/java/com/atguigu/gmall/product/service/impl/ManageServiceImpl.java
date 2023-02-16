package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.model.product.*;
import com.atguigu.gmall.product.mapper.*;
import com.atguigu.gmall.product.service.ManageService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
}
