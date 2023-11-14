import cn.chef.pojo.Role;
import cn.hutool.json.JSONUtil;

import java.util.ArrayList;
import java.util.List;

public class JsonUtilTest {
    public static void main(String[] args) {
        List<Role> list = new ArrayList<>();
        Role role1 = new Role();
        role1.setId(1);
        role1.setName("xiba");
        Role role2= new Role();
        role2.setId(2);
        role2.setName("gouba");
        list.add(role1);
        list.add(role2);
        String string = JSONUtil.parse(list).toString();
        List<Role> list1 = JSONUtil.toList(string, Role.class);
        list1.stream().forEach((role -> {
            System.out.println(role.toString());
        }));
    }
}
