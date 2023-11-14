package cn.chef.doctor.controller;

import cn.chef.doctor.domain.DoctorRequestParam;
import cn.chef.doctor.service.DoctorService;
import cn.chef.pojo.Doctor;
import cn.chef.utils.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {
    @Autowired
    DoctorService doctorService;
    @ApiOperation("根据部门列出医生")
    @PostMapping("list")
    Result getByDepartmentId(@RequestBody DoctorRequestParam doctorRequestParam){

        return doctorService.getPageByDepartmentId(doctorRequestParam);
    }
    @ApiOperation("通过用户名查询医生")
    @PostMapping("filter")
    Result getDoctorByName(@RequestBody DoctorRequestParam doctorRequestParam){
        return doctorService.getDoctorByName(doctorRequestParam);
    }

    @ApiOperation("添加医生")
    @PostMapping("")
    Result saveDoctor(@RequestBody DoctorRequestParam doctorRequestParam){
        return doctorService.saveDoctor(doctorRequestParam);
    }

    @ApiOperation("删除医生/逻辑删除")
    @DeleteMapping("{id}")
    Result deleteDoctor(@PathVariable Long id ){
        System.out.println(id);
        return Result.error().message("暂时还没实现");
    }


    // todo  修改医生信息，考虑数据库的同步问题
    @PutMapping()
    Result modifyDoctorInfo(@RequestBody Doctor doctor){
        return doctorService.modify(doctor);
    }
}
