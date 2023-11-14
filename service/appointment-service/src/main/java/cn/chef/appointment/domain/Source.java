package cn.chef.appointment.domain;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;


/**
 *
 * @TableName tb_source
 */
@Data
public class Sources implements Serializable {

    /**
     * source_id
     */

    @ApiModelProperty("source_id")
    private Long id;
    /**
     * 号源时间
     */

    @ApiModelProperty("号源开始时间")
    private LocalTime startTime;


    @ApiModelProperty("号源结束时间")
    private  LocalTime endTime;
    /**
     * 号源医生
     */

    @ApiModelProperty("号源医生")
    private Long doctorId;
    /**
     * 号源科室
     */
    @ApiModelProperty("号源科室")
    private Long departmentId;
    /**
     * 号源日期
     */

    @ApiModelProperty("号源日期")
    private LocalDate date;
    /**
     * 号源状态
     */

    @ApiModelProperty("号源状态")
    private String status;

    @ApiModelProperty("号源库存")
    private Integer stock;
    /**
     * source_id
     */

}
