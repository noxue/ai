package com.ai.shiro.matcher;


import com.ai.domain.vo.JwtAccount;
import com.ai.util.JsonWebTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.springframework.stereotype.Component;


/* *
 * @Author tomsun28
 * @Description 
 * @Date 18:01 2018/3/3
 */
@Component
public class JwtMatcher implements CredentialsMatcher {


    @Override
    public boolean doCredentialsMatch(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) {

        String jwt = (String) authenticationInfo.getCredentials();
        JwtAccount jwtAccount = null;
        try{
            jwtAccount = JsonWebTokenUtil.parseJwt(jwt,JsonWebTokenUtil.SECRET_KEY);
        } catch(SignatureException | UnsupportedJwtException | MalformedJwtException | IllegalArgumentException e){
            throw new AuthenticationException("errJwt"); // 令牌错误
        } catch(ExpiredJwtException e){

            throw new AuthenticationException("expiredJwt"); // 令牌过期
        } catch(Exception e){
            throw new AuthenticationException("errJwt");
        }
        if(null == jwtAccount){
            throw new AuthenticationException("errJwt");
        }

        return true;
    }
}
