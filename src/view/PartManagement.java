package view;

import dao.CategoryDao;
import dao.CategoryDaoImpl;
import dao.PartDao;
import dao.PartDaoImpl;
import dao.SupplierDao;
import dao.SupplierDaoImpl;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.plaf.basic.BasicButtonUI; // <--- ADDED THIS IMPORT
import model.Category;
import model.Part;
import model.Supplier;
import util.UITheme;

public class PartManagement extends JFrame {

    private JPanel mainPanel, headerPanel, contentPanel, formPanel, buttonPanel, tablePanel;
    private JLabel titleLabel, subtitleLabel, searchLabel;

    // Form Inputs
    private JTextField txtCode, txtName, txtBrand, txtPrice, txtStock, txtSearch;
    private JComboBox<CategoryItem> categoryCombo;
    private JComboBox<SupplierItem> supplierCombo;

    // Buttons
    private JButton backButton, searchBtn, saveBtn, updateBtn, deleteBtn, displayBtn;

    private JScrollPane tableScrollPane;
    private JTable partTable;

    // DAO + Logger
    private final PartDao partDao = new PartDaoImpl();
    private static final java.util.logging.Logger logger =
            java.util.logging.Logger.getLogger(PartManagement.class.getName());

    public PartManagement() {
        initComponents();
        setLocationRelativeTo(null);
        loadCategoriesToCombo();
        loadSuppliersToCombo();
        loadPartsToTable();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Part Management - Car Spare Parts System");
        setResizable(true);
        setMinimumSize(new Dimension(1300, 750));

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(UITheme.BACKGROUND_LIGHT); 

        createHeader();
        createContent();

        getContentPane().add(mainPanel);
        pack();
    }

    private void createHeader() {
        headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(UITheme.PRIMARY);
        headerPanel.setPreferredSize(new Dimension(1300, 100));
        headerPanel.setBorder(new EmptyBorder(20, 40, 20, 40));

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setOpaque(false);

        titleLabel = new JLabel("Part Management");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(UITheme.TEXT_WHITE);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        subtitleLabel = new JLabel("Manage inventory items, pricing, and stock levels");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitleLabel.setForeground(new Color(255, 255, 255, 180));
        subtitleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        subtitleLabel.setBorder(new EmptyBorder(5, 0, 0, 0));

        leftPanel.add(titleLabel);
        leftPanel.add(subtitleLabel);

        // Header Button
        backButton = new JButton("← Back to Dashboard");
        // FIX: Remove system styling so colors show up
        backButton.setUI(new BasicButtonUI());
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(52, 73, 94)); // Darker Blue
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.setPreferredSize(new Dimension(180, 40));

        backButton.addActionListener(e -> {
            if (Login.getCurrentUser() != null && Login.getCurrentUser().isAdmin()) {
                new AdminDashboard().setVisible(true);
            } else {
                new HomePage().setVisible(true);
            }
            dispose();
        });

