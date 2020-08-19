package fruit.controller;

import com.github.pagehelper.Page;
import fruit.po.Item;
import fruit.po.Sc;
import fruit.service.ItemService;
import fruit.service.ScService;
import fruit.utils.Consts;
import fruit.utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/sc")
public class ScController {
    @Autowired
    private ScService scService;
    
    @Autowired
    private ItemService itemService;

    @RequestMapping("/findBySql")
    private String findBySql(Sc sc, HttpServletRequest request, Model model)
    {
        Object obj=request.getSession().getAttribute(Consts.USERID);
        if(obj==" "|| obj==null)
        {
            return "redirect:/sc/findBySql.action";
        }
        Integer id=Integer.valueOf(obj.toString());

        String sql="select * from sc where user_id="+id+" order by id desc";
        Pager<Sc> page=scService.findBySqlRerturnEntity(sql);
        model.addAttribute("pagers",page);
        return "sc/my";


    }

    @RequestMapping("/exAdd")
    private String exAdd(Sc sc, HttpServletRequest request)
    {
Object obj=request.getSession().getAttribute(Consts.USERID);

        if(obj==null){
            return "redirect:/login/uLogin";
        }
        //保存到收藏表
        Integer userId = Integer.valueOf(obj.toString());
        sc.setUserId(userId);
        scService.insert(sc);
        //商品表收藏数+1
        Item item = itemService.load(sc.getItemId());
        item.setScNum(item.getScNum()+1);
        itemService.updateById(item);
        return "redirect:/sc/findBySql.action";
    }

    @RequestMapping("/delete")
    private String delete(Integer id,HttpServletRequest request)
    {
        Object attribute = request.getSession().getAttribute(Consts.USERID);
        if(attribute==null){
            return "redirect:/login/uLogin";
        }
        scService.deleteById(id);
        return "redirect:/sc/findBySql.action";
    }
}
