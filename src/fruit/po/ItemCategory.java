package fruit.po;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemCategory {

  private Integer id;
  private String name;
  private Integer pid;
  private Integer isDelete;




}
