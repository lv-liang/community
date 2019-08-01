package lvliang.community.provider;


import com.alibaba.fastjson.JSON;
import lvliang.community.dto.AccessTokenDTO;
import lvliang.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * github第三方提供者
 */
@Component
public class GithubProvider {
     /**
      * @description 获取github返回的AccessToken,这个对象的参数设置在callback方法中设置的
      * @params accessTokenDTO 实例化的accessTokenDTO对象
      * @return
      * @author  lvliang
      * @date  2019/8/1 8:13
      */
    public String getAccessToken(AccessTokenDTO accessTokenDTO){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType,JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            /*
            得到的内容格式为：
            access_token=e8d0d6be5d6a0594e713ea6adec106cb3ee470a0&scope=user&token_type=bearer
            在这里进行拆分操作，获取access_token的值
             */
            String string = response.body().string();
            String [] split = string.split("&");
            String tokenStr = split[0];
            String token = tokenStr.split("=")[1];
            return token;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @description 得到用户对象
     * @params accessToken传入Token对象获取用户信息
     * @return githubUser
     * @author  lvliang
     * @date  2019/8/1 8:16
     */
    public GithubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            GithubUser githubUser = JSON.parseObject(string,GithubUser.class);
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
