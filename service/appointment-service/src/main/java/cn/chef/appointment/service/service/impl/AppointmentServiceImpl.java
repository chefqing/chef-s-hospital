package cn.chef.appointment.service.service.impl;

import cn.chef.appointment.mapper.AppointmentMapper;
import cn.chef.appointment.pojo.Appointment;
import cn.chef.appointment.service.service.AppointmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
* @author chef
* @description 针对表【tb_appointment】的数据库操作Service实现
* @createDate 2023-10-19 19:36:36
*/
@Service
public class AppointmentServiceImpl extends ServiceImpl<AppointmentMapper, Appointment>
    implements AppointmentService {

}




