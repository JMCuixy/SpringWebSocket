package org.springframework.websocket.controller.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by XiuYin.Cui on 2018/5/2.
 */
@Controller
public class index {


    @RequestMapping(value = "/index", method = {RequestMethod.GET, RequestMethod.POST})
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/indexSockJS", method = {RequestMethod.GET, RequestMethod.POST})
    public String indexSockJS() {
        return "indexSockJS";
    }

    @RequestMapping(value = "/indexWs", method = {RequestMethod.GET, RequestMethod.POST})
    public String indexWs() {
        return "indexWs";
    }

    @RequestMapping(value = "/indexStomp", method = {RequestMethod.GET, RequestMethod.POST})
    public String indexStomp() {
        return "indexStomp";
    }

}
