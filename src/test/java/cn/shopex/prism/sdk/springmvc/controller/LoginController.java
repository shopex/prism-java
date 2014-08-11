package cn.shopex.prism.sdk.springmvc.controller;

import cn.shopex.prism.sdk.PrismClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/user")
public class LoginController {
  String url = "http://dilbmtcv.apihub.cn/api";
  String key = "buwb2lii";
  String secret = "ucr72ygfutspqeuu6s36";

  PrismClient prismClient = new PrismClient(url,key,secret);

  //testing oauth
  @RequestMapping("/login")
  public ModelAndView login(HttpServletRequest request,HttpServletResponse response) {
    ModelAndView view = new ModelAndView("/index");
    prismClient.requireOauth(request,response);
    return view;
  }
}
