package fruit.service.impl;

import fruit.base.BaseDao;
import fruit.base.BaseServiceImpl;
import fruit.mapper.OrderDetailMapper;
import fruit.po.OrderDetail;
import fruit.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrderDetailServiceImpl extends BaseServiceImpl<OrderDetail> implements OrderDetailService {
    @Autowired
    OrderDetailMapper itemMapper;

    @Override
    public BaseDao<OrderDetail> getBaseDao() {
        return itemMapper;
    }
}
