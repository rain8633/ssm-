package fruit.service.impl;

import fruit.base.BaseDao;
import fruit.base.BaseServiceImpl;
import fruit.mapper.ItemMapper;
import fruit.mapper.ItemOrderMapper;
import fruit.po.Item;
import fruit.po.ItemOrder;
import fruit.service.ItemOrderService;
import fruit.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ItemOrderServiceImpl extends BaseServiceImpl<ItemOrder> implements ItemOrderService {
    @Autowired
    ItemOrderMapper itemOrderMapper;

    @Override
    public BaseDao<ItemOrder> getBaseDao() {
        return itemOrderMapper;
    }
}
