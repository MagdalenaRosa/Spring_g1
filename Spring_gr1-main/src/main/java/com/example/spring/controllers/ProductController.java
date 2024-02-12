package com.example.spring.controllers;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.spring.model.Product;
import com.example.spring.services.CategoryService;
import com.example.spring.services.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ProductController {

    final ProductService productService;
    final CategoryService categoryService;

    @GetMapping("/")
    public String showProducts(Model model) {
        model.addAttribute("title", "  Products");
        model.addAttribute("db", productService.getDatabase());
        model.addAttribute("actionUri", "/saveProduct");
        model.addAttribute("categories", categoryService.findAllCategoryProduct());
        return "product/products";
    }

    @PostMapping("/saveProduct")
    public String saveProduct(@Valid Product productForm, BindingResult bindingResult, RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) {
            var errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();
            attributes.addFlashAttribute("errors", errors);
            attributes.addFlashAttribute("product", productForm);
        } else {
            productService.insertProduct(productForm);
        }

        return "redirect:/"; // żeby wrócić do tego, co było w getmapingu
    }

    @GetMapping("/removeProduct/{productId}")
    public String removeProduct(@PathVariable Integer productId) {
        productService.removeProduct(productId);
        return "redirect:/";
    }

    @GetMapping("/productDetails/{productId}")
    public String productDetail(@PathVariable Integer productId, Model model) {

        if (!productService.findProduct(productId).isEmpty()) { // jest obecny (negacja-że jest pusty)
            model.addAttribute("product", productService.findProduct(productId).get());
        }
        return "/product/product";
    }

    @GetMapping("/editProduct/{productId}")
    // id = productId
    public String showEditProductForm(@PathVariable Integer productId, Model model) {
        model.addAttribute("categories", categoryService.findAllCategoryProduct());
        model.addAttribute("actionUri", "/editedProduct/" + productId);
        if (!productService.findProduct(productId).isEmpty()) {
            model.addAttribute("product", productService.findProduct(productId).get());
        }
        return "/product/edit-product";
    }

    @PostMapping("/editedProduct/{productId}")
    public String saveEditedProduct(@PathVariable Integer productId, @Valid Product productForm,
            BindingResult bindingResult, RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) {
            var errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();
            attributes.addFlashAttribute("errors", errors);
            return "redirect:/editProduct/" + productId;
        } else {

            productService.updateProduct(productForm, productId);
            return "redirect:/";
        }
    }
}
