package fruit.controller;

import com.alibaba.fastjson.JSONObject;
import fruit.base.BaseController;
import fruit.po.Message;
import fruit.service.MessageService;
import fruit.utils.Pager;
import org.aspectj.bridge.IMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/message")
public class MessageController extends BaseController {

    @Autowired
    private MessageService messageService;

    @RequestMapping("/findBySql")
    private String findBySql(Message message, Model model)
    {
        String sql="select * from message where 1=1";
        if(!isEmpty(message.getName())){
            sql+=" and name like '%"+message.getName()+"%'";
        }
        sql+=" order by id desc";
        Pager<Message> pager=messageService.findBySqlRerturnEntity(sql);
        model.addAttribute("pagers",pager);
        model.addAttribute("obj",message);
        return "message/message";
    }

    @RequestMapping("/add")
    private String add()
    {
        return "message/add";
    }

    @RequestMapping("/exAdd")
    @ResponseBody
    private String exAdd(Message message) {
        messageService.insert(message);
        JSONObject js = new JSONObject();
        js.put("message", "添加成功");
        return js.toString();
    }

    @RequestMapping("/delete")
    private String delete(Integer id)
    {
        messageService.deleteById(id);
        return "redirect:/message/findBySql.action";
    }
}
