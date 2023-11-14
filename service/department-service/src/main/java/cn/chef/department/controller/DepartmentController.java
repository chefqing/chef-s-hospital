package cn.chef.department.controller;

import cn.chef.department.service.DepartmentService;
import cn.chef.pojo.Department;
import cn.chef.utils.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/department")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @GetMapping("/list")
    @ApiOperation("获取所有科室信息")
    Result getDepartments(){
        return departmentService.getDepartments();
    }
    @PostMapping("")
    @ApiOperation("添加科室")
    Result addDepartment(@RequestBody Department department){
        return departmentService.addDepartment(department);
    }
    @ApiOperation("删除科室")
    @DeleteMapping("{id}")
    Result deleteDepartment(@PathVariable Long id){
        return departmentService.deleteById(id);
    }

    @ApiOperation("修改科室")
    @PutMapping("update")
    Result modifyDepartmentById(@RequestBody Department department){
        return departmentService.modifyById(department);
    }

}
