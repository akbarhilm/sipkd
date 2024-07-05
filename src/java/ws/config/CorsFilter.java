/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ws.config;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 *
 * @author User
 */
public class CorsFilter extends HandlerInterceptorAdapter {

 public static final String CREDENTIALS_NAME = "Access-Control-Allow-Credentials";
 public static final String ORIGIN_NAME = "Access-Control-Allow-Origin";
 public static final String METHODS_NAME = "Access-Control-Allow-Methods";
 public static final String HEADERS_NAME = "Access-Control-Allow-Headers";
 public static final String MAX_AGE_NAME = "Access-Control-Max-Age";

 @Override
 public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
  response.setHeader(CREDENTIALS_NAME, "true");
  response.setHeader(ORIGIN_NAME, "*");
  response.setHeader(METHODS_NAME, "GET, OPTIONS, POST, PUT, DELETE");
  response.setHeader(HEADERS_NAME, "Origin, X-Requested-With, Content-Type, Accept");
  response.setHeader(MAX_AGE_NAME, "3600");
  return true;
 }

}