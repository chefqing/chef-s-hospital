package cn.chef.appointment.controller;

import cn.chef.appointment.domain.Sources;
import cn.chef.appointment.domain.SourcesRequestParam;
import cn.chef.appointment.service.service.SourcesService;
import cn.chef.utils.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sources")
public class SourcesController {
    @Autowired
    SourcesService sourcesService;
    @PostMapping("")
    @ApiOperation("添加号源信息")
    public Result createSources(@RequestBody Sources sources){
        return sourcesService.createSources(sources);
    }

    @ApiOperation("获取符合条件的号源")
    @PostMapping("/getAvailable")
    public  Result getAvailableSources(@RequestBody SourcesRequestParam requestParam){
        Result result = sourcesService.getAvailable(requestParam);
        return result;
    }


}
