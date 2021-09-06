package com.codejava.ProducManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AppController {
    @Autowired
    private ProductService productService;

    @RequestMapping("/")
    public String viewHomePage(Model model){
        List<Product> productList = productService.listAll();
        model.addAttribute("productList", productList);
        return "index";
    }

    @RequestMapping("/new")
    public String showProductForm(Model model){
        Product product = new Product();
        model.addAttribute("product", product);
        return "new_product";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute("product")Product product){
        productService.save(product);
        return "redirect:/";
    }

    @RequestMapping("/edit")
    public ModelAndView showEditProductForm(@PathVariable(name = "id")Long id){
        ModelAndView modelAndView = new ModelAndView("eidt_product");
        Product product = productService.get(id);
        modelAndView.addObject("product", product);
        return modelAndView;
    }

    @RequestMapping("/delete/id")
    public String deleteProduct(@PathVariable(name = "id")Long id ){
        productService.delete(id);
        return "redirect/";
    }
}
