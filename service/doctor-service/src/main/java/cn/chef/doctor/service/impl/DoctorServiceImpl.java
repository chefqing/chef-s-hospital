package cn.chef.doctor.service.impl;

import cn.chef.doctor.domain.DoctorRequestParam;
import cn.chef.doctor.mapper.DoctorMapper;
import cn.chef.doctor.service.DoctorService;
import cn.chef.doctor.service.UserService;
import cn.chef.pojo.Doctor;
import cn.chef.pojo.User;
import cn.chef.utils.MD5;
import cn.chef.utils.Result;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DoctorServiceImpl extends ServiceImpl<DoctorMapper,Doctor> implements DoctorService {
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    UserService userService;
    // todo 做缓存
    @Override
    @Cacheable(value = "doctors",key = "'cache'+#requestParam.departmentId+#requestParam.page+#requestParam.size")
    public Result getPageByDepartmentId(DoctorRequestParam requestParam) {
        int page = requestParam.getPage();
        int size = requestParam.getSize();
        Page<Doctor> doctors = query().eq("department_id", requestParam.getDepartmentId()).page(new Page<>(page, size));
        return Result.ok().data("items",doctors.getRecords()).data("total",doctors.getTotal());
    }

    //todo 通过用户名查询医生/放es中
    @Override
    public Result getDoctorByName(DoctorRequestParam doctorRequestParam) {
        int page = doctorRequestParam.getPage();
        int size = doctorRequestParam.getSize();
        String doctorName = doctorRequestParam.getName();
        query().like("name",doctorName).page(new Page<>(page,size));
        return Result.ok();
    }

    @Override
    public Result modify(Doctor doctor) {
        return Result.ok();
    }
    @Override
    // todo  不应该在此服务中
    public Result saveDoctor(DoctorRequestParam doctorRequestParam) {
        User user = new User();
        String password = MD5.encrypt("user"+doctorRequestParam.getPhone());
        if(doctorRequestParam.getUserId()==null){
            user.setUsername(doctorRequestParam.getPhone());
            user.setPassword(password);
            userService.save(user);
            doctorRequestParam.setUserId(user.getId());
        }
        boolean isSuccess = save(doctorRequestParam);
        if (isSuccess){
            return Result.ok();
        }
        return Result.error().message("保存失败");
    }
}
