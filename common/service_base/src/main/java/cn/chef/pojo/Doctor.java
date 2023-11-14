package cn.chef.pojo;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;


/**
* 医生
* @TableName tb_doctor
*/
@Data
public class Doctor implements Serializable {

    /**
    * 
    */
    @NotNull(message="[]不能为空")
    @ApiModelProperty("")
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
    * 
    */
    @ApiModelProperty("")
    private Long userId;
    /**
    * 
    */
    @ApiModelProperty("")
    @TableField("department_id")
    private Integer departmentId;
    /**
    * 
    */
    @Size(max= 20,message="编码长度不能超过20")
    @ApiModelProperty("")
    @Length(max= 20,message="编码长度不能超过20")
    private String name;
    /**
    * 医生信息
    */
    @Size(max= -1,message="编码长度不能超过-1")
    @ApiModelProperty("医生信息")
    @Length(max= -1,message="编码长度不能超过-1")
    private String info;
    /**
    * 头像
    */
    @Size(max= 100,message="编码长度不能超过100")
    @ApiModelProperty("头像")
    @Length(max= 100,message="编码长度不能超过100")
    private String avator;
    @ApiModelProperty("薪水")
    private long salary;
    private String position;
}
