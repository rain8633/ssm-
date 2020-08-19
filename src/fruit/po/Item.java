package fruit.po;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {

  private Integer id;
  private String name;
  private String price;
  private Integer zk;
  private Integer scNum;
  private Integer gmNum;
  private String url1;
  private String url2;
  private String url3;
  private String url4;
  private String url5;
  private String ms;
  private String pam1;
  private String pam2;
  private String pam3;
  private String val1;
  private String val2;
  private String val3;
  private Integer type;
  private Integer categoryIdOne;
  private ItemCategory yiji;
  private Integer categoryIdTwo;
  private ItemCategory erji;
  private Integer isDelete;

  private List<Comment> pls;
}
