package cn.chef.pojo;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
* 用户角色表
* @TableName tb_user_role
*/
public class UserRole implements Serializable {

    /**
    * 用户id
    */
    @NotNull(message="[用户id]不能为空")
    @ApiModelProperty("用户id")
    private Integer userId;
    /**
    * 角色id
    */
    @NotNull(message="[角色id]不能为空")
    @ApiModelProperty("角色id")
    private Integer roleId;

    /**
    * 用户id
    */
    private void setUserId(Integer userId){
    this.userId = userId;
    }

    /**
    * 角色id
    */
    private void setRoleId(Integer roleId){
    this.roleId = roleId;
    }


    /**
    * 用户id
    */
    private Integer getUserId(){
    return this.userId;
    }

    /**
    * 角色id
    */
    private Integer getRoleId(){
    return this.roleId;
    }

}
