package cn.chef.user.mapper;

import cn.chef.pojo.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;


/**
* @author chef
* @description 针对表【tb_role(角色表)】的数据库操作Mapper
* @createDate 2023-09-28 23:51:47
* @Entity com.chef.aclservice.Role
*/
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

}




