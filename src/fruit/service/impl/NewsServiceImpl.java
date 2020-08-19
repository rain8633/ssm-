package fruit.service.impl;

import fruit.base.BaseDao;
import fruit.base.BaseServiceImpl;

import fruit.mapper.NewsMapper;

import fruit.po.News;

import fruit.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class NewsServiceImpl extends BaseServiceImpl<News> implements NewsService {
    @Autowired
    NewsMapper newsMapper;

    @Override
    public BaseDao<News> getBaseDao() {
        return newsMapper;
    }
}
