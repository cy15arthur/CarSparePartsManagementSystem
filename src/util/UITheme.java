package util;

import java.awt.Color;
import java.awt.Font;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

/**
 * UI Theme utility class for consistent styling across the application.
 * Provides modern, professional color schemes and styling constants.
 * 
 * @author cyita
 */
public final class UITheme {
    
    // Private constructor to prevent instantiation
    private UITheme() {
        throw new AssertionError("Cannot instantiate utility class");
    }
    
    // ========== COLOR PALETTE ==========
    
    // Primary Colors - Modern Blue Theme
    public static final Color PRIMARY_DARK = new Color(30, 58, 138);      // Deep Blue
    public static final Color PRIMARY = new Color(59, 130, 246);          // Blue
    public static final Color PRIMARY_LIGHT = new Color(147, 197, 253);  // Light Blue
    
    // Secondary Colors
    public static final Color SECONDARY = new Color(16, 185, 129);        // Teal/Green
    public static final Color SECONDARY_LIGHT = new Color(110, 231, 183); // Light Teal
    
    // Accent Colors
    public static final Color ACCENT_ORANGE = new Color(249, 115, 22);   // Orange
    public static final Color ACCENT_PURPLE = new Color(139, 92, 246);    // Purple
    public static final Color ACCENT_PINK = new Color(236, 72, 153);     // Pink
    
    // Neutral Colors
    public static final Color BACKGROUND_LIGHT = new Color(249, 250, 251); // Light Gray
    public static final Color BACKGROUND = new Color(243, 244, 246);      // Gray
    public static final Color BACKGROUND_DARK = new Color(229, 231, 235); // Dark Gray
    public static final Color SURFACE = new Color(255, 255, 255);          // White
    public static final Color SURFACE_ELEVATED = new Color(255, 255, 255); // White
    
    // Text Colors
    public static final Color TEXT_PRIMARY = new Color(17, 24, 39);       // Dark Gray/Black
    public static final Color TEXT_SECONDARY = new Color(107, 114, 128);   // Medium Gray
    public static final Color TEXT_LIGHT = new Color(156, 163, 175);      // Light Gray
    public static final Color TEXT_WHITE = new Color(255, 255, 255);      // White
    
    // Status Colors
    public static final Color SUCCESS = new Color(34, 197, 94);          // Green
    public static final Color WARNING = new Color(234, 179, 8);           // Yellow
    public static final Color ERROR = new Color(239, 68, 68);             // Red
    public static final Color INFO = new Color(59, 130, 246);            // Blue
    
    // Button Colors
    public static final Color BUTTON_PRIMARY = PRIMARY;
    public static final Color BUTTON_PRIMARY_HOVER = new Color(37, 99, 235);
    public static final Color BUTTON_SECONDARY = SECONDARY;
    public static final Color BUTTON_SECONDARY_HOVER = new Color(5, 150, 105);
    public static final Color BUTTON_DANGER = ERROR;
    public static final Color BUTTON_DANGER_HOVER = new Color(220, 38, 38);
    public static final Color BUTTON_WARNING = WARNING;
    public static final Color BUTTON_INFO = INFO;
    
    // Border Colors
    public static final Color BORDER_LIGHT = new Color(229, 231, 235);
    public static final Color BORDER = new Color(209, 213, 219);
    public static final Color BORDER_DARK = new Color(156, 163, 175);
    
    // ========== TYPOGRAPHY ==========
    
    public static final Font FONT_TITLE = new Font("Segoe UI", Font.BOLD, 32);
    public static final Font FONT_HEADING = new Font("Segoe UI", Font.BOLD, 24);
    public static final Font FONT_SUBHEADING = new Font("Segoe UI", Font.BOLD, 18);
    public static final Font FONT_BODY = new Font("Segoe UI", Font.PLAIN, 14);
    public static final Font FONT_BODY_BOLD = new Font("Segoe UI", Font.BOLD, 14);
    public static final Font FONT_SMALL = new Font("Segoe UI", Font.PLAIN, 12);
    public static final Font FONT_BUTTON = new Font("Segoe UI", Font.BOLD, 14);
    
    // ========== BORDERS ==========
    
    /**
     * Creates a modern rounded border.
     */
    public static Border createRoundedBorder(Color color, int thickness) {
        return new LineBorder(color, thickness, true);
    }
    
    /**
     * Creates a modern input field border.
     */
    public static Border createInputBorder() {
        return new CompoundBorder(
            new LineBorder(BORDER, 1),
            new EmptyBorder(8, 12, 8, 12)
        );
    }
    
    /**
     * Creates a modern card border with shadow effect.
     */
    public static Border createCardBorder() {
        return new CompoundBorder(
            new LineBorder(BORDER_LIGHT, 1),
            new EmptyBorder(16, 16, 16, 16)
        );
    }
    
    /**
     * Creates a button border.
     */
    public static Border createButtonBorder(Color color) {
        return new LineBorder(color, 1, true);
    }
    
    // ========== SPACING ==========
    
    public static final int SPACING_XS = 4;
    public static final int SPACING_SM = 8;
    public static final int SPACING_MD = 16;
    public static final int SPACING_LG = 24;
    public static final int SPACING_XL = 32;
    
    // ========== DIMENSIONS ==========
    
    public static final int BUTTON_HEIGHT = 40;
    public static final int INPUT_HEIGHT = 38;
    public static final int CARD_RADIUS = 8;
    public static final int BORDER_RADIUS = 6;
}

