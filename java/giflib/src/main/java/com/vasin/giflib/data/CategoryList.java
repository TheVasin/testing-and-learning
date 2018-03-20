package com.vasin.giflib.data;

import com.vasin.giflib.model.Category;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class CategoryList {
    private static final List<Category> ALL_CATEGORIES = Arrays.asList(
            new Category(1, "People"),
            new Category(2, "IT"),
            new Category(3, "Pets")
    );

    public List<Category> getAllCategories() {
        return ALL_CATEGORIES;
    }

    public Category findById(int id) {
        for (Category c : ALL_CATEGORIES) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }
}
