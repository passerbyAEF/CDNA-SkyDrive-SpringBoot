package com.example.cdnaskydrivejava.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.cdnaskydrivejava.model.UserMode;
import com.example.cdnaskydrivejava.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    RedisUtil redisUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = null;
        for (Cookie c : request.getCookies()) {
            if (c.getName().equals("Token"))
                token = c.getValue();
        }
        if(token!=null){
            String str=redisUtil.get(token);
            if(str!=null){
                JSONObject redisJson = JSONObject.parseObject(str);
                UserMode user = new UserMode();
                user.setFileId(Integer.parseInt(redisJson.getString("fileId")));
                user.setId(Integer.parseInt(redisJson.getString("id")));
                user.setName(redisJson.getString("name"));
                user.setPwd(redisJson.getString("pwd"));
                List<GrantedAuthority> authList = new ArrayList<>();
                for(Object o: redisJson.getJSONArray("authorities")){
                    JSONObject j=(JSONObject) o;
                    authList.add(new SimpleGrantedAuthority(j.getString("authority")));
                }
                user.setAuthorities(authList);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request,response);
    }
}
