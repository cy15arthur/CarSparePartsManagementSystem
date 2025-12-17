package view;

import dao.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.plaf.basic.BasicButtonUI; // <--- CRITICAL IMPORT
import java.awt.*;
import java.awt.event.*;
import model.*;
import util.UITheme;

public class SalesManagement extends JFrame {
    
    private final SalesDao salesDao = new SalesDaoImpl();
    private static final java.util.logging.Logger logger = 
        java.util.logging.Logger.getLogger(SalesManagement.class.getName());

    private JPanel mainPanel, headerPanel, contentPanel, formPanel, buttonPanel, tablePanel;
    private JLabel titleLabel, subtitleLabel;
    private JTextField salesIdTxt, quantityTxt, totalPriceTxt, salesDateTxt;
    private JComboBox<String> partComboTxt, customerComboTxt;
    private JButton backButton, searchBtn, saveBtn, displayBtn;
    private JScrollPane tableScrollPane;
    private JTable salesTable;

    public SalesManagement() {
        initComponents();
        setLocationRelativeTo(null);
        loadPartsToCombo();
        loadCustomersToCombo();
        loadSalesToTable();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sales Management - Car Spare Parts System");
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

        titleLabel = new JLabel("Sales Management");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(UITheme.TEXT_WHITE);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        subtitleLabel = new JLabel("Record and manage sales transactions");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitleLabel.setForeground(new Color(255, 255, 255, 180));
        subtitleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        subtitleLabel.setBorder(new EmptyBorder(5, 0, 0, 0));

        leftPanel.add(titleLabel);
        leftPanel.add(subtitleLabel);

        // --- Back Button Fix ---
        backButton = new JButton("← Back to Dashboard");
        backButton.setUI(new BasicButtonUI()); // The Fix
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        backButton.setForeground(UITheme.TEXT_WHITE);
        backButton.setBackground(new Color(52, 73, 94));
        backButton.setBorder(new EmptyBorder(12, 30, 12, 30));
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                new AdminDashboard().setVisible(true);
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

        // Form on Left
        createFormPanel();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.35;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(0, 0, 0, 20);
        contentPanel.add(formPanel, gbc);

        // Table on Right
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

        addFormField("Sales ID", salesIdTxt = createTextField());
        addFormCombo("Select Part", partComboTxt = createComboBox());
        addFormCombo("Select Customer", customerComboTxt = createComboBox());
        addFormField("Quantity", quantityTxt = createTextField());
        addFormField("Total Price", totalPriceTxt = createTextField());
        addFormField("Sale Date (yyyy-MM-dd)", salesDateTxt = createTextField());

        createButtons();
        formPanel.add(Box.createVerticalGlue());
    }

    private void createButtons() {
        buttonPanel = new JPanel(new GridLayout(1, 2, 15, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        buttonPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        buttonPanel.setBorder(new EmptyBorder(20, 0, 0, 0));

        saveBtn = createButton("Save Sale", UITheme.BUTTON_SECONDARY, new Color(23, 162, 184));
        saveBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) { saveBtnActionPerformed(); }
        });

