package fruit.controller;

import fruit.base.BaseController;
import fruit.po.News;
import fruit.service.NewsService;

import fruit.utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.jws.WebParam;
import java.util.Date;

@Controller
@RequestMapping("/news")
public class NewsController extends BaseController {

    @Autowired
    private NewsService newsService;

    @RequestMapping("/findBySql")
    private String findBySql(Model model,News news)
    {
        String sql="select * from news where 1=1 ";
        if (!isEmpty(news.getName()))
        {
         sql+=" and name like+'%"+news.getName()+"%'";
        }
        sql+=" order by id desc";
        Pager<News> pager=newsService.findBySqlRerturnEntity(sql);
        model.addAttribute("obj",news);
        model.addAttribute("pagers",pager);
        return "news/news";
    }

    @RequestMapping("/add")
    private String add()
    {
        return "news/add";
    }

    @RequestMapping("/exAdd")
    private String exAdd(News news)
    {
        news.setAddTime( new Date());
        newsService.insert(news);
        return "redirect:/news/findBySql.action";
    }

    @RequestMapping("/update")
    private String update(Integer id,Model model)
    {
        News news=newsService.load(id);
        model.addAttribute("obj",news);
        return "news/update";
    }

    @RequestMapping("/exUpdate")
    private String exUpdate( News news)
    {
        newsService.updateById(news);
        return "redirect:/news/findBySql.action";
    }

    @RequestMapping("/delete")
    private String delete(Integer id)
    {

        newsService.deleteById(id);
        return "redirect:/news/findBySql.action";
    }

    @RequestMapping("/list")
    private String list(Model model)
    {
        String sql="select * from news ";
        Pager<News> pager=newsService.findBySqlRerturnEntity(sql);
        model.addAttribute("pagers",pager);
        return "news/list";
    }

    @RequestMapping("/view")
    private String view(Integer id, Model model)
    {
        News news=newsService.load(id);
        model.addAttribute("obj",news);
        return "news/view";
    }
}
