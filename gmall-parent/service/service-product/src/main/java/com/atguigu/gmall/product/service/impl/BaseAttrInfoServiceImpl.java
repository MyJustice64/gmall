package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.model.product.BaseAttrInfo;
import com.atguigu.gmall.product.mapper.BaseAttrInfoMapper;
import com.atguigu.gmall.product.service.BaseAttrInfoService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 台属性相关的服务层的实现类
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BaseAttrInfoServiceImpl implements BaseAttrInfoService {

    @Resource
    private BaseAttrInfoMapper baseAttrInfoMapper;

    /**
     * 主键查询
     *
     * @param id
     * @return
     */
    @Override
    public BaseAttrInfo getBaseAttrInfo(Long id) {
        return baseAttrInfoMapper.selectById(id);
    }

    /**
     * 查询全部
     *
     * @return
     */
    @Override
    public List<BaseAttrInfo> getAll() {
        return baseAttrInfoMapper.selectList(null);
    }

    /**
     * 新增: 事务
     *
     * @param baseAttrInfo
     */
    @Override
    public void add(BaseAttrInfo baseAttrInfo) {
        //参数校验
        if(baseAttrInfo == null){
            //throw new GmallException("参数不能为空", ResultCodeEnum.FAIL.getCode());
            throw new RuntimeException("参数不能为空");
        }
        //新增
        int insert = baseAttrInfoMapper.insert(baseAttrInfo);
        if(insert <= 0){
            throw new RuntimeException("新增失败,请重试!");
        }
    }

    /**
     * 修改
     *
     * @param baseAttrInfo
     */
    @Override
    public void update(BaseAttrInfo baseAttrInfo) {
        //参数校验
        if(baseAttrInfo == null || baseAttrInfo.getId() == null){
            //throw new GmallException("参数不能为空", ResultCodeEnum.FAIL.getCode());
            throw new RuntimeException("参数不能为空");
        }
        //修改: 返回的结果都是受影响的行数
        int update = baseAttrInfoMapper.updateById(baseAttrInfo);
        if(update < 0){
            throw new RuntimeException("修改失败,请重试!");
        }
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public void delete(Long id) {
        //参数校验
        if(id == null){
            throw new RuntimeException("参数不能为空");
        }
        //删除
        int i = baseAttrInfoMapper.deleteById(id);
        if(i < 0){
            throw new RuntimeException("删除失败,请重试!");
        }
    }

    /**
     * 条件查询
     *
     * @param baseAttrInfo
     * @return
     */
    @Override
    public List<BaseAttrInfo> search(BaseAttrInfo baseAttrInfo) {
        //参数校验
        if(baseAttrInfo == null){
            //查询全部
            return baseAttrInfoMapper.selectList(null);
        }
        //拼接查询条件
        LambdaQueryWrapper wrapper = buildQueryWrapper(baseAttrInfo);
        //查询返回结果
        return baseAttrInfoMapper.selectList(wrapper);
    }

    /**
     * 分页查询
     *  @param page
     * @param size
     * @return
     */
    @Override
    public IPage<BaseAttrInfo> page(Integer page, Integer size) {
        //参数校验
        if(page == null){
            //默认第一页
            page = 1;
        }
        if(size == null){
            //默认10条
            size = 10;
        }
        //分页查询
        return baseAttrInfoMapper.selectPage(new Page<>(page, size), null);
    }

    /**
     * 分页条件查询
     *
     * @param baseAttrInfo
     * @param page
     * @param size
     * @return
     */
    @Override
    public IPage<BaseAttrInfo> search(BaseAttrInfo baseAttrInfo,
                                      Integer page,
                                      Integer size) {
        //参数校验
        if(page == null){
            //默认第一页
            page = 1;
        }
        if(size == null){
            //默认10条
            size = 10;
        }
        //参数校验
        if(baseAttrInfo == null){
            //查询全部
            return baseAttrInfoMapper.selectPage(new Page<>(page, size), null);
        }
        //拼接查询条件
        LambdaQueryWrapper wrapper = buildQueryWrapper(baseAttrInfo);
        //执行查询
        return baseAttrInfoMapper.selectPage(new Page<>(page, size), wrapper);
    }

    /**
     * 拼接查询条件
     * @param baseAttrInfo
     * @return
     */
    private LambdaQueryWrapper buildQueryWrapper(BaseAttrInfo baseAttrInfo) {
        //拼接条件,声明条件构造器
        LambdaQueryWrapper<BaseAttrInfo> wrapper = new LambdaQueryWrapper<>();
        //id不为空,作为条件:等于
        if(baseAttrInfo.getId() != null){
            wrapper.eq(BaseAttrInfo::getId, baseAttrInfo.getId());
        }
        //名字: like
        if(!StringUtils.isEmpty(baseAttrInfo.getAttrName())){
            wrapper.like(BaseAttrInfo::getAttrName, baseAttrInfo.getAttrName());
        }
        //分类id: 等于
        if(baseAttrInfo.getCategoryId() != null){
            wrapper.eq(BaseAttrInfo::getCategoryId, baseAttrInfo.getCategoryId());
        }
        //分类级别: 等于
        if(baseAttrInfo.getCategoryLevel() != null){
            wrapper.eq(BaseAttrInfo::getCategoryLevel, baseAttrInfo.getCategoryLevel());
        }
        //返回
        return wrapper;
    }
}
