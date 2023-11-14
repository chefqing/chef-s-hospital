package cn.chef.appointment.pojo;

import java.io.Serializable;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
* 
* @TableName tb_source
*/
@Data
public class Source implements Serializable {


    @ApiModelProperty("source_id")
    private Integer id;
    /**
    * 号源时间
    */

    @ApiModelProperty("号源时间")

    private String time;
    /**
    * 号源医生
    */

    @ApiModelProperty("号源医生")
    private Integer doctorId;
    /**
    * 号源科室
    */

    @ApiModelProperty("号源科室")
    private Integer departmentId;

    @ApiModelProperty("号源日期")
    private Date date;
    /**
    * 号源状态
    */

    @ApiModelProperty("号源状态")
    private String status;

}
