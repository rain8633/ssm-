package fruit.service.impl;

import fruit.base.BaseDao;
import fruit.base.BaseServiceImpl;
import fruit.mapper.ManageMapper;
import fruit.po.Manage;
import fruit.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ManageServiceImpl extends BaseServiceImpl<Manage> implements ManageService {
    @Autowired
    ManageMapper manageMapper;

    @Override
    public BaseDao<Manage> getBaseDao() {
        return manageMapper;
    }
}
