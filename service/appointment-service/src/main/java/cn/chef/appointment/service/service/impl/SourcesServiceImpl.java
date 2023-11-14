package cn.chef.sources.service.impl;


import cn.chef.sources.domain.Sources;
import cn.chef.sources.domain.SourcesRequestParam;
import cn.chef.sources.mapper.SourcesMapper;
import cn.chef.sources.service.SourcesService;
import cn.chef.utils.RedisConstants;
import cn.chef.utils.Result;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service("SourcesService")
public class SourcesServiceImpl extends ServiceImpl<SourcesMapper, Sources> implements SourcesService {

    @Autowired
    StringRedisTemplate redisTemplate;
    ExecutorService executorService  = Executors.newSingleThreadExecutor();


    @Override
    // todo 缓存
    public Result getAvailable(SourcesRequestParam requestParam) {
        Long doctorId = requestParam.getDoctorId();
        if (doctorId==null){
            return Result.error().message("请先选择医生");
        }
        LocalDate date = requestParam.getDate();
        List<Sources> sourcesList = query().eq("doctor_id", doctorId).eq("date", date).list();
        return Result.ok().data("total", sourcesList.size()).data("items", sourcesList);
    }

    @Override
    public Result createSources(Sources sources) {

        //1. 校验参数
        Long doctorId = sources.getDoctorId();
        Long departmentId = sources.getDepartmentId();
        LocalDate date = sources.getDate();
        LocalTime startTime = sources.getStartTime();
        LocalTime endTime = sources.getEndTime();
        Integer stock = sources.getStock();
        if(doctorId==null&&departmentId==null){
            return Result.error().message("参数错误");
        }
        LocalDate now = LocalDate.now();
        if(date==null||date.isAfter(now)||date.isBefore(now.plusMonths(1))){
            return Result.error().message("请输入从现在开始，一个月以内的号源");
        }
        if(startTime==null||endTime==null){
            return Result.error().message("请输入号源的时间段");
        }
        if (stock==null||stock<=0||stock>80){
            return Result.error().message("请将库存设定为0-80");
        }

        //2. 存入Redis

        String key = String.format("%s:%d:%s:%s", RedisConstants.CACHE_SOURCES, doctorId, date, startTime);        Map<String, Object> map = BeanUtil.beanToMap(sources,new HashMap<>(),
                CopyOptions.create()
                        .setFieldValueEditor((filedName,filedValue)->{
                            if (filedValue==null){
                                return "";
                            }
                            return filedValue.toString();
                        })
                        .ignoreNullValue()
        );

        //2.1 ttl
        Duration ttl = Duration.between(date.atStartOfDay(), LocalDate.now().atStartOfDay()).plusDays(1);
        redisTemplate.opsForHash().putAll(key, map);
        redisTemplate.expire(key, ttl.toDays(), TimeUnit.DAYS);

        //3. 异步更新数据库

        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                save(sources);
            } catch (Exception e) {
                // 异常处理，例如记录日志
                log.error("Error updating database asynchronously: " + e.getMessage(), e);
            }
        }, executorService);

        //4. 返回 CompletableFuture 的结果
        future.exceptionally(ex -> {
            // 在这里处理异常，例如记录日志或者返回一个默认的 Result
            log.error("Async database update failed: " + ex.getMessage(), ex);
            return null;  // 这里可以返回一个默认的 Result，或者抛出一个自定义异常
        });

        return Result.ok();
    }
}
