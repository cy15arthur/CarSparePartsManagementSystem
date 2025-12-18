package view;

import dao.CustomerDao;
import dao.CustomerDaoImpl;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.plaf.basic.BasicButtonUI; // <--- CRITICAL IMPORT
import java.awt.*;
import java.awt.event.*;
import model.Customer;
import util.UITheme;

public class CustomerManagement extends JFrame {
    
    private final CustomerDao customerDao = new CustomerDaoImpl();
    private static final java.util.logging.Logger logger = 
        java.util.logging.Logger.getLogger(CustomerManagement.class.getName());

    private JPanel mainPanel, headerPanel, contentPanel, formPanel, buttonPanel, tablePanel;
    private JLabel titleLabel, subtitleLabel, searchLabel;
    private JTextField customerIdTxt, firstNameTxt, lastNameTxt, emailTxt, phoneTxt, searchTxt;
    private JButton backButton, searchBtn, saveBtn, updateBtn, deleteBtn, displayBtn;
    private JScrollPane tableScrollPane;
    private JTable customerTable;

    public CustomerManagement() {
        initComponents();
        setLocationRelativeTo(null);
        loadCustomersToTable();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Customer Management - Car Spare Parts System");
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

        titleLabel = new JLabel("Customer Management");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(UITheme.TEXT_WHITE);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        subtitleLabel = new JLabel("Manage customer information and contacts");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitleLabel.setForeground(new Color(255, 255, 255, 180));
        subtitleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        subtitleLabel.setBorder(new EmptyBorder(5, 0, 0, 0));

        leftPanel.add(titleLabel);
        leftPanel.add(subtitleLabel);

        // --- Back Button Fix ---
        backButton = new JButton("← Back to Dashboard");
        backButton.setUI(new BasicButtonUI()); // Removes Windows styling
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        backButton.setForeground(UITheme.TEXT_WHITE);
        backButton.setBackground(new Color(52, 73, 94));
        backButton.setBorder(new EmptyBorder(12, 30, 12, 30));
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (Login.getCurrentUser() != null && Login.getCurrentUser().isAdmin()) {
                    new AdminDashboard().setVisible(true);
                } else {
                    new HomePage().setVisible(true);
                }
                dispose();
            }
        });
        setupButtonHover(backButton, new Color(52, 73, 94), new Color(44, 62, 80));

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

        createFormPanel();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.35;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(0, 0, 0, 20);
        contentPanel.add(formPanel, gbc);

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

        addFormField("Customer ID", customerIdTxt = createTextField());
        addFormField("First Name", firstNameTxt = createTextField());
        addFormField("Last Name", lastNameTxt = createTextField());
        addFormField("Email Address", emailTxt = createTextField());
        addFormField("Phone Number", phoneTxt = createTextField());

        searchLabel = createLabel("Search by ID");
        searchLabel.setBorder(new EmptyBorder(20, 0, 0, 0));
        formPanel.add(searchLabel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 8)));

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));
        searchPanel.setOpaque(false);
        searchPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        searchPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));

        searchTxt = createTextField();
        searchBtn = createButton("Search", UITheme.INFO, new Color(23, 162, 184));
        searchBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                searchBtnActionPerformed();
            }
        });

        searchPanel.add(searchTxt);
        searchPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        searchPanel.add(searchBtn);
        formPanel.add(searchPanel);

        createButtons();
        formPanel.add(Box.createVerticalGlue());
    }

    private void createButtons() {
        buttonPanel = new JPanel(new GridLayout(2, 2, 15, 15));
        buttonPanel.setOpaque(false);
        buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        buttonPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 110));
        buttonPanel.setBorder(new EmptyBorder(20, 0, 0, 0));

        saveBtn = createButton("Save", UITheme.BUTTON_SECONDARY, new Color(23, 162, 184));
        saveBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) { saveBtnActionPerformed(); }
        });

        updateBtn = createButton("Update", UITheme.BUTTON_WARNING, new Color(227, 151, 0));
        updateBtn.setForeground(Color.BLACK); // Make text black for yellow button
        updateBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) { updateBtnActionPerformed(); }
        });

        deleteBtn = createButton("Delete", UITheme.BUTTON_DANGER, new Color(200, 35, 51));
        deleteBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) { deleteBtnActionPerformed(); }
        });

        displayBtn = createButton("View All", UITheme.BUTTON_PRIMARY, UITheme.PRIMARY);
        displayBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) { loadCustomersToTable(); }
        });

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

        JLabel tableTitle = new JLabel("Customers List");
        tableTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        tableTitle.setForeground(UITheme.TEXT_PRIMARY);
        tableTitle.setBorder(new EmptyBorder(0, 0, 15, 0));

        customerTable = new JTable();
        customerTable.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        customerTable.setRowHeight(40);
        customerTable.setGridColor(new Color(240, 240, 240));
        customerTable.setSelectionBackground(UITheme.PRIMARY_LIGHT);
        customerTable.setSelectionForeground(UITheme.TEXT_PRIMARY);
        customerTable.setShowVerticalLines(false);
        customerTable.setIntercellSpacing(new Dimension(0, 1));
        
        customerTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        customerTable.getTableHeader().setBackground(UITheme.BACKGROUND_DARK);
        customerTable.getTableHeader().setForeground(UITheme.TEXT_PRIMARY);
        
        customerTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                int row = customerTable.getSelectedRow();
                if (row != -1) {
                    customerIdTxt.setText(customerTable.getValueAt(row, 0).toString());
                    firstNameTxt.setText(customerTable.getValueAt(row, 1).toString());
                    lastNameTxt.setText(customerTable.getValueAt(row, 2).toString());
                    emailTxt.setText(customerTable.getValueAt(row, 3).toString());
                    phoneTxt.setText(customerTable.getValueAt(row, 4).toString());
                    customerIdTxt.setEditable(false);
                }
            }
        });

        tableScrollPane = new JScrollPane(customerTable);
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

    /**
     * APPLIED FIX: Using BasicButtonUI to ensure colors render correctly on all OS
     */
    private JButton createButton(String text, Color normalColor, Color hoverColor) {
        JButton btn = new JButton(text);
        btn.setUI(new BasicButtonUI()); // The Fix
        
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setBackground(normalColor);
        btn.setForeground(Color.WHITE); // Default white text
        btn.setBorder(new EmptyBorder(12, 20, 12, 20));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        setupButtonHover(btn, normalColor, hoverColor);
        return btn;
    }

    private void setupButtonHover(JButton button, Color normalColor, Color hoverColor) {
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) { button.setBackground(hoverColor); }
            public void mouseExited(MouseEvent evt) { button.setBackground(normalColor); }
        });
    }

    // --- Logic Implementation ---

    private void saveBtnActionPerformed() {
        if (!validateFields()) return;
        Customer c = buildCustomer();
        if (customerDao.create(c) > 0) {
            JOptionPane.showMessageDialog(this, "Customer saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
            loadCustomersToTable();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to save customer!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateBtnActionPerformed() {
        if (customerIdTxt.isEditable()) {
            JOptionPane.showMessageDialog(this, "Search customer first!", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Customer c = buildCustomer();
        if (customerDao.update(c) > 0) {
            JOptionPane.showMessageDialog(this, "Customer updated!", "Success", JOptionPane.INFORMATION_MESSAGE);
            customerIdTxt.setEditable(true);
            clearFields();
            loadCustomersToTable();
        }
    }

    private void deleteBtnActionPerformed() {
        if (customerIdTxt.isEditable()) {
            JOptionPane.showMessageDialog(this, "Search customer first!", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Delete customer?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION && customerDao.delete(customerIdTxt.getText().trim()) > 0) {
            JOptionPane.showMessageDialog(this, "Customer deleted!", "Success", JOptionPane.INFORMATION_MESSAGE);
            customerIdTxt.setEditable(true);
            clearFields();
            loadCustomersToTable();
        }
    }

    private void searchBtnActionPerformed() {
        String id = searchTxt.getText().trim();
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter ID!", "Missing Input", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Customer c = customerDao.searchById(id);
        if (c == null) {
            JOptionPane.showMessageDialog(this, "Not found!", "Not Found", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
        } else {
            customerIdTxt.setText(c.getCustomerId());
            firstNameTxt.setText(c.getCustomerFirstName());
            lastNameTxt.setText(c.getCustomerLastName());
            emailTxt.setText(c.getCustomerEmail());
            phoneTxt.setText(c.getCustomerPhone());
            customerIdTxt.setEditable(false);
            searchTxt.setText("");
        }
    }

    private boolean validateFields() {
        if (customerIdTxt.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Customer ID required!", "Validation", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (firstNameTxt.getText().trim().isEmpty() || !firstNameTxt.getText().matches("^[A-Za-z ]+$")) {
            JOptionPane.showMessageDialog(this, "Valid first name required!", "Validation", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (lastNameTxt.getText().trim().isEmpty() || !lastNameTxt.getText().matches("^[A-Za-z ]+$")) {
            JOptionPane.showMessageDialog(this, "Valid last name required!", "Validation", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!emailTxt.getText().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            JOptionPane.showMessageDialog(this, "Valid email required!", "Validation", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!phoneTxt.getText().matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Valid phone required!", "Validation", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private Customer buildCustomer() {
        Customer c = new Customer();
        c.setCustomerId(customerIdTxt.getText().trim());
        c.setCustomerFirstName(firstNameTxt.getText().trim());
        c.setCustomerLastName(lastNameTxt.getText().trim());
        c.setCustomerEmail(emailTxt.getText().trim());
        c.setCustomerPhone(phoneTxt.getText().trim());
        return c;
    }

    private void clearFields() {
        customerIdTxt.setText("");
        firstNameTxt.setText("");
        lastNameTxt.setText("");
        emailTxt.setText("");
        phoneTxt.setText("");
        searchTxt.setText("");
        customerIdTxt.setEditable(true);
    }

    private void loadCustomersToTable() {
        List<Customer> list = customerDao.displayAllCustomers();
        String[] cols = {"Customer ID", "First Name", "Last Name", "Email", "Phone"};
        Object[][] data = new Object[list.size()][5];
        for (int i = 0; i < list.size(); i++) {
            Customer c = list.get(i);
            data[i][0] = c.getCustomerId();
            data[i][1] = c.getCustomerFirstName();
            data[i][2] = c.getCustomerLastName();
            data[i][3] = c.getCustomerEmail();
            data[i][4] = c.getCustomerPhone();
        }
        customerTable.setModel(new DefaultTableModel(data, cols) {
            public boolean isCellEditable(int row, int column) { return false; }
        });
    }

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() { new CustomerManagement().setVisible(true); }
        });
    }
}