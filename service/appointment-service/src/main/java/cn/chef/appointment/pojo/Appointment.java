package cn.chef.appointment.pojo;


import java.io.Serializable;

import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
* 
* @TableName tb_appointment
*/
@Data
public class Appointment implements Serializable {

    /**
    * 预约id
    */

    @ApiModelProperty("预约id")
    private Integer id;
    /**
    * 用户id
    */

    @ApiModelProperty("用户id")
    private Integer userId;
    /**
    * 病人id
    */

    @ApiModelProperty("病人id")
    private Integer patientId;
    /**
    * 医生id
    */

    @ApiModelProperty("医生id")
    private Integer doctorId;
    /**
    * 预约信息
    */
    @ApiModelProperty("预约信息")
    private Date date;

    @ApiModelProperty("科室id")
    private Integer departmentId;




}
