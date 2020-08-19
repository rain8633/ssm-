package fruit.service.impl;

import fruit.base.BaseDao;
import fruit.base.BaseServiceImpl;
import fruit.mapper.CarMapper;
import fruit.mapper.CommentMapper;
import fruit.po.Car;
import fruit.po.Comment;
import fruit.service.CarService;
import fruit.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CommentServiceImpl extends BaseServiceImpl<Comment> implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public BaseDao<Comment> getBaseDao() {
        return commentMapper;
    }
}
