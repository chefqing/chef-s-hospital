package cn.chef.acl.service.impl;

import cn.chef.acl.mapper.PatientMapper;
import cn.chef.acl.service.PatientService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.chef.pojo.Patient;

import org.springframework.stereotype.Service;

/**
* @author chef
* @description 针对表【tb_patient(病人表)】的数据库操作Service实现
* @createDate 2023-10-07 16:36:42
*/
@Service
public class PatientServiceImpl extends ServiceImpl<PatientMapper, Patient>
    implements PatientService {

}




