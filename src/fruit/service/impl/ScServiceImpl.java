package fruit.service.impl;

import fruit.base.BaseDao;
import fruit.base.BaseServiceImpl;
import fruit.mapper.ItemOrderMapper;
import fruit.mapper.ScMapper;
import fruit.po.ItemOrder;
import fruit.po.Sc;
import fruit.service.ItemOrderService;
import fruit.service.ScService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ScServiceImpl extends BaseServiceImpl<Sc> implements ScService {
    @Autowired
    ScMapper scMapper;

    @Override
    public BaseDao<Sc> getBaseDao() {
        return scMapper;
    }
}
