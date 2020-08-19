package fruit.controller;

import com.github.pagehelper.Page;
import fruit.base.BaseController;
import fruit.po.ItemCategory;
import fruit.service.ItemCategoryService;
import fruit.utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/itemCategory")
public class ItemCategoryController extends BaseController {

    @Autowired
    private ItemCategoryService itemCategoryService;

    @RequestMapping(value = "/findBySql")
    public String findBySql(Model model,ItemCategory itemCategory)
    {
       String sql="select * from item_Category where isDelete = 0 and pid is null order by id";
       Pager<ItemCategory> pagers=itemCategoryService.findBySqlRerturnEntity(sql);
       model.addAttribute("pagers",pagers);
       model.addAttribute("obj",itemCategory);
        return "itemCategory/itemCategory";
    }

    /**
     * 转向新增页面
     */
    @RequestMapping("/add")
    public String add()
    {
      return "itemCategory/add";
    }

    /**
     * 保存新增
     */
    @RequestMapping("/exAdd")
    public String exAdd(ItemCategory itemCategory)
    {
       itemCategory.setIsDelete(0);
       itemCategoryService.insert(itemCategory);
       return "redirect:/itemCategory/findBySql.action";
    }

    /**
     * 删除一级类目
     */
    @RequestMapping("/delete")
    public String delete(Integer id)
    {
        //获取该类目并删除自身
        ItemCategory load=itemCategoryService.load(id);
        load.setIsDelete(1);
        itemCategoryService.updateById(load);

        //删除二级类目
        String sql="update item_category set isDelete=1 where pid="+id;
        itemCategoryService.updateBysql(sql);
      return "redirect:/itemCategory/findBySql.action";
    }

    /**
     * 跳转到修改页面
     */
    @RequestMapping("/update")
    public String update(Model model,Integer id)
    {
        ItemCategory load=itemCategoryService.load(id);
        model.addAttribute("obj",load);
        return "itemCategory/update";
    }

    /**
     * 修改类目信息
     */
    @RequestMapping("/exUpdate")
    public String exUpdate(ItemCategory itemCategory)
    {
        itemCategoryService.updateById(itemCategory);
        return "redirect:/itemCategory/findBySql.action";
    }

    /**
     * 查看二级类目
     */
    @RequestMapping("/findBySql2")
    public String findBySql2(Model model,ItemCategory itemCategory)
    {
        String sql2="select * from item_category where isDelete=0 and pid="+itemCategory.getPid()+" order by id";
        Pager<ItemCategory> pagers=itemCategoryService.findBySqlRerturnEntity(sql2);
        model.addAttribute("pagers",pagers);
        model.addAttribute("obj",itemCategory);
        return "itemCategory/itemCategory2";
    }

    /**
     * 转向二级类目新增页面
     */
    @RequestMapping("/add2")
    public String add2(int pid,Model model)
    {
        model.addAttribute("pid",pid);
        return "itemCategory/add2";
    }

    /**
     * 保存新增
     */
    @RequestMapping("/exAdd2")
    public String exAdd2(ItemCategory itemCategory)
    {
        itemCategory.setIsDelete(0);
        itemCategoryService.insert(itemCategory);
        return "redirect:/itemCategory/findBySql2.action?pid="+itemCategory.getPid();
    }

    /**
     * 删除二级类目
     */
    @RequestMapping("/delete2")
    public String delete2(Integer id,Integer pid)
    {
        //获取该类目并删除自身
        ItemCategory load=itemCategoryService.load(id);
        load.setIsDelete(1);
        itemCategoryService.updateById(load);
        return "redirect:/itemCategory/findBySql2.action?pid="+pid;
    }

    /**
     * 跳转到修改页面
     */
    @RequestMapping("/update2")
    public String update2(Model model,Integer id)
    {
        ItemCategory load=itemCategoryService.load(id);
        model.addAttribute("obj",load);
        return "itemCategory/update2";
    }

    /**
     * 修改二级类目信息
     */
    @RequestMapping("/exUpdate2")
    public String exUpdate2(ItemCategory itemCategory)
    {
        itemCategoryService.updateById(itemCategory);
        return "redirect:/itemCategory/findBySql2.action?pid="+itemCategory.getPid();
    }
}
