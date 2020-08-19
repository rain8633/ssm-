package fruit.po;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {

  private Integer id;
  private Integer itemId;
  private Item item;
  private Integer orderId;
  private Integer status;
  private Integer num;
  private String total;



}
