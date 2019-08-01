package lvliang.community.controller;

import lvliang.community.dto.AccessTokenDTO;
import lvliang.community.dto.GithubUser;
import lvliang.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 处理github-authorize接口的回调处理
 *
 */
@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;
    //引入配置文件中的 ClientId，ClientSecret，RedirectUri
    //在spring容器启动时会将配置文件中的信息加载到一个map中我们就可以在这里获取
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;
    /**
     * @description
     * @param code
     * @param state
     * @return  java.lang.String
     * @author  lvliang
     * @date  2019/8/1 8:09
     *
     */
    @RequestMapping("/callback")
    public String githubLoginCallback(
            @RequestParam(name="code") String code,
            @RequestParam(name="state")String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println(user.getName());
        return "index";
    }
}
