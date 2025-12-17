package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Category;
import util.DatabaseConnectionManager;

/**
 * Implementation of CategoryDao interface for managing category data access operations.
 * 
 * @author cyita
 */
public class CategoryDaoImpl implements CategoryDao {
    
    private static final Logger LOGGER = Logger.getLogger(CategoryDaoImpl.class.getName());
    
    @Override
    public int create(Category categoryObj) {
        if (categoryObj == null) {
            LOGGER.log(Level.WARNING, "Attempted to create null category object");
            return 0;
        }
        
        String sql = "INSERT INTO categories(category_id, category_name) VALUES (?,?)";
        
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            
            preparedStatement.setString(1, categoryObj.getCategoryId());
            preparedStatement.setString(2, categoryObj.getCategoryName());
            
            int rowsAffected = preparedStatement.executeUpdate();
            LOGGER.log(Level.INFO, "Category created successfully: {0}", categoryObj.getCategoryId());
            return rowsAffected;
            
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error creating category: " + categoryObj.getCategoryId(), e);
            return 0;
        }
    }

    @Override
    public int update(Category categoryObj) {
        if (categoryObj == null) {
            LOGGER.log(Level.WARNING, "Attempted to update null category object");
            return 0;
        }
        
        String sql = "UPDATE categories SET category_name=? WHERE category_id=?";
        
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            
            preparedStatement.setString(1, categoryObj.getCategoryName());
            preparedStatement.setString(2, categoryObj.getCategoryId());
            
            int rowsAffected = preparedStatement.executeUpdate();
            LOGGER.log(Level.INFO, "Category updated successfully: {0}", categoryObj.getCategoryId());
            return rowsAffected;
            
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating category: " + categoryObj.getCategoryId(), e);
            return 0;
        }
    }
    

    @Override
    public int delete(String categoryId) {
        if (categoryId == null || categoryId.trim().isEmpty()) {
            LOGGER.log(Level.WARNING, "Attempted to delete category with null or empty ID");
            return 0;
        }
        
        String sql = "DELETE FROM categories WHERE category_id=?";
        
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            
            preparedStatement.setString(1, categoryId);
            int rowsAffected = preparedStatement.executeUpdate();
            LOGGER.log(Level.INFO, "Category deleted successfully: {0}", categoryId);
            return rowsAffected;
            
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting category: " + categoryId, e);
            return 0;
        }
    }

    @Override
    public Category searchById(String categoryId) {
        if (categoryId == null || categoryId.trim().isEmpty()) {
            LOGGER.log(Level.WARNING, "Attempted to search category with null or empty ID");
            return null;
        }
        
        String sql = "SELECT * FROM categories WHERE category_id=?";
        
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            
            preparedStatement.setString(1, categoryId);
            
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Category category = new Category();
                    category.setCategoryId(resultSet.getString("category_id"));
                    category.setCategoryName(resultSet.getString("category_name"));
                    LOGGER.log(Level.FINE, "Category found: {0}", categoryId);
                    return category;
                }
            }
            
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error searching category: " + categoryId, e);
        }
        
        return null;
    }
    @Override
    public List<Category> displayAllCategories() {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM categories";
        
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            
            while (resultSet.next()) {
                Category category = new Category();
                category.setCategoryId(resultSet.getString("category_id"));
                category.setCategoryName(resultSet.getString("category_name"));
                categories.add(category);
            }
            
            LOGGER.log(Level.FINE, "Retrieved {0} categories", categories.size());
            
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving all categories", e);
        }
        
        return categories;
    }
}
