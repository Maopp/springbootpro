package com.catpp.springbootpro.jsp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * com.catpp.springbootpro.jsp
 *
 * @Author cat_pp
 * @Date 2018/10/8
 * @Description
 */
@Controller
@RequestMapping("/jsp")
public class JspIndexController {

    @RequestMapping("/index")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("name", "张三");
        modelAndView.setViewName("/index");
        return modelAndView;
    }
}
