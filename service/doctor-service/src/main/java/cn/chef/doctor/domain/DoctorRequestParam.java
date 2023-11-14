package cn.chef.doctor.domain;

import cn.chef.pojo.Doctor;
import lombok.Data;

@Data
public class DoctorRequestParam extends Doctor {
    private Integer page;
    private Integer size;

    private String phone;
}
