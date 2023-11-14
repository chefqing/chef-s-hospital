package cn.chef.user.mapper;

import cn.chef.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<String> selectRolesByUserId(Long userId);
}
