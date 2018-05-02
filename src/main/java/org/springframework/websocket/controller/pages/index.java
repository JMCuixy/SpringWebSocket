package org.springframework.websocket.controller.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by XiuYin.Cui on 2018/5/2.
 */
@Controller
public class index {


    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
    public String index() {
        return "index";
    }

}
