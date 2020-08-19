package fruit.controller;

import com.alibaba.fastjson.JSONObject;
import fruit.po.Car;
import fruit.po.Item;
import fruit.service.CarService;
import fruit.service.ItemService;
import fruit.utils.Consts;
import fruit.utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Controller
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarService carService;

    @Autowired
    private ItemService itemService;

    @RequestMapping("/findBySql")
public String findBySql(HttpServletRequest request, Model model)
{
    Object obj=request.getSession().getAttribute(Consts.USERID);
    if(obj==null)
    {
        return "redirect:/login/toLogin";
    }
    Integer userId=Integer.valueOf(obj.toString());
    String sql="select * from car where user_id="+userId+" order by id desc";
    List<Car> list=carService.listBySqlReturnEntity(sql);
    model.addAttribute("list",list);
    return "car/car";
}

@RequestMapping("/exAdd")
@ResponseBody
public String exAdd(Car car,HttpServletRequest request)
{
    JSONObject js = new JSONObject();
    Object attribute = request.getSession().getAttribute(Consts.USERID);
    if(attribute==null){
        js.put(Consts.RES,0);
        return js.toJSONString();
    }
    //保存到购物车
    Integer userId = Integer.valueOf(attribute.toString());
    car.setUserId(userId);
    Item item = itemService.load(car.getItemId());
    String price = item.getPrice();
    Double valueOf = Double.valueOf(price);
    car.setPrice(valueOf);
    if(item.getZk()!=null){
        valueOf = valueOf*item.getZk()/10;
        BigDecimal bg = new BigDecimal(valueOf).setScale(2, RoundingMode.UP);//保留两位小数
        car.setPrice(bg.doubleValue());
        valueOf = bg.doubleValue();
    }
    Integer num = car.getNum();
    Double t = valueOf*num;

    BigDecimal bg = new BigDecimal(t).setScale(2, RoundingMode.UP);
    double doubleValue = bg.doubleValue();
    car.setTotal(doubleValue+"");
    carService.insert(car);
    js.put(Consts.RES,1);
    return js.toJSONString();

}

@RequestMapping("/delete")
@ResponseBody
public String delete(Integer id)
{
carService.deleteById(id);
return "success";
}
}