        // Add hover effect
        backButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) { backButton.setBackground(new Color(44, 62, 80)); }
            public void mouseExited(MouseEvent evt) { backButton.setBackground(new Color(52, 73, 94)); }
        });

        headerPanel.add(leftPanel, BorderLayout.WEST);
        headerPanel.add(backButton, BorderLayout.EAST);
        mainPanel.add(headerPanel, BorderLayout.NORTH);
    }

    private void createContent() {
        contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(UITheme.BACKGROUND_LIGHT);
        contentPanel.setBorder(new EmptyBorder(30, 40, 30, 40));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        // 1. Create Form (Left Side)
        createFormPanel();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.35; 
        gbc.weighty = 1.0;
        gbc.insets = new Insets(0, 0, 0, 20); 
        contentPanel.add(formPanel, gbc);

        // 2. Create Table (Right Side)
        createTablePanel();
        gbc.gridx = 1;
        gbc.weightx = 0.65; 
        gbc.insets = new Insets(0, 0, 0, 0);
        contentPanel.add(tablePanel, gbc);

        mainPanel.add(contentPanel, BorderLayout.CENTER);
    }

    private void createFormPanel() {
        formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(UITheme.SURFACE);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(230, 230, 230), 1, true),
            new EmptyBorder(30, 30, 30, 30)
        ));

        // Adding Fields
        addFormField("Part Code", txtCode = createTextField());
        addFormField("Part Name", txtName = createTextField());
        addFormField("Brand", txtBrand = createTextField());
        addFormField("Price ($)", txtPrice = createTextField());
        addFormField("Stock Quantity", txtStock = createTextField());
        addFormCombo("Category", categoryCombo = createCategoryCombo());
        addFormCombo("Supplier", supplierCombo = createSupplierCombo());

        // Search Section
        searchLabel = createLabel("Search by Code");
        searchLabel.setBorder(new EmptyBorder(20, 0, 0, 0));
        formPanel.add(searchLabel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 8)));

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));
        searchPanel.setOpaque(false);
        searchPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        searchPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45)); 

        txtSearch = createTextField();
        searchBtn = createButton("Search", new Color(23, 162, 184), new Color(19, 132, 150)); // Cyan
        searchBtn.addActionListener(e -> searchBtnActionPerformed());

        searchPanel.add(txtSearch);
        searchPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        searchPanel.add(searchBtn);
        formPanel.add(searchPanel);

        // CRUD Buttons
        createButtons();
        formPanel.add(Box.createVerticalGlue());
    }

    private void createButtons() {
        buttonPanel = new JPanel(new GridLayout(2, 2, 15, 15));
        buttonPanel.setOpaque(false);
        buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        buttonPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 110));
        buttonPanel.setBorder(new EmptyBorder(20, 0, 0, 0));

        // Using clear, hardcoded colors
        saveBtn = createButton("Save", new Color(40, 167, 69), new Color(33, 136, 56));     // Green
        saveBtn.addActionListener(e -> saveBtnActionPerformed());

        updateBtn = createButton("Update", new Color(255, 193, 7), new Color(224, 168, 0)); // Yellow/Orange
        updateBtn.setForeground(Color.BLACK); // Black text for yellow button
        updateBtn.addActionListener(e -> updateBtnActionPerformed());

        deleteBtn = createButton("Delete", new Color(220, 53, 69), new Color(200, 35, 51)); // Red
        deleteBtn.addActionListener(e -> deleteBtnActionPerformed());

        displayBtn = createButton("View All", UITheme.PRIMARY, UITheme.PRIMARY.darker());   // Blue
        displayBtn.addActionListener(e -> loadPartsToTable());

        buttonPanel.add(saveBtn);
        buttonPanel.add(updateBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(displayBtn);
        formPanel.add(buttonPanel);
    }

    private void createTablePanel() {
        tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(UITheme.SURFACE);
        tablePanel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(230, 230, 230), 1, true),
            new EmptyBorder(20, 20, 20, 20)
        ));

        JLabel tableTitle = new JLabel("Parts Inventory");
        tableTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        tableTitle.setForeground(UITheme.TEXT_PRIMARY);
        tableTitle.setBorder(new EmptyBorder(0, 0, 15, 0));

        partTable = new JTable();
        partTable.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        partTable.setRowHeight(40); 
        partTable.setGridColor(new Color(240, 240, 240));
        partTable.setSelectionBackground(UITheme.PRIMARY_LIGHT);
        partTable.setSelectionForeground(UITheme.TEXT_PRIMARY);
        partTable.setShowVerticalLines(false);
        partTable.setIntercellSpacing(new Dimension(0, 1));
        
        partTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        partTable.getTableHeader().setBackground(UITheme.BACKGROUND_DARK);
        partTable.getTableHeader().setForeground(UITheme.TEXT_PRIMARY);
        
        partTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                int row = partTable.getSelectedRow();
                if (row != -1) {
                    txtCode.setText(partTable.getValueAt(row, 0).toString());
                    txtName.setText(partTable.getValueAt(row, 1).toString());
                    txtBrand.setText(partTable.getValueAt(row, 2).toString());
                    txtPrice.setText(partTable.getValueAt(row, 3).toString());
                    txtStock.setText(partTable.getValueAt(row, 4).toString());
                    String catId = partTable.getValueAt(row, 5).toString();
                    String supId = partTable.getValueAt(row, 6).toString();
                    selectCategoryById(catId);
                    selectSupplierById(supId);
                    txtCode.setEditable(false); 
                }
            }
        });

        tableScrollPane = new JScrollPane(partTable);
        tableScrollPane.setBorder(new LineBorder(new Color(230, 230, 230), 1));

        tablePanel.add(tableTitle, BorderLayout.NORTH);
        tablePanel.add(tableScrollPane, BorderLayout.CENTER);
    }

    // --- HELPER METHODS ---

    private void addFormField(String labelText, JTextField field) {
        JLabel label = createLabel(labelText);
        if (formPanel.getComponentCount() > 0) {
            label.setBorder(new EmptyBorder(15, 0, 0, 0));
        }
        formPanel.add(label);
        formPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        formPanel.add(field);
    }

    private void addFormCombo(String labelText, JComponent combo) {
        JLabel label = createLabel(labelText);
        if (formPanel.getComponentCount() > 0) {
            label.setBorder(new EmptyBorder(15, 0, 0, 0));
        }
        formPanel.add(label);
        formPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        combo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        combo.setAlignmentX(Component.LEFT_ALIGNMENT);
        formPanel.add(combo);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label.setForeground(UITheme.TEXT_PRIMARY);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    private JTextField createTextField() {
        JTextField field = new JTextField();
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBackground(UITheme.SURFACE);
        field.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(200, 200, 200), 1, true),
            new EmptyBorder(10, 12, 10, 12)
        ));
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        field.setAlignmentX(Component.LEFT_ALIGNMENT);
        return field;
    }

    private JComboBox<CategoryItem> createCategoryCombo() {
        JComboBox<CategoryItem> combo = new JComboBox<>();
        combo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        combo.setBackground(UITheme.SURFACE);
        combo.setBorder(new LineBorder(new Color(200, 200, 200), 1, true));
        return combo;
    }

    private JComboBox<SupplierItem> createSupplierCombo() {
        JComboBox<SupplierItem> combo = new JComboBox<>();
        combo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        combo.setBackground(UITheme.SURFACE);
        combo.setBorder(new LineBorder(new Color(200, 200, 200), 1, true));
        return combo;
    }

    /**
     * MAJOR FIX HERE:
     * We use setUI(new BasicButtonUI()) to force the button to accept our colors
     * regardless of the Windows System Look and Feel.
     */
    private JButton createButton(String text, Color normalColor, Color hoverColor) {
        JButton btn = new JButton(text);
        // This line removes the default Windows/Mac styling that ignores colors
        btn.setUI(new BasicButtonUI()); 
        
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setForeground(Color.WHITE); 
        btn.setBackground(normalColor);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Add a slight padding since BasicButtonUI has no default padding
        btn.setBorder(new EmptyBorder(10, 20, 10, 20));

        // Hover Effect
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) { btn.setBackground(hoverColor); }
            public void mouseExited(MouseEvent evt) { btn.setBackground(normalColor); }
        });
        return btn;
    }

    // --- Logic ---

    private boolean validateFields() {
        if (txtCode.getText().trim().isEmpty() || txtName.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Part Code and Part Name are required!",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (txtPrice.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Price is required!",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (txtStock.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Stock quantity is required!",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            Double.parseDouble(txtPrice.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Price must be a valid number.",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            Double.parseDouble(txtStock.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Stock quantity must be a valid number.",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (categoryCombo.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this,
                    "Please select a Category.",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (supplierCombo.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this,
                    "Please select a Supplier.",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private Part buildPartFromForm() {
        Part part = new Part();
        part.setPartCode(txtCode.getText().trim());
        part.setPartName(txtName.getText().trim());
        part.setBrand(txtBrand.getText().trim());

        CategoryItem catItem = (CategoryItem) categoryCombo.getSelectedItem();
        SupplierItem supItem = (SupplierItem) supplierCombo.getSelectedItem();
        if (catItem != null) {
            part.setCategoryId(catItem.getId());
        }
        if (supItem != null) {
            part.setSupplierId(supItem.getId());
        }

        part.setPrice(Double.parseDouble(txtPrice.getText().trim()));
        part.setStockQuantity(Double.parseDouble(txtStock.getText().trim()));
        return part;
    }

    private void saveBtnActionPerformed() {
        if (!validateFields()) {
            return;
        }

        String code = txtCode.getText().trim();
        if (partDao.exists(code)) {
            JOptionPane.showMessageDialog(this,
                    "A part with this code already exists.",
                    "Duplicate Code",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        Part part = buildPartFromForm();
        int rows = partDao.create(part);
        if (rows > 0) {
            JOptionPane.showMessageDialog(this,
                    "Part saved successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            loadPartsToTable();
            clearFields();
        } else {
            // Most common reason: category_id or supplier_id do not exist
            JOptionPane.showMessageDialog(this,
                    "Could not save part.\n" +
                    "- Make sure the Category ID exists in Categories.\n" +
                    "- Make sure the Supplier ID exists in Suppliers.",
                    "Save Failed",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private void updateBtnActionPerformed() {
        if (txtCode.isEditable()) {
            JOptionPane.showMessageDialog(this,
                    "Please search or select a part first before updating.",
                    "No Part Selected",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!validateFields()) {
            return;
        }

        Part part = buildPartFromForm();
        int rows = partDao.update(part);
        if (rows > 0) {
            JOptionPane.showMessageDialog(this,
                    "Part updated successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            loadPartsToTable();
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Failed to update part. Please check the database connection.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteBtnActionPerformed() {
        if (txtCode.isEditable()) {
            JOptionPane.showMessageDialog(this,
                    "Please search or select a part first before deleting.",
                    "No Part Selected",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        String code = txtCode.getText().trim();
        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete part " + code + "?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        int rows = partDao.delete(code);
        if (rows > 0) {
            JOptionPane.showMessageDialog(this,
                    "Part deleted successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            loadPartsToTable();
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Failed to delete part. It may not exist or there is a database issue.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void searchBtnActionPerformed() {
        String code = txtSearch.getText().trim();
        if (code.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Enter a Part Code to search.",
                    "Missing Input",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        Part part = partDao.searchByCode(code);
        if (part == null) {
            JOptionPane.showMessageDialog(this,
                    "Part not found.",
                    "Not Found",
                    JOptionPane.INFORMATION_MESSAGE);
            clearFields();
        } else {
            txtCode.setText(part.getPartCode());
            txtName.setText(part.getPartName());
            txtBrand.setText(part.getBrand());
            selectCategoryById(part.getCategoryId());
            selectSupplierById(part.getSupplierId());
            txtPrice.setText(String.valueOf(part.getPrice()));
            txtStock.setText(String.valueOf(part.getStockQuantity()));
            txtCode.setEditable(false);
        }
    }

    private void clearFields() {
        txtCode.setText("");
        txtName.setText("");
        txtBrand.setText("");
        txtPrice.setText("");
        txtStock.setText("");
        txtSearch.setText("");
        txtCode.setEditable(true);
        if (categoryCombo.getItemCount() > 0) {
            categoryCombo.setSelectedIndex(0);
        }
        if (supplierCombo.getItemCount() > 0) {
            supplierCombo.setSelectedIndex(0);
        }
    }

    private void loadPartsToTable() {
        List<Part> parts = partDao.displayAllParts();
        SupplierDao supplierDao = new SupplierDaoImpl();

        String[] cols = {
                "Code", "Name", "Brand", "Price", "Stock",
                "Category ID", "Supplier ID", "Supplier Name"
        };
        Object[][] data = new Object[parts.size()][8];

        for (int i = 0; i < parts.size(); i++) {
            Part p = parts.get(i);
            data[i][0] = p.getPartCode();
            data[i][1] = p.getPartName();
            data[i][2] = p.getBrand();
            data[i][3] = p.getPrice();
            data[i][4] = p.getStockQuantity();
            data[i][5] = p.getCategoryId();
            data[i][6] = p.getSupplierId();

            Supplier supplier = supplierDao.searchByCode(p.getSupplierId());
            data[i][7] = (supplier != null) ? supplier.getSupplierNames() : "";
        }

        partTable.setModel(new DefaultTableModel(data, cols) {
            public boolean isCellEditable(int row, int column) { return false; }
        });
    }

    private void loadCategoriesToCombo() {
        CategoryDao dao = new CategoryDaoImpl();
        List<Category> list = dao.displayAllCategories();
        categoryCombo.removeAllItems();
        for (Category c : list) {
            categoryCombo.addItem(new CategoryItem(c.getCategoryId(), c.getCategoryName()));
        }
    }

    private void loadSuppliersToCombo() {
        SupplierDao dao = new SupplierDaoImpl();
        List<Supplier> list = dao.displayAllSuppliers();
        supplierCombo.removeAllItems();
        for (Supplier s : list) {
            supplierCombo.addItem(new SupplierItem(s.getSupplierId(), s.getSupplierNames()));
        }
    }

    private void selectCategoryById(String categoryId) {
        for (int i = 0; i < categoryCombo.getItemCount(); i++) {
            CategoryItem item = categoryCombo.getItemAt(i);
            if (item.getId().equals(categoryId)) {
                categoryCombo.setSelectedIndex(i);
                return;
            }
        }
    }

    private void selectSupplierById(String supplierId) {
        for (int i = 0; i < supplierCombo.getItemCount(); i++) {
            SupplierItem item = supplierCombo.getItemAt(i);
            if (item.getId().equals(supplierId)) {
                supplierCombo.setSelectedIndex(i);
                return;
            }
        }
    }

    // Wrapper items so combo shows names but we still keep the IDs
    private static class CategoryItem {
        private final String id;
        private final String name;

        CategoryItem(String id, String name) {
            this.id = id;
            this.name = name;
        }

        String getId() {
            return id;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    private static class SupplierItem {
        private final String id;
        private final String name;

        SupplierItem(String id, String name) {
            this.id = id;
            this.name = name;
        }

        String getId() {
            return id;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {}
        
        EventQueue.invokeLater(() -> new PartManagement().setVisible(true));
    }
}