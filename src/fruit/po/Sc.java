package fruit.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sc {

  private Integer id;
  private Integer itemId;
  private Item item;
  private Integer userId;



}
