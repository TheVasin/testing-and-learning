package com.vasin.giflib.controller;

import com.vasin.giflib.data.CategoryList;
import com.vasin.giflib.data.GifRepository;
import com.vasin.giflib.model.Category;
import com.vasin.giflib.model.Gif;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    private CategoryList categoryList;

    @Autowired
    private GifRepository gifRepository;

    @RequestMapping("/categories")
    public String categories(ModelMap modelMap) {
        List<Category> allCategories = categoryList.getAllCategories();
        modelMap.put("categories", allCategories);
        return "categories";
    }

    @RequestMapping("/category/{id}")
    public String categoryDetail(@PathVariable int id, ModelMap modelMap) {
        Category c = categoryList.findById(id);
        modelMap.put("category", c);
        List<Gif> gifs = gifRepository.findByCategoryId(id);
        modelMap.put("gifs", gifs);
        return "category";
    }
}
