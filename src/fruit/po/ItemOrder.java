package fruit.po;




import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemOrder implements Serializable {

  private Integer id;
  private Integer itemId;
  private Integer userId;
  private User user;
  private String code;
  private Date addTime;
  private String total;
  private Integer isDelete;
  private Integer status;
  private List<OrderDetail> details;




}
