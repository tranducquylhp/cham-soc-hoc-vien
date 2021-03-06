package com.codegym.demo_chatbot_fb.controller;

import com.codegym.demo_chatbot_fb.model.ParamConfig;
import com.codegym.demo_chatbot_fb.service.ParamConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/params")
public class ParamConfigController {
    @Autowired
    private ParamConfigService paramService;

    @GetMapping()
    public ModelAndView paramList(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("param/list");
        List<ParamConfig> paramConfigList = paramService.findAll();
        modelAndView.addObject("params", paramConfigList);
        if (request.getParameter("message")!= null){
            modelAndView.addObject("message",request.getParameter("message"));
        }

        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView createForm(){
        ModelAndView modelAndView = new ModelAndView("param/create");
        modelAndView.addObject("paramConfig", new ParamConfig());

        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView create(@ModelAttribute(name = "paramConfig") ParamConfig paramConfig){
        String message = paramService.save(paramConfig);
        ModelAndView modelAndView;
        if (StringUtils.hasText(message)) {
            modelAndView = new ModelAndView("param/create");
            modelAndView.addObject("message", message);
            modelAndView.addObject("paramConfig", paramConfig);
        } else {
            modelAndView = new ModelAndView("redirect:/params");
            modelAndView.addObject("message", "Create done");
        }

        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editForm(@PathVariable Long id){
        ModelAndView modelAndView;
        Optional<ParamConfig> paramConfigOptional = paramService.findById(id);
        if (paramConfigOptional.isPresent()) {
            modelAndView = new ModelAndView("param/edit");
            modelAndView.addObject("paramConfig", paramConfigOptional.get());
        } else modelAndView = new ModelAndView("error");

        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView edit(@ModelAttribute ParamConfig paramConfig, @PathVariable Long id){
        ModelAndView modelAndView;
        String message = paramService.save(paramConfig);
        if (StringUtils.hasText(message)) {
            modelAndView = new ModelAndView("param/edit/" + id);
            modelAndView.addObject("message", message);
            modelAndView.addObject("paramConfig", paramConfig);
        } else {
            modelAndView = new ModelAndView("redirect:/params");
            modelAndView.addObject("message", "S???a th??ng tin th??nh c??ng");
        }

        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteForm(@PathVariable Long id){
        ModelAndView modelAndView;
        Optional<ParamConfig> paramConfigOptional = paramService.findById(id);
        if (paramConfigOptional.isPresent()) {
            modelAndView = new ModelAndView("param/delete");
            modelAndView.addObject("paramConfig", paramConfigOptional.get());
        } else modelAndView = new ModelAndView("error");

        return modelAndView;
    }

    @PostMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("redirect:/params");
        modelAndView.addObject("message", "Xo?? th??nh c??ng");
        paramService.delete(id);

        return modelAndView;
    }

    @GetMapping("/view/{id}")
    public ModelAndView view(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("param/view");
        Optional<ParamConfig> paramConfigOptional = paramService.findById(id);
        paramConfigOptional.ifPresent(paramConfig -> modelAndView.addObject("paramConfig", paramConfig));

        return modelAndView;
    }
}
