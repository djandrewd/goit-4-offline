package ua.goit.offline4.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.offline4.dao.ComponentsService;

@Controller
@RequestMapping("/")
public class ComponentsController {
    private ComponentsService componentsService;

    @Autowired
    public ComponentsController(ComponentsService componentsService) {
        this.componentsService = componentsService;
    }

    @GetMapping
    public ModelAndView getAllComponents() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("cps", componentsService.getAll());
        mav.setViewName("components");
        return mav;
    }
}
