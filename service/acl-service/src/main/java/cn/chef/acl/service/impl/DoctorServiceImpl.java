package cn.chef.acl.service.impl;

import cn.chef.acl.mapper.DoctorMapper;
import cn.chef.acl.service.DoctorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.chef.pojo.Doctor;

import org.springframework.stereotype.Service;

/**
* @author chef
* @description 针对表【tb_doctor(医生)】的数据库操作Service实现
* @createDate 2023-10-07 16:36:42
*/
@Service
public class DoctorServiceImpl extends ServiceImpl<DoctorMapper, Doctor>
    implements DoctorService {

}




