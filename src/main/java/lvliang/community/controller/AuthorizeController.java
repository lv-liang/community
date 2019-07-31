package lvliang.community.controller;

import lvliang.community.dto.AccessTokenDTO;
import lvliang.community.dto.GithubUser;
import lvliang.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 处理github登录返回的回调处理
 *
 */
@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;
    @RequestMapping("/callback")
    public String githubLoginCallback(
            @RequestParam(name="code") String code,
            @RequestParam(name="state")String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri("http://localhost:8887/callback");
        accessTokenDTO.setClient_id("27c7dccccf05ec0550c8");
        accessTokenDTO.setClient_secret("1f734dda357545365d33db87d5f7318613b651ea");
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println(user.getName());
        return "index";
    }
}
