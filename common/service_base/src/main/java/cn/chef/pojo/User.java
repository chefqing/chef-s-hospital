package cn.chef.pojo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
* 用户表
* @TableName tb_user
*/
@Data
public class User implements Serializable {

    /**
    * 
    */
    @NotNull(message="[]不能为空")
    @ApiModelProperty("")
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
    * 用户名
    */
    @NotBlank(message="[用户名]不能为空")
    @Size(max= 20,message="编码长度不能超过20")
    @ApiModelProperty("用户名")
    @Length(max= 20,message="编码长度不能超过20")
    private String username;
    /**
    * 密码
    */
    @NotBlank(message="[密码]不能为空")
    @Size(max= 20,message="编码长度不能超过20")
    @ApiModelProperty("密码")
    @Length(max= 20,message="编码长度不能超过20")
    private String password;


}
