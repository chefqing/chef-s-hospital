package cn.chef.appointment.service.service;

import cn.chef.sources.domain.Sources;
import cn.chef.sources.domain.SourcesRequestParam;
import cn.chef.utils.Result;
import com.baomidou.mybatisplus.extension.service.IService;

public interface SourcesService extends IService<Sources> {


    Result getAvailable(SourcesRequestParam requestParam);

    Result createSources(Sources sources);
}
