package cn.chef.department.service.impl;


import cn.chef.department.mapper.DepartmentMapper;
import cn.chef.department.service.DepartmentService;
import cn.chef.utils.Result;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.chef.pojo.Department;

import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author chef
* @description 针对表【tb_department(科室表)】的数据库操作Service实现
* @createDate 2023-10-07 16:36:42
*/
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department>
    implements DepartmentService {
    @Override
    @ApiOperation("获取所有科室的信息")
    public Result getDepartments() {
        List<Department> list = this.list();
        return Result.ok().data("data",list);
    }

    @Override
    public Result addDepartment(Department department) {
        boolean save = save(department);
        if (save){
            return Result.ok();
        }
        return Result.error().message("保存失败");
    }

    @Override
    public Result deleteById(Long id) {
        boolean isSuccess = this.removeById(id);
        if (isSuccess){
            return Result.ok();
        }else {
            return Result.error().message("删除失败");
        }
    }

    @Override
    public Result modifyById(Department department) {
        boolean isSuccess = this.update().eq("id",department.getId()).update(department);
        if (isSuccess){
            return Result.ok();
        }
        return Result.error().message("更新失败");
    }
}




