package view;

import dao.SupplierDao;
import dao.SupplierDaoImpl;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import model.Supplier;
import util.UITheme;

/**
 * Professional Supplier Management interface
 * Compatible with NetBeans 8.1
 * 
 * @author cyita
 */
public class SupplierManagement extends JFrame {
    
    private final SupplierDao supplierDao = new SupplierDaoImpl();
    private static final java.util.logging.Logger logger = 
        java.util.logging.Logger.getLogger(SupplierManagement.class.getName());

    public SupplierManagement() {
        initComponents();
        setLocationRelativeTo(null);
        loadSuppliersToTable();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        mainPanel = new JPanel();
        headerPanel = new JPanel();
        titleLabel = new JLabel();
        subtitleLabel = new JLabel();
        backButton = new JButton();
        contentPanel = new JPanel();
        formPanel = new JPanel();
        supplierIdLabel = new JLabel();
        supplierIdTxt = new JTextField();
        supplierNameLabel = new JLabel();
        supplierNameTxt = new JTextField();
        contactLabel = new JLabel();
        contactTxt = new JTextField();
        emailLabel = new JLabel();
        emailTxt = new JTextField();
        addressLabel = new JLabel();
        addressTxt = new JTextField();
        searchLabel = new JLabel();
        searchTxt = new JTextField();
        searchBtn = new JButton();
        buttonPanel = new JPanel();
        saveBtn = new JButton();
        updateBtn = new JButton();
        deleteBtn = new JButton();
        displayBtn = new JButton();
        tablePanel = new JPanel();
        tableScrollPane = new JScrollPane();
        supplierTable = new JTable();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Supplier Management - Car Spare Parts System");
        setResizable(true);
        setMinimumSize(new Dimension(1300, 750));

        // Main Panel
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(UITheme.BACKGROUND_LIGHT);

        // ===== Header Panel =====
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBackground(UITheme.PRIMARY);
        headerPanel.setPreferredSize(new Dimension(1300, 100));
        headerPanel.setBorder(new EmptyBorder(20, 40, 20, 40));

        // Left side - Title
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setOpaque(false);

        titleLabel.setText("Supplier Management");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(UITheme.TEXT_WHITE);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        subtitleLabel.setText("Manage supplier information and contacts");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitleLabel.setForeground(new Color(255, 255, 255, 180));
        subtitleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        subtitleLabel.setBorder(new EmptyBorder(5, 0, 0, 0));

        leftPanel.add(titleLabel);
        leftPanel.add(subtitleLabel);

        // Right side - Back button
        backButton.setText("← Back to Dashboard");
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        backButton.setForeground(UITheme.TEXT_WHITE);
        backButton.setBackground(new Color(52, 73, 94));
        backButton.setBorder(new EmptyBorder(12, 30, 12, 30));
        backButton.setFocusPainted(false);
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });
        backButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                backButton.setBackground(new Color(44, 62, 80));
            }
            public void mouseExited(MouseEvent evt) {
                backButton.setBackground(new Color(52, 73, 94));
            }
        });

        headerPanel.add(leftPanel, BorderLayout.WEST);
        headerPanel.add(backButton, BorderLayout.EAST);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // ===== Content Panel =====
        contentPanel.setLayout(new GridBagLayout());
        contentPanel.setBackground(UITheme.BACKGROUND_LIGHT);
        contentPanel.setBorder(new EmptyBorder(30, 40, 30, 40));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 0, 0, 20);

        // ===== Form Panel =====
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(UITheme.SURFACE);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(230, 230, 230), 1, true),
            new EmptyBorder(30, 30, 30, 30)
        ));
        formPanel.setPreferredSize(new Dimension(450, 600));

        // Supplier ID
        supplierIdLabel.setText("Supplier ID");
        supplierIdLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        supplierIdLabel.setForeground(UITheme.TEXT_PRIMARY);
        supplierIdLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        supplierIdTxt.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        supplierIdTxt.setBackground(UITheme.SURFACE);
        supplierIdTxt.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(200, 200, 200), 1, true),
            new EmptyBorder(10, 12, 10, 12)
        ));
        supplierIdTxt.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        supplierIdTxt.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Supplier Name
        supplierNameLabel.setText("Supplier Name");
        supplierNameLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        supplierNameLabel.setForeground(UITheme.TEXT_PRIMARY);
        supplierNameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        supplierNameLabel.setBorder(new EmptyBorder(15, 0, 0, 0));

        supplierNameTxt.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        supplierNameTxt.setBackground(UITheme.SURFACE);
        supplierNameTxt.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(200, 200, 200), 1, true),
            new EmptyBorder(10, 12, 10, 12)
        ));
        supplierNameTxt.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        supplierNameTxt.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Contact Information
        contactLabel.setText("Contact Number");
        contactLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        contactLabel.setForeground(UITheme.TEXT_PRIMARY);
        contactLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        contactLabel.setBorder(new EmptyBorder(15, 0, 0, 0));

        contactTxt.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        contactTxt.setBackground(UITheme.SURFACE);
        contactTxt.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(200, 200, 200), 1, true),
            new EmptyBorder(10, 12, 10, 12)
        ));
        contactTxt.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        contactTxt.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Email
        emailLabel.setText("Email Address");
        emailLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        emailLabel.setForeground(UITheme.TEXT_PRIMARY);
        emailLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        emailLabel.setBorder(new EmptyBorder(15, 0, 0, 0));

        emailTxt.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        emailTxt.setBackground(UITheme.SURFACE);
        emailTxt.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(200, 200, 200), 1, true),
            new EmptyBorder(10, 12, 10, 12)
        ));
        emailTxt.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        emailTxt.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Address
        addressLabel.setText("Address");
        addressLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        addressLabel.setForeground(UITheme.TEXT_PRIMARY);
        addressLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        addressLabel.setBorder(new EmptyBorder(15, 0, 0, 0));

        addressTxt.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        addressTxt.setBackground(UITheme.SURFACE);
        addressTxt.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(200, 200, 200), 1, true),
            new EmptyBorder(10, 12, 10, 12)
        ));
        addressTxt.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        addressTxt.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Search Section
        searchLabel.setText("Search by ID");
        searchLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        searchLabel.setForeground(UITheme.TEXT_PRIMARY);
        searchLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        searchLabel.setBorder(new EmptyBorder(20, 0, 0, 0));

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));
        searchPanel.setOpaque(false);
        searchPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        searchPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));

        searchTxt.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        searchTxt.setBackground(UITheme.SURFACE);
        searchTxt.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(200, 200, 200), 1, true),
            new EmptyBorder(10, 12, 10, 12)
        ));

        searchBtn.setText("Search");
        searchBtn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        searchBtn.setForeground(UITheme.TEXT_WHITE);
        searchBtn.setBackground(UITheme.INFO);
        searchBtn.setBorder(new EmptyBorder(10, 20, 10, 20));
        searchBtn.setFocusPainted(false);
        searchBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        searchBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });
        setupButtonHover(searchBtn, UITheme.INFO, new Color(23, 162, 184));

        searchPanel.add(searchTxt);
        searchPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        searchPanel.add(searchBtn);

        // Button Panel
        buttonPanel.setLayout(new GridLayout(2, 2, 15, 15));
        buttonPanel.setOpaque(false);
        buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        buttonPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 110));
        buttonPanel.setBorder(new EmptyBorder(20, 0, 0, 0));

        saveBtn.setText("Save");
        saveBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        saveBtn.setForeground(UITheme.TEXT_WHITE);
        saveBtn.setBackground(UITheme.BUTTON_SECONDARY);
        saveBtn.setBorder(new EmptyBorder(12, 20, 12, 20));
        saveBtn.setFocusPainted(false);
        saveBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        saveBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });
        setupButtonHover(saveBtn, UITheme.BUTTON_SECONDARY, new Color(23, 162, 184));

        updateBtn.setText("Update");
        updateBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        updateBtn.setForeground(UITheme.TEXT_WHITE);
        updateBtn.setBackground(UITheme.BUTTON_WARNING);
        updateBtn.setBorder(new EmptyBorder(12, 20, 12, 20));
        updateBtn.setFocusPainted(false);
        updateBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        updateBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                updateBtnActionPerformed(evt);
            }
        });
        setupButtonHover(updateBtn, UITheme.BUTTON_WARNING, new Color(227, 151, 0));

        deleteBtn.setText("Delete");
        deleteBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        deleteBtn.setForeground(UITheme.TEXT_WHITE);
        deleteBtn.setBackground(UITheme.BUTTON_DANGER);
        deleteBtn.setBorder(new EmptyBorder(12, 20, 12, 20));
        deleteBtn.setFocusPainted(false);
        deleteBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        deleteBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });
        setupButtonHover(deleteBtn, UITheme.BUTTON_DANGER, new Color(200, 35, 51));

        displayBtn.setText("View All");
        displayBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        displayBtn.setForeground(UITheme.TEXT_WHITE);
        displayBtn.setBackground(UITheme.BUTTON_PRIMARY);
        displayBtn.setBorder(new EmptyBorder(12, 20, 12, 20));
        displayBtn.setFocusPainted(false);
        displayBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        displayBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                displayBtnActionPerformed(evt);
            }
        });
        setupButtonHover(displayBtn, UITheme.BUTTON_PRIMARY, UITheme.PRIMARY);

        buttonPanel.add(saveBtn);
        buttonPanel.add(updateBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(displayBtn);

        // Add components to form panel
        formPanel.add(supplierIdLabel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        formPanel.add(supplierIdTxt);
        formPanel.add(supplierNameLabel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        formPanel.add(supplierNameTxt);
        formPanel.add(contactLabel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        formPanel.add(contactTxt);
        formPanel.add(emailLabel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        formPanel.add(emailTxt);
        formPanel.add(addressLabel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        formPanel.add(addressTxt);
        formPanel.add(searchLabel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        formPanel.add(searchPanel);
        formPanel.add(buttonPanel);
        formPanel.add(Box.createVerticalGlue());

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.35;
        gbc.weighty = 1.0;
        contentPanel.add(formPanel, gbc);

        // ===== Table Panel =====
        tablePanel.setLayout(new BorderLayout());
        tablePanel.setBackground(UITheme.SURFACE);
        tablePanel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(230, 230, 230), 1, true),
            new EmptyBorder(20, 20, 20, 20)
        ));

        JLabel tableTitle = new JLabel("Suppliers List");
        tableTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        tableTitle.setForeground(UITheme.TEXT_PRIMARY);
        tableTitle.setBorder(new EmptyBorder(0, 0, 15, 0));

        JTable dataTable = new JTable();
        dataTable.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        dataTable.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {"Supplier ID", "Name", "Contact", "Email", "Address"}
        ));
        dataTable.setRowHeight(40);
        dataTable.setGridColor(new Color(240, 240, 240));
        dataTable.setSelectionBackground(UITheme.PRIMARY_LIGHT);
        dataTable.setSelectionForeground(UITheme.TEXT_PRIMARY);
        dataTable.setShowVerticalLines(false);
        dataTable.setIntercellSpacing(new Dimension(0, 1));
        dataTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        dataTable.getTableHeader().setBackground(UITheme.BACKGROUND_DARK);
        dataTable.getTableHeader().setForeground(UITheme.TEXT_PRIMARY);
        dataTable.getTableHeader().setBorder(new LineBorder(new Color(230, 230, 230)));
        dataTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                supplierTableMouseClicked(evt);
            }
        });
        
        supplierTable = dataTable;

        tableScrollPane.setViewportView(dataTable);
        tableScrollPane.setBorder(new LineBorder(new Color(230, 230, 230), 1));

        tablePanel.add(tableTitle, BorderLayout.NORTH);
        tablePanel.add(tableScrollPane, BorderLayout.CENTER);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.65;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(0, 0, 0, 0);
        contentPanel.add(tablePanel, gbc);

        mainPanel.add(contentPanel, BorderLayout.CENTER);

        // Layout
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, 1300, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
        );

        pack();
    }

    private void setupButtonHover(JButton button, Color normalColor, Color hoverColor) {
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                button.setBackground(hoverColor);
            }
            public void mouseExited(MouseEvent evt) {
                button.setBackground(normalColor);
            }
        });
    }

    // Event Handlers
    private void backButtonActionPerformed(ActionEvent evt) {
        new AdminDashboard().setVisible(true);
        this.dispose();
    }

    private void saveBtnActionPerformed(ActionEvent evt) {
        if (supplierIdTxt.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Supplier ID is required!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (supplierNameTxt.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Supplier name is required!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!supplierNameTxt.getText().trim().matches("^[A-Za-z ]+$")) {
            JOptionPane.showMessageDialog(this, "Name must contain only letters and spaces!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (contactTxt.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Contact number is required!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!contactTxt.getText().matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Contact must contain only numbers!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (emailTxt.getText().trim().isEmpty() || !emailTxt.getText().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            JOptionPane.showMessageDialog(this, "Valid email is required!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (addressTxt.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Address is required!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Supplier s = new Supplier();
        s.setSupplierId(supplierIdTxt.getText().trim());
        s.setSupplierNames(supplierNameTxt.getText().trim());
        s.setSuppliercontact(contactTxt.getText().trim());
        s.setSupplierEmail(emailTxt.getText().trim());
        s.setAddress(addressTxt.getText().trim());

        int result = supplierDao.create(s);
        if (result > 0) {
            JOptionPane.showMessageDialog(this, "Supplier saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
            loadSuppliersToTable();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to save supplier!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateBtnActionPerformed(ActionEvent evt) {
        if (supplierIdTxt.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Search for a supplier first!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (supplierIdTxt.isEditable()) {
            JOptionPane.showMessageDialog(this, "Search a supplier first (ID must be locked).", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (supplierNameTxt.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Supplier name is required!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Supplier s = new Supplier();
        s.setSupplierId(supplierIdTxt.getText().trim());
        s.setSupplierNames(supplierNameTxt.getText().trim());
        s.setSuppliercontact(contactTxt.getText().trim());
        s.setSupplierEmail(emailTxt.getText().trim());
        s.setAddress(addressTxt.getText().trim());

        int result = supplierDao.update(s);
        if (result > 0) {
            JOptionPane.showMessageDialog(this, "Supplier updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            supplierIdTxt.setEditable(true);
            clearFields();
            loadSuppliersToTable();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to update supplier!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteBtnActionPerformed(ActionEvent evt) {
        if (supplierIdTxt.getText().trim().isEmpty() || supplierIdTxt.isEditable()) {
            JOptionPane.showMessageDialog(this, "Search for a supplier first!", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete supplier: " + supplierIdTxt.getText() + "?",
                "Confirm Delete", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (confirm != JOptionPane.YES_OPTION) return;

        int result = supplierDao.delete(supplierIdTxt.getText().trim());
        if (result > 0) {
            JOptionPane.showMessageDialog(this, "Supplier deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            supplierIdTxt.setEditable(true);
            clearFields();
            loadSuppliersToTable();
        } else {
            JOptionPane.showMessageDialog(this, "Could not delete supplier!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayBtnActionPerformed(ActionEvent evt) {
        loadSuppliersToTable();
    }

    private void searchBtnActionPerformed(ActionEvent evt) {
        String id = searchTxt.getText().trim();

        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter Supplier ID to search!", "Missing Input", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Supplier found = supplierDao.searchByCode(id);

        if (found == null) {
            JOptionPane.showMessageDialog(this, "Supplier not found!", "Not Found", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
            supplierIdTxt.setEditable(true);
            return;
        }

        supplierIdTxt.setText(found.getSupplierId());
        supplierNameTxt.setText(found.getSupplierNames());
        contactTxt.setText(found.getSuppliercontact());
        emailTxt.setText(found.getSupplierEmail());
        addressTxt.setText(found.getAddress());
        supplierIdTxt.setEditable(false);
        searchTxt.setText("");
    }

    private void supplierTableMouseClicked(MouseEvent evt) {
        int selectedRow = supplierTable.getSelectedRow();
        if (selectedRow != -1) {
            supplierIdTxt.setText(supplierTable.getValueAt(selectedRow, 0).toString());
            supplierNameTxt.setText(supplierTable.getValueAt(selectedRow, 1).toString());
            contactTxt.setText(supplierTable.getValueAt(selectedRow, 2).toString());
            emailTxt.setText(supplierTable.getValueAt(selectedRow, 3).toString());
            addressTxt.setText(supplierTable.getValueAt(selectedRow, 4).toString());
            supplierIdTxt.setEditable(false);
        }
    }

    // Helper Methods
    private void clearFields() {
        supplierIdTxt.setText("");
        supplierNameTxt.setText("");
        contactTxt.setText("");
        emailTxt.setText("");
        addressTxt.setText("");
        searchTxt.setText("");
        supplierIdTxt.setEditable(true);
    }

    private void loadSuppliersToTable() {
        List<Supplier> list = supplierDao.displayAllSuppliers();

        String[] cols = {"Supplier ID", "Name", "Contact", "Email", "Address"};
        Object[][] data = new Object[list.size()][5];
        
        for (int i = 0; i < list.size(); i++) {
            Supplier s = list.get(i);
            data[i][0] = s.getSupplierId();
            data[i][1] = s.getSupplierNames();
            data[i][2] = s.getSuppliercontact();
            data[i][3] = s.getSupplierEmail();
            data[i][4] = s.getAddress();
        }
        
        supplierTable.setModel(new DefaultTableModel(data, cols) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
    }

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SupplierManagement().setVisible(true);
            }
        });
    }

    // Variables declaration
    private JPanel mainPanel;
    private JPanel headerPanel;
    private JLabel titleLabel;
    private JLabel subtitleLabel;
    private JButton backButton;
    private JPanel contentPanel;
    private JPanel formPanel;
    private JLabel supplierIdLabel;
    private JTextField supplierIdTxt;
    private JLabel supplierNameLabel;
    private JTextField supplierNameTxt;
    private JLabel contactLabel;
    private JTextField contactTxt;
    private JLabel emailLabel;
    private JTextField emailTxt;
    private JLabel addressLabel;
    private JTextField addressTxt;
    private JLabel searchLabel;
    private JTextField searchTxt;
    private JButton searchBtn;
    private JPanel buttonPanel;
    private JButton saveBtn;
    private JButton updateBtn;
    private JButton deleteBtn;
    private JButton displayBtn;
    private JPanel tablePanel;
    private JScrollPane tableScrollPane;
    private JTable supplierTable;
}