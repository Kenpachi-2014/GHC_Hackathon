package com.example.expensetracker.controller;

import com.example.expensetracker.model.TransactionCategory;
import com.example.expensetracker.service.TransactionCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final TransactionCategoryService categoryService;

    public CategoryController(TransactionCategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * Get all categories
     * @return List of all transaction categories
     */
    @GetMapping
    public List<TransactionCategory> getAllCategories() {
        return categoryService.findAll();
    }
    
    /**
     * Get category by ID
     * @param id Category ID
     * @return Category if found, or 404 if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<TransactionCategory> getCategoryById(@PathVariable String id) {
        Optional<TransactionCategory> category = categoryService.findById(id);
        return category.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    /**
     * Create a new category
     * @param category Category data
     * @return Created category with generated ID
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionCategory createCategory(@RequestBody TransactionCategory category) {
        return categoryService.save(category);
    }
    
    /**
     * Update an existing category
     * @param id Category ID
     * @param category Updated category data
     * @return Updated category, or 404 if not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<TransactionCategory> updateCategory(
            @PathVariable String id, 
            @RequestBody TransactionCategory category) {
        
        if (!categoryService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        category.setCategoryId(id); // Ensure ID is set correctly
        TransactionCategory updatedCategory = categoryService.save(category);
        return ResponseEntity.ok(updatedCategory);
    }
    
    /**
     * Delete a category
     * @param id Category ID
     * @return 204 No Content if successful, 404 if not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable String id) {
        if (!categoryService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        categoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
    /**
     * Find categories by name
     * @param name Category name
     * @return List of matching categories
     */
    @GetMapping("/search/name/{name}")
    public List<TransactionCategory> getCategoriesByName(@PathVariable String name) {
        return categoryService.findByCategoryName(name);
    }
    
    /**
     * Find categories by type
     * @param type Category type
     * @return List of matching categories
     */
    @GetMapping("/search/type/{type}")
    public List<TransactionCategory> getCategoriesByType(@PathVariable String type) {
        return categoryService.findByCategoryType(type);
    }
}
