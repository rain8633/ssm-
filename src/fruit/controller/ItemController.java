package fruit.controller;

import fruit.base.BaseController;
import fruit.po.Item;
import fruit.po.ItemCategory;
import fruit.service.ItemCategoryService;
import fruit.service.ItemService;
import fruit.utils.Pager;
import fruit.utils.SystemContext;
import fruit.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("/item")
public class ItemController extends BaseController {
    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemCategoryService itemCategoryService;

    @RequestMapping("/findBySql")
    private String findBySql(Model model, Item item)
    {
        String sql="select * from item where isDelete=0";
        if (!isEmpty(item.getName()))
        {
             sql+=" and name like '%"+item.getName()+"%'";
        }
        sql+=" order by id desc";
        Pager<Item> pagers=itemService.findBySqlRerturnEntity(sql);
        model.addAttribute("pagers",pagers);
        model.addAttribute("obj",item);
        return "item/item";
    }

    @RequestMapping("/add")
    private String add(Model model)
    {
        String sql = "select * from item_category where isDelete = 0 and pid is not null order by id";
        List<ItemCategory> listBySqlReturnEntity = itemCategoryService.listBySqlReturnEntity(sql);
        model.addAttribute("types",listBySqlReturnEntity);
        return "item/add";
    }

    @RequestMapping("/exAdd")
    private String exAdd(Item item, @RequestParam("file") CommonsMultipartFile[] files, HttpServletRequest request) throws IOException
    {
        itemCommon(item,files,request);
        item.setGmNum(0);
        item.setIsDelete(0);
        item.setScNum(0);
        itemService.insert(item);
        return "redirect:/item/findBySql.action";
    }

    @RequestMapping("/update")
    private String update(Model model, Integer id)
    {
        Item obj=itemService.load(id);
        model.addAttribute("obj",obj);
        String sql = "select * from item_category where isDelete = 0 and pid is not null order by id";
        List<ItemCategory> listBySqlReturnEntity = itemCategoryService.listBySqlReturnEntity(sql);
        model.addAttribute("types",listBySqlReturnEntity);
        return "item/update";
    }

    @RequestMapping("/exUpdate")
    private String exUpdate(Item item, @RequestParam("file") CommonsMultipartFile[] files, HttpServletRequest request) throws IOException
    {
        itemCommon(item,files,request);
        itemService.updateById(item);
        return "redirect:/item/findBySql.action";
    }

    /**
     * 新增和更新的公共方法
     */
    private void itemCommon(Item item, @RequestParam("file") CommonsMultipartFile[] files, HttpServletRequest request) throws IOException {
        if(files.length>0) {
            for (int s = 0; s < files.length; s++) {
                String n = UUIDUtils.create();
                String path = SystemContext.getRealPath() + "\\resource\\ueditor\\upload\\" + n + files[s].getOriginalFilename();
                File newFile = new File(path);
                //通过CommonsMultipartFile的方法直接写文件
                files[s].transferTo(newFile);
                if (s == 0) {
                    item.setUrl1(request.getContextPath()+"\\resource\\ueditor\\upload\\" + n + files[s].getOriginalFilename());
                }
                if (s == 1) {
                    item.setUrl2(request.getContextPath()+"\\resource\\ueditor\\upload\\" + n + files[s].getOriginalFilename());
                }
                if (s == 2) {
                    item.setUrl3(request.getContextPath()+"\\resource\\ueditor\\upload\\" + n + files[s].getOriginalFilename());
                }
                if (s == 3) {
                    item.setUrl4(request.getContextPath()+"\\resource\\ueditor\\upload\\" + n + files[s].getOriginalFilename());
                }
                if (s == 4) {
                    item.setUrl5(request.getContextPath()+"\\resource\\ueditor\\upload\\" + n + files[s].getOriginalFilename());
                }
            }
        }
        ItemCategory byId = itemCategoryService.getById(item.getCategoryIdTwo());
        item.setCategoryIdOne(byId.getPid());
    }

    @RequestMapping("/delete")
private String delete(Integer id)
{
    Item item=itemService.load(id);
    item.setIsDelete(1);

    itemService.updateById(item);
     return "redirect:/item/findBySql.action";
}

/*@RequestMapping("/view")
private String view(Integer id,Model model)
{
    Item item=itemService.load(id);
    model.addAttribute("obj",item);
    return "item/view";
}*/

    @RequestMapping("/view")
    private String view(Integer id,Model model)
    {
        Item item=itemService.load(id);
        model.addAttribute("obj",item);
        return "item/view1";
    }

@RequestMapping("/shoplist")
private String shoplist(String condition,Item item,Model model)
{
    String sql="select * from item where isDelete=0 ";
    if(!isEmpty(item.getCategoryIdTwo())){
        sql+=" and category_id_two= "+item.getCategoryIdTwo();
    }
    if(!isEmpty(condition))
    {
        sql+=" and name like '%"+condition+"%'";
        model.addAttribute("condition",condition);
    }
    if (!isEmpty(item.getPrice()))
    {
        sql+="  order by (price+0) desc";//因为设计的price是varchar型的,不能识别两位数还是三位数,89>120,加0变成整型
    }
    if (!isEmpty(item.getScNum()))
    {
        sql+="  order by scNum desc ";
    }
    if(isEmpty(item.getPrice())&&isEmpty(item.getGmNum())){
        sql += " order by id desc";
    }

    Pager<Item> pagers = itemService.findBySqlRerturnEntity(sql);
    model.addAttribute("pagers",pagers);
    model.addAttribute("obj",item);
    return "item/shoplist";
}
}
