package fruit.po;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class News {

  private Integer id;
  private String name;
  private String content;
  private Date addTime;



}
