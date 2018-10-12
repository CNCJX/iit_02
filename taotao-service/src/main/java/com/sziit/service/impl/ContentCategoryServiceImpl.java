package com.sziit.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.sziit.mapper.ContentCategoryMapper;
import com.sziit.pojo.ContentCategory;
import com.sziit.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.sziit.service
 *  @文件名:   ContentCategoryServiceImpl
 *  @创建者:   Cjx
 *  @创建时间:  2018/9/28 13:27
 *  @描述：    TODO
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {


    @Autowired
    private ContentCategoryMapper mapper;

    @Override
    public List<ContentCategory> getCategoryByParentId(Long id) {

        ContentCategory category = new ContentCategory();

        category.setParentId(id);

        return mapper.select(category);

    }

    @Override
    public ContentCategory add(ContentCategory contentCategory) {

        //1.  直接添加这个分类到    ContentCategory： parentId ，  name
        contentCategory.setStatus(1);           //正常使用
        contentCategory.setIsParent(false);     //添加的都不是父亲分类
        contentCategory.setCreated(new Date());     //创建时间
        contentCategory.setUpdated(new Date());     //创建时间

        mapper.insertSelective(contentCategory);

        //上面的代码针对的场景是：在父级分类下创建子分类。 如果在子分类下创建子分类。 上面的代码仅仅只能添加子分类。
        //如果我们实在子分类AA下创建分类BB。 那么上面的代码仅仅只能添加 子分类BB，并不会把子分类AA变成父级分类。

        //2.判断当前这个分类的父亲是不是子分类，如果是子分类，  那么把这个分类变成父级分类。

        Long parentId = contentCategory.getParentId();
        ContentCategory parentCategory = mapper.selectByPrimaryKey(parentId);

        //判定他的父亲是不是子级分类，
        if (!parentCategory.getIsParent()) {

            //让它的父亲是父亲分类
            parentCategory.setIsParent(true);

        }

        mapper.updateByPrimaryKeySelective(parentCategory);

        System.out.println("contentCate=" + contentCategory);

        return contentCategory;

    }

    @Override
    public int update(ContentCategory contentCategory) {

        int rows = mapper.updateByPrimaryKeySelective(contentCategory);

        System.out.println("rows=" + rows);

        return rows;
    }

    @Override
    public int delete(ContentCategory contentCategory) {

        //int result = mapper.deleteByPrimaryKey(contentCategory);

        List<ContentCategory> list = new ArrayList<>();
        list.add(contentCategory);
        findAllChild(list, contentCategory.getId());

        int result = deleteAll(list);

        ContentCategory c = new ContentCategory();
        c.setParentId(contentCategory.getParentId());
        int count = mapper.selectCount(c);

        if(count < 1){

            c = new ContentCategory();
            c.setId(contentCategory.getParentId());
            c.setIsParent(false);
            mapper.updateByPrimaryKeySelective(c);
        }
        return result;
    }

    /**
     * 删除一个集合
     * @param list
     * @return
     */
    private int deleteAll(List<ContentCategory> list) {

        int result = 0;
        for (ContentCategory category : list) {
            mapper.delete(category);
        }


        return  result;
    }

    /**
     * 查询给定的分类id的所有子分类 包含多重的子级分类
     *
     * @param list 存储的集合
     * @param id   当前要查询的id
     */
    private void findAllChild(List<ContentCategory> list, Long id) {

        //找到当前节点的孩子
        List<ContentCategory> childList = getCategoryByParentId(id);

        if(childList !=null && childList.size() >0 ) {
            //便利这些子集分类
            for (ContentCategory category : childList) {

                list.add(category);

                findAllChild(list, category.getId());
            }
        }
    }
}
