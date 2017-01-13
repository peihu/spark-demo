package com.dmeo.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by phwang on 2017/1/13.
 */
@Controller
@RequestMapping(value = "/")
public class IndexController {

    @RequestMapping(value = "/ok", method = RequestMethod.GET)
    public String index(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.getOutputStream().print("xixihaha");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "ok";
    }
}
