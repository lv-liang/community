package lvliang.community.provider;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class UserInfo{
    public Map getUserInfo(String userName,String password){
        Map map = new ConcurrentHashMap();
        map.put("code","ok");
        map.put("msg","success");
        return map;
    }
}

public class TestDemo {
    public static void main(String[] args) {
        UserInfo userInfo = new UserInfo();
        Map userInfo1 = userInfo.getUserInfo("lvliang", "dsadsa");
        System.out.println(userInfo1.toString());
    }


}
