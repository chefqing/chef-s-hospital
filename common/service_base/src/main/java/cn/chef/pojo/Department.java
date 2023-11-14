package cn.chef.pojo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
* 科室表
* @TableName tb_department
*/
@Data
public class Department implements Serializable {

    /**
    * 
    */
    @NotNull(message="[]不能为空")
    @ApiModelProperty("")
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
    * 科室名字
    */
    @NotBlank(message="[科室名字]不能为空")
    @ApiModelProperty("科室名字")

    private String name;
    /**
    * 科室地理位置
    */
    @ApiModelProperty("科室地理位置")
    private String location;
    /**
    * 科室信息
    */

    @ApiModelProperty("科室信息")

    private String info;

    @ApiModelProperty("主管")
    private String manager;
}
