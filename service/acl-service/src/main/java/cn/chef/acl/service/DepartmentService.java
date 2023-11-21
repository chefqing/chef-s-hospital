package cn.chef.department.service;

import cn.chef.pojo.Department;
import cn.chef.utils.Result;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author chef
* @description 针对表【tb_department(科室表)】的数据库操作Service
* @createDate 2023-10-07 16:36:42
*/
public interface DepartmentService extends IService<Department> {

    Result getDepartments();

    Result addDepartment(Department department);

    Result deleteById(Long id);

    Result modifyById(Department department);
}
