package cn.chef.pojo;

import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;


/**
* 角色表
* @TableName tb_role
*/
@Data
public class Role implements Serializable {

    /**
    * 
    */
    @NotNull(message="[]不能为空")
    @ApiModelProperty("")
    private Integer id;
    /**
    * 角色名称
    */
    @Size(max= 20,message="编码长度不能超过20")
    @ApiModelProperty("角色名称")
    @Length(max= 20,message="编码长度不能超过20")
    private String name;

}
