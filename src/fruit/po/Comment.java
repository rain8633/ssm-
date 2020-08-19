package fruit.po;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

  private Integer id;
  private Integer userId;
  private User user;
  private Integer itemId;
  private String content;
  private Date addTime;


}
