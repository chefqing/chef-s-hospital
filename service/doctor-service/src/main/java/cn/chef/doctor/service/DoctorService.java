package cn.chef.doctor.service;

import cn.chef.doctor.domain.DoctorRequestParam;
import cn.chef.pojo.Doctor;
import cn.chef.utils.Result;
import com.baomidou.mybatisplus.extension.service.IService;


public interface DoctorService extends IService<Doctor> {
    Result getPageByDepartmentId(DoctorRequestParam departmentId);

    Result getDoctorByName(DoctorRequestParam doctorRequestParam);

    Result modify(Doctor doctor);

    Result saveDoctor(DoctorRequestParam doctorRequestParam);
}
