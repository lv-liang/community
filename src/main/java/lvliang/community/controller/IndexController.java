package lvliang.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController{
    /*
     * "/"为根目录
     */
    @RequestMapping("/")
    public String index(){
        return "index";
    }

}
