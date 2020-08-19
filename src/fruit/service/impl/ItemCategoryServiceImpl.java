package fruit.service.impl;

import fruit.base.BaseDao;
import fruit.base.BaseServiceImpl;
import fruit.mapper.ItemCategoryMapper;
import fruit.mapper.ManageMapper;
import fruit.po.ItemCategory;
import fruit.po.Manage;
import fruit.service.ItemCategoryService;
import fruit.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ItemCategoryServiceImpl extends BaseServiceImpl<ItemCategory> implements ItemCategoryService {
    @Autowired
    ItemCategoryMapper itemCategoryMapper;

    @Override
    public BaseDao<ItemCategory> getBaseDao() {
        return itemCategoryMapper;
    }
}
