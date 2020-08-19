package fruit.service.impl;

import fruit.base.BaseDao;
import fruit.base.BaseServiceImpl;

import fruit.mapper.ItemMapper;
import fruit.po.Item;

import fruit.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ItemServiceImpl extends BaseServiceImpl<Item> implements ItemService {
    @Autowired
    ItemMapper itemMapper;

    @Override
    public BaseDao<Item> getBaseDao() {
        return itemMapper;
    }
}
