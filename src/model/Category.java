package model;

import java.util.Objects;

/**
 * Represents a Category entity in the Car Spare Parts Management System.
 * 
 * @author cyita
 */
public class Category {
    
    private String categoryId;
    private String categoryName;

    /**
     * Default constructor.
     */
    public Category() {
    }

    /**
     * Constructor with parameters.
     * 
     * @param categoryId the unique identifier for the category
     * @param categoryName the name of the category
     */
    public Category(String categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    /**
     * Gets the category ID.
     * 
     * @return the category ID
     */
    public String getCategoryId() {
        return categoryId;
    }

    /**
     * Sets the category ID.
     * 
     * @param categoryId the category ID to set
     */
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * Gets the category name.
     * 
     * @return the category name
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * Sets the category name.
     * 
     * @param categoryName the category name to set
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    
    @Override
    public String toString() {
        return "Category{"
                + "categoryId='" + categoryId + '\''
                + ", categoryName='" + categoryName + '\''
                + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Category category = (Category) obj;
        return Objects.equals(categoryId, category.categoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryId);
    }
}
