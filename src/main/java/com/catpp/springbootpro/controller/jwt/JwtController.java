package com.catpp.springbootpro.controller.jwt;

import com.catpp.springbootpro.common.JsonResult;
import com.catpp.springbootpro.pojo.JwtToken;
import com.catpp.springbootpro.pojo.JwtUser;
import com.catpp.springbootpro.service.JwtTokenService;
import com.catpp.springbootpro.service.JwtUserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * com.catpp.springbootpro.controller.jwt
 *
 * @Author cat_pp
 * @Date 2018/10/22
 * @Description
 */
@RestController
@RequestMapping("/jwt")
public class JwtController {

    @Autowired
    private JwtUserService userService;
    @Autowired
    private JwtTokenService tokenService;

    /**
     * 获取token，更新token
     * @param appId 用户编号
     * @param appSecret 用户密码
     * @return token
     */
    @RequestMapping(value = "/token", method = {RequestMethod.POST, RequestMethod.GET})
    public JsonResult getToken(String appId, String appSecret) {
        JsonResult jsonResult = JsonResult.ok();

        if (StringUtils.isEmpty(appId) || StringUtils.isEmpty(appId.trim())) {
            // appId is null
            jsonResult.setMsg("appId is not found");
            jsonResult.setStatus(401);
        } else if (StringUtils.isEmpty(appSecret) || StringUtils.isEmpty(appSecret.trim())) {
            // appSecret is null
            jsonResult.setMsg("appSecret is not found");
            jsonResult.setStatus(401);
        } else {
            // 根据appId查询用户信息
            JwtUser user = userService.selectOne(appId);
            if (null == user) {
                // 如果用户为空
                jsonResult.setMsg("appId:" + appId + "is not found");
                jsonResult.setStatus(401);
            } else if (!StringUtils.equals(appSecret.replace(" ", "+"), new String(user.getAppSecret()))) {
                // 判断appSecret是否存在
                jsonResult.setStatus(401);
                jsonResult.setMsg("appSecret is not effective");
            } else {
                // 查看数据库是否存在该appId的token
                JwtToken token = tokenService.selectOneByAppId(appId);
                // 返回token值
                String tokenStr = null;
                if (null == token) {
                    // null == token：生成新token->保存数据库->写入内存->返回新token
                    tokenStr = createNewToken(appId);
                    token = new JwtToken();
                    token.setAppId(appId);
                    token.setBuildTime(String.valueOf(System.currentTimeMillis()));
                    token.setToken(tokenStr.getBytes());
                    tokenService.save(token);
                } else {
                    /**
                     * null != token：验证是否超时
                     * 不超时，返回token
                     * 超时，生成新token->更新token->更新内存token->返回新token
                     */
                    // 判断数据库中的token是否过期，如果没过期直接返回
                    // 数据库中token生成时间
                    long tokenBuildTime = Long.valueOf(token.getBuildTime());
                    // 当前时间
                    long currentTime = System.currentTimeMillis();
                    // 判断tokne是否过期
                    long second = TimeUnit.MILLISECONDS.toSeconds(currentTime - tokenBuildTime);
                    if (second > 0 && second < 7200) {
                        tokenStr = new String(token.getToken());
                    } else {
                        // 超时
                        tokenStr = createNewToken(appId);
                        // 更新token
                        token.setToken(tokenStr.getBytes());
                        // 更新生成时间
                        token.setBuildTime(String.valueOf(System.currentTimeMillis()));
                        // 更新数据
                        tokenService.update(token);
                    }
                }
                // 返回token
                jsonResult.setData(tokenStr);
            }
        }
        return jsonResult;
    }

    /**
     * 生成token
     * @param appId 用户编号
     * @return
     */
    private String createNewToken(String appId) {
        // 当前时间
        Date now = new Date(System.currentTimeMillis());
        // 过期时间
        Date expiration = new Date(now.getTime() + 7200000);
        return Jwts.builder()
                .setSubject(appId)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, "catppAuth")
                .compact();
    }
}