        searchBtn = createButton("Search", UITheme.INFO, new Color(23, 162, 184));
        searchBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) { searchBtnActionPerformed(); }
        });

        buttonPanel.add(saveBtn);
        buttonPanel.add(searchBtn);
        formPanel.add(buttonPanel);
    }

    private void createTablePanel() {
        tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(UITheme.SURFACE);
        tablePanel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(230, 230, 230), 1, true),
            new EmptyBorder(20, 20, 20, 20)
        ));

        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setOpaque(false);
        titlePanel.setBorder(new EmptyBorder(0, 0, 15, 0));

        JLabel tableTitle = new JLabel("Sales Transactions");
        tableTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        tableTitle.setForeground(UITheme.TEXT_PRIMARY);

        // --- Refresh Button Fix ---
        displayBtn = new JButton("Refresh");
        displayBtn.setUI(new BasicButtonUI()); // The Fix
        displayBtn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        displayBtn.setForeground(UITheme.TEXT_WHITE);
        displayBtn.setBackground(UITheme.BUTTON_PRIMARY);
        displayBtn.setBorder(new EmptyBorder(8, 16, 8, 16));
        displayBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        displayBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) { loadSalesToTable(); }
        });
        setupButtonHover(displayBtn, UITheme.BUTTON_PRIMARY, UITheme.PRIMARY);

        titlePanel.add(tableTitle, BorderLayout.WEST);
        titlePanel.add(displayBtn, BorderLayout.EAST);

        salesTable = new JTable();
        salesTable.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        salesTable.setRowHeight(40);
        salesTable.setGridColor(new Color(240, 240, 240));
        salesTable.setSelectionBackground(UITheme.PRIMARY_LIGHT);
        salesTable.setSelectionForeground(UITheme.TEXT_PRIMARY);
        salesTable.setShowVerticalLines(false);
        salesTable.setIntercellSpacing(new Dimension(0, 1));
        
        salesTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        salesTable.getTableHeader().setBackground(UITheme.BACKGROUND_DARK);
        salesTable.getTableHeader().setForeground(UITheme.TEXT_PRIMARY);
        
        salesTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                int row = salesTable.getSelectedRow();
                if (row != -1) {
                    salesIdTxt.setText(salesTable.getValueAt(row, 0).toString());
                    partComboTxt.setSelectedItem(salesTable.getValueAt(row, 1).toString());
                    customerComboTxt.setSelectedItem(salesTable.getValueAt(row, 2).toString());
                    quantityTxt.setText(salesTable.getValueAt(row, 3).toString());
                    totalPriceTxt.setText(salesTable.getValueAt(row, 4).toString());
                    salesDateTxt.setText(salesTable.getValueAt(row, 5).toString());
                    salesIdTxt.setEditable(false);
                }
            }
        });

        tableScrollPane = new JScrollPane(salesTable);
        tableScrollPane.setBorder(new LineBorder(new Color(230, 230, 230), 1));

        tablePanel.add(titlePanel, BorderLayout.NORTH);
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

    private void addFormCombo(String labelText, JComboBox<String> combo) {
        JLabel label = createLabel(labelText);
        label.setBorder(new EmptyBorder(15, 0, 0, 0));
        formPanel.add(label);
        formPanel.add(Box.createRigidArea(new Dimension(0, 8)));
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

    private JComboBox<String> createComboBox() {
        JComboBox<String> combo = new JComboBox<String>();
        combo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        combo.setBackground(UITheme.SURFACE);
        combo.setBorder(new LineBorder(new Color(200, 200, 200), 1, true));
        combo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        combo.setAlignmentX(Component.LEFT_ALIGNMENT);
        return combo;
    }

    /**
     * APPLIED FIX: Using BasicButtonUI
     */
    private JButton createButton(String text, Color normalColor, Color hoverColor) {
        JButton btn = new JButton(text);
        btn.setUI(new BasicButtonUI()); // The Fix
        
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setForeground(UITheme.TEXT_WHITE);
        btn.setBackground(normalColor);
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

        try {
            int qty = Integer.parseInt(quantityTxt.getText().trim());
            double price = Double.parseDouble(totalPriceTxt.getText().trim());
            
            LocalDateTime saleDate;
            DateTimeFormatter fmt2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String txt = salesDateTxt.getText().trim();
            
            if (txt.length() == 10) {
                saleDate = LocalDate.parse(txt, fmt2).atStartOfDay();
            } else {
                saleDate = LocalDateTime.parse(txt, fmt1);
            }

            Sales s = new Sales();
            s.setSalesId(salesIdTxt.getText().trim());
            s.setPartCode(partComboTxt.getSelectedItem().toString());
            s.setCustomerId(customerComboTxt.getSelectedItem().toString());
            s.setQuantity(qty);
            s.setTotalPrice(price);
            s.setSaleDate(saleDate);

            if (salesDao.create(s) > 0) {
                JOptionPane.showMessageDialog(this, "Sale recorded successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearFields();
                loadSalesToTable();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to save!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid date format! Use: yyyy-MM-dd", "Date Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void searchBtnActionPerformed() {
        String id = salesIdTxt.getText().trim();
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter Sales ID!", "Missing Input", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Sales s = salesDao.searchById(id);
        if (s == null) {
            JOptionPane.showMessageDialog(this, "Sale not found!", "Not Found", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
        } else {
            partComboTxt.setSelectedItem(s.getPartCode());
            customerComboTxt.setSelectedItem(s.getCustomerId());
            quantityTxt.setText(String.valueOf(s.getQuantity()));
            totalPriceTxt.setText(String.valueOf(s.getTotalPrice()));
            salesDateTxt.setText(String.valueOf(s.getSaleDate()));
            salesIdTxt.setEditable(false);
        }
    }

    private boolean validateFields() {
        if (salesIdTxt.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Sales ID required!", "Validation", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (partComboTxt.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Select a part!", "Validation", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (customerComboTxt.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Select a customer!", "Validation", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (quantityTxt.getText().trim().isEmpty() || !quantityTxt.getText().matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Valid quantity required!", "Validation", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (totalPriceTxt.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Price required!", "Validation", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (salesDateTxt.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Date required!", "Validation", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private void clearFields() {
        salesIdTxt.setText("");
        quantityTxt.setText("");
        totalPriceTxt.setText("");
        salesDateTxt.setText("");
        salesIdTxt.setEditable(true);
    }

    private void loadSalesToTable() {
        List<Sales> list = salesDao.displayAllSales();
        String[] cols = {"Sales ID", "Part", "Customer", "Qty", "Total Price", "Date"};
        Object[][] data = new Object[list.size()][6];
        
        for (int i = 0; i < list.size(); i++) {
            Sales s = list.get(i);
            data[i][0] = s.getSalesId();
            data[i][1] = s.getPartCode();
            data[i][2] = s.getCustomerId();
            data[i][3] = s.getQuantity();
            data[i][4] = s.getTotalPrice();
            data[i][5] = s.getSaleDate();
        }
        
        salesTable.setModel(new DefaultTableModel(data, cols) {
            public boolean isCellEditable(int row, int column) { return false; }
        });
    }

    private void loadPartsToCombo() {
        PartDao dao = new PartDaoImpl();
        List<model.Part> list = dao.displayAllParts();
        partComboTxt.removeAllItems();
        for (model.Part p : list) {
            partComboTxt.addItem(p.getPartCode());
        }
    }

    private void loadCustomersToCombo() {
        CustomerDao dao = new CustomerDaoImpl();
        List<Customer> list = dao.displayAllCustomers();
        customerComboTxt.removeAllItems();
        for (Customer c : list) {
            customerComboTxt.addItem(c.getCustomerId());
        }
    }

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() { new SalesManagement().setVisible(true); }
        });
    }
}