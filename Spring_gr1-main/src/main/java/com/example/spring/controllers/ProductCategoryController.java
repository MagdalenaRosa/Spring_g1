package com.example.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.spring.model.ProductCategory;
import com.example.spring.services.CategoryService;

@Controller
public class ProductCategoryController {
    CategoryService categoryService;

    ProductCategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public String showProductCategory(Model model) {
        model.addAttribute("title", "Category List");
        model.addAttribute("categoryDB", categoryService.getDatabase());
        model.addAttribute("actionUri", "/saveCategory");

        return "categories/categories";
    }

    @PostMapping("/saveCategory")
    public String saveProductCategory(ProductCategory productCategoryForm) {
        categoryService.insertCategory(productCategoryForm);
        return "redirect:/categories";
    }

    @GetMapping("/editCategory/{id}")
    public String showEditCategoryForm(@PathVariable("id") Integer categoryId, Model model) {
        model.addAttribute("actionUri", "/editedCategory/" + categoryId);
        bindCategory(categoryId, model);
        model.addAttribute("title", "category");

        return "categories/edit-category";
    }

    @PostMapping("/editedCategory/{id}")
    public String saveEditedCategory(@PathVariable("id") Integer categoryId, ProductCategory categoryForm) {
        categoryService.updateCategory(categoryId, categoryForm);
        return "redirect:/categories";
    }

    @GetMapping("/removeCategory/{id}")
    public String removeProductCategory(@PathVariable("id") Integer categoryID) {
        categoryService.removeCategory(categoryID);
        return "redirect:/categories";
    }
    // /categoryDetail/123

    @GetMapping("/categoryDetail/{id}")
    public String showCategoryDetail(@PathVariable("id") Integer categoryID, Model model) {

        bindCategory(categoryID, model);
        model.addAttribute("title", "Category detail");

        return "categories/category";

    }

    private void bindCategory(Integer categoryId, Model model) {

        if (categoryService.findCategory(categoryId).isPresent()) {
            var category = categoryService.findCategory(categoryId).get();
            model.addAttribute("category", category);

        }
    }
}