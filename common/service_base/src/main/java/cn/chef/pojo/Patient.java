package cn.chef.pojo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

/**
* 病人表
* @TableName tb_patient
*/
public class Patient implements Serializable {

    /**
    * 病人id
    */
    @NotNull(message="[病人id]不能为空")
    @ApiModelProperty("病人id")
    private Integer id;
    /**
    * 姓名
    */
    @NotBlank(message="[姓名]不能为空")
    @Size(max= 20,message="编码长度不能超过20")
    @ApiModelProperty("姓名")
    @Length(max= 20,message="编码长度不能超过20")
    private String name;
    /**
    * 性别
    */
    @NotBlank(message="[性别]不能为空")
    @Size(max= 10,message="编码长度不能超过10")
    @ApiModelProperty("性别")
    @Length(max= 10,message="编码长度不能超过10")
    private String sex;
    /**
    * 年龄
    */
    @NotNull(message="[年龄]不能为空")
    @ApiModelProperty("年龄")
    private Integer age;
    /**
    * 
    */
    @NotNull(message="[]不能为空")
    @ApiModelProperty("")
    private Integer refUserId;
    /**
    * 身份证号
    */
    @NotBlank(message="[身份证号]不能为空")
    @Size(max= 20,message="编码长度不能超过20")
    @ApiModelProperty("身份证号")
    @Length(max= 20,message="编码长度不能超过20")
    private String idcard;

    /**
    * 病人id
    */
    private void setId(Integer id){
    this.id = id;
    }

    /**
    * 姓名
    */
    private void setName(String name){
    this.name = name;
    }

    /**
    * 性别
    */
    private void setSex(String sex){
    this.sex = sex;
    }

    /**
    * 年龄
    */
    private void setAge(Integer age){
    this.age = age;
    }

    /**
    * 
    */
    private void setRefUserId(Integer refUserId){
    this.refUserId = refUserId;
    }

    /**
    * 身份证号
    */
    private void setIdcard(String idcard){
    this.idcard = idcard;
    }


    /**
    * 病人id
    */
    private Integer getId(){
    return this.id;
    }

    /**
    * 姓名
    */
    private String getName(){
    return this.name;
    }

    /**
    * 性别
    */
    private String getSex(){
    return this.sex;
    }

    /**
    * 年龄
    */
    private Integer getAge(){
    return this.age;
    }

    /**
    * 
    */
    private Integer getRefUserId(){
    return this.refUserId;
    }

    /**
    * 身份证号
    */
    private String getIdcard(){
    return this.idcard;
    }

}
