package com.excelib.controller;

import main.spring.textrecognition.service.RecognizeService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Controller
public class GoController {
    private final Log logger = LogFactory.getLog(GoController.class); //处理HEAD类型的”/”请求

    @Autowired
    public RecognizeService service;

    @RequestMapping(value = { "/" }, method = { RequestMethod.HEAD })
    public String head() {
        return "go.jsp";
    }

    //处理GET类型的"/index"和”/”请求 
    @RequestMapping(value = { "/index", "/" }, method = { RequestMethod.GET })
    public String index(Model model) throws Exception {
        logger.info("======processed by index=======");
        //返回msg参数 
        model.addAttribute("msg", "Go Go Go!");
        return "go.jsp";
    }

    @RequestMapping(value = "/recognition", method = RequestMethod.POST)
    @ResponseBody
    public String recognition(@RequestParam MultipartFile pics) {
        logger.info("上传文件：" + pics.getOriginalFilename());
        String recognize = service.recognize(pics);
        logger.info("结果：" + recognize);
        return recognize;
    }

}