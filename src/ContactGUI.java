import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ContactGUI extends JFrame {

    // =========================================================
    // COLORS
    // =========================================================

    private static final Color BACKGROUND =
            new Color(245, 247, 250);

    private static final Color SIDEBAR =
            new Color(31, 41, 55);

    private static final Color SIDEBAR_HOVER =
            new Color(55, 65, 81);

    private static final Color PRIMARY =
            new Color(37, 99, 235);

    private static final Color PRIMARY_HOVER =
            new Color(29, 78, 216);

    private static final Color DANGER =
            new Color(220, 38, 38);

    private static final Color DANGER_HOVER =
            new Color(185, 28, 28);

    private static final Color TEXT =
            new Color(31, 41, 55);

    private static final Color SECONDARY_TEXT =
            new Color(107, 114, 128);

    private static final Color WHITE =
            Color.WHITE;

    private static final Color TABLE_HEADER =
            new Color(31, 41, 55);

    private static final Color TABLE_BORDER =
            new Color(229, 231, 235);


    // =========================================================
    // COMPONENTS
    // =========================================================

    private JTable contactTable;

    private DefaultTableModel tableModel;

    private JTextField searchField;

    private JLabel contactCountLabel;

    private ContactManager manager;


    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public ContactGUI() {

        manager = new ContactManager();

        setTitle(
                "Contact Management System"
        );

        setSize(
                1000,
                650
        );

        setMinimumSize(
                new Dimension(
                        850,
                        550
                )
        );

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);

        getContentPane().setBackground(
                BACKGROUND
        );

        setLayout(
                new BorderLayout()
        );

        createUI();

        refreshTable();
    }


    // =========================================================
    // CREATE UI
    // =========================================================

    private void createUI() {

        createSidebar();

        createMainPanel();
    }


    // =========================================================
    // SIDEBAR
    // =========================================================

    private void createSidebar() {

        JPanel sidebar =
                new JPanel();

        sidebar.setPreferredSize(
                new Dimension(
                        210,
                        0
                )
        );

        sidebar.setBackground(
                SIDEBAR
        );

        sidebar.setLayout(
                new BorderLayout()
        );


        // -----------------------------------------------------
        // LOGO
        // -----------------------------------------------------

        JPanel logoPanel =
                new JPanel(
                        new BorderLayout()
                );

        logoPanel.setBackground(
                SIDEBAR
        );

        logoPanel.setBorder(
                new EmptyBorder(
                        30,
                        20,
                        30,
                        20
                )
        );


        JLabel logo =
                new JLabel(
                        "📇  Contacts"
                );

        logo.setForeground(
                WHITE
        );

        logo.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        22
                )
        );


        logoPanel.add(
                logo,
                BorderLayout.CENTER
        );


        sidebar.add(
                logoPanel,
                BorderLayout.NORTH
        );


        // -----------------------------------------------------
        // MENU
        // -----------------------------------------------------

        JPanel menuPanel =
                new JPanel();

        menuPanel.setBackground(
                SIDEBAR
        );

        menuPanel.setLayout(
                new BoxLayout(
                        menuPanel,
                        BoxLayout.Y_AXIS
                )
        );

        menuPanel.setBorder(
                new EmptyBorder(
                        10,
                        15,
                        10,
                        15
                )
        );


        JButton addButton =
                createSidebarButton(
                        "＋  Add Contact"
                );

        JButton refreshButton =
                createSidebarButton(
                        "↻  Refresh"
                );

        JButton sortButton =
                createSidebarButton(
                        "↕  Sort Contacts"
                );


        addButton.addActionListener(
                e -> showAddDialog()
        );

        refreshButton.addActionListener(
                e -> refreshTable()
        );

        sortButton.addActionListener(
                e -> showSortDialog()
        );


        menuPanel.add(
                addButton
        );

        menuPanel.add(
                Box.createVerticalStrut(
                        10
                )
        );

        menuPanel.add(
                refreshButton
        );

        menuPanel.add(
                Box.createVerticalStrut(
                        10
                )
        );

        menuPanel.add(
                sortButton
        );


        sidebar.add(
                menuPanel,
                BorderLayout.CENTER
        );


        // -----------------------------------------------------
        // FOOTER
        // -----------------------------------------------------

        JLabel footer =
                new JLabel(
                        "Contact Manager v1.0"
                );

        footer.setForeground(
                new Color(
                        156,
                        163,
                        175
                )
        );

        footer.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        12
                )
        );

        footer.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        footer.setBorder(
                new EmptyBorder(
                        15,
                        5,
                        20,
                        5
                )
        );


        sidebar.add(
                footer,
                BorderLayout.SOUTH
        );


        add(
                sidebar,
                BorderLayout.WEST
        );
    }


    // =========================================================
    // SIDEBAR BUTTON
    // =========================================================

    private JButton createSidebarButton(
            String text) {

        JButton button =
                new JButton(
                        text
                );


        button.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        15
                )
        );


        // IMPORTANT:
        // Force our colors

        button.setForeground(
                WHITE
        );

        button.setBackground(
                SIDEBAR
        );

        button.setOpaque(
                true
        );

        button.setContentAreaFilled(
                true
        );

        button.setBorderPainted(
                false
        );

        button.setFocusPainted(
                false
        );


        button.setHorizontalAlignment(
                SwingConstants.LEFT
        );


        button.setBorder(
                new EmptyBorder(
                        12,
                        15,
                        12,
                        15
                )
        );


        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );


        button.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        50
                )
        );


        // Hover effect

        button.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseEntered(
                            MouseEvent e) {

                        button.setBackground(
                                SIDEBAR_HOVER
                        );

                        button.setForeground(
                                WHITE
                        );
                    }


                    @Override
                    public void mouseExited(
                            MouseEvent e) {

                        button.setBackground(
                                SIDEBAR
                        );

                        button.setForeground(
                                WHITE
                        );
                    }
                }
        );


        return button;
    }


    // =========================================================
    // MAIN PANEL
    // =========================================================

    private void createMainPanel() {

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout()
                );


        mainPanel.setBackground(
                BACKGROUND
        );


        mainPanel.setBorder(
                new EmptyBorder(
                        25,
                        30,
                        25,
                        30
                )
        );


        // =====================================================
        // TOP
        // =====================================================

        JPanel topPanel =
                new JPanel(
                        new BorderLayout()
                );


        topPanel.setBackground(
                BACKGROUND
        );


        // -----------------------------------------------------
        // TITLE
        // -----------------------------------------------------

        JLabel title =
                new JLabel(
                        "My Contacts"
                );


        title.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        28
                )
        );


        title.setForeground(
                TEXT
        );


        topPanel.add(
                title,
                BorderLayout.WEST
        );


        // -----------------------------------------------------
        // SEARCH
        // -----------------------------------------------------

        searchField =
                new JTextField();


        searchField.setPreferredSize(
                new Dimension(
                        280,
                        40
                )
        );


        searchField.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        14
                )
        );


        // Force text colors

        searchField.setForeground(
                TEXT
        );

        searchField.setBackground(
                WHITE
        );

        searchField.setCaretColor(
                TEXT
        );

        searchField.setOpaque(
                true
        );


        searchField.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        209,
                                        213,
                                        219
                                )
                        ),
                        new EmptyBorder(
                                5,
                                12,
                                5,
                                12
                        )
                )
        );


        // Placeholder

        searchField.putClientProperty(
                "JTextField.placeholderText",
                "Search contacts..."
        );


        // Search while typing

        searchField
                .getDocument()
                .addDocumentListener(
                        new DocumentListener() {

                            @Override
                            public void insertUpdate(
                                    DocumentEvent e) {

                                performSearch();
                            }


                            @Override
                            public void removeUpdate(
                                    DocumentEvent e) {

                                performSearch();
                            }


                            @Override
                            public void changedUpdate(
                                    DocumentEvent e) {

                                performSearch();
                            }
                        }
                );


        topPanel.add(
                searchField,
                BorderLayout.EAST
        );


        mainPanel.add(
                topPanel,
                BorderLayout.NORTH
        );


        // =====================================================
        // TABLE
        // =====================================================

        createTable();


        JScrollPane scrollPane =
                new JScrollPane(
                        contactTable
                );


        scrollPane.setBorder(
                BorderFactory.createLineBorder(
                        TABLE_BORDER
                )
        );


        scrollPane.setBackground(
                WHITE
        );


        mainPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );


        // =====================================================
        // BOTTOM
        // =====================================================

        JPanel bottomPanel =
                new JPanel(
                        new BorderLayout()
                );


        bottomPanel.setBackground(
                BACKGROUND
        );


        bottomPanel.setBorder(
                new EmptyBorder(
                        15,
                        0,
                        0,
                        0
                )
        );


        // -----------------------------------------------------
        // CONTACT COUNT
        // -----------------------------------------------------

        contactCountLabel =
                new JLabel();


        contactCountLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        14
                )
        );


        contactCountLabel.setForeground(
                SECONDARY_TEXT
        );


        bottomPanel.add(
                contactCountLabel,
                BorderLayout.WEST
        );


        // -----------------------------------------------------
        // ACTION BUTTONS
        // -----------------------------------------------------

        JPanel actionPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                10,
                                0
                        )
                );


        actionPanel.setBackground(
                BACKGROUND
        );


        JButton editButton =
                createPrimaryButton(
                        "✏  Edit"
                );


        JButton deleteButton =
                createDangerButton(
                        "🗑  Delete"
                );


        editButton.addActionListener(
                e -> editSelectedContact()
        );


        deleteButton.addActionListener(
                e -> deleteSelectedContact()
        );


        actionPanel.add(
                editButton
        );


        actionPanel.add(
                deleteButton
        );


        bottomPanel.add(
                actionPanel,
                BorderLayout.EAST
        );


        mainPanel.add(
                bottomPanel,
                BorderLayout.SOUTH
        );


        add(
                mainPanel,
                BorderLayout.CENTER
        );
    }


    // =========================================================
    // TABLE
    // =========================================================

    private void createTable() {

        String[] columns = {
                "Name",
                "Phone",
                "Email"
        };


        tableModel =
                new DefaultTableModel(
                        columns,
                        0
                ) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column) {

                        return false;
                    }
                };


        contactTable =
                new JTable(
                        tableModel
                );


        // -----------------------------------------------------
        // TABLE STYLE
        // -----------------------------------------------------

        contactTable.setRowHeight(
                45
        );


        contactTable.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        14
                )
        );


        contactTable.setForeground(
                TEXT
        );


        contactTable.setBackground(
                WHITE
        );


        contactTable.setSelectionBackground(
                new Color(
                        219,
                        234,
                        254
                )
        );


        contactTable.setSelectionForeground(
                TEXT
        );


        contactTable.setGridColor(
                TABLE_BORDER
        );


        contactTable.setShowVerticalLines(
                false
        );


        contactTable.setIntercellSpacing(
                new Dimension(
                        0,
                        1
                )
        );


        // =====================================================
        // TABLE HEADER
        // =====================================================

        contactTable
                .getTableHeader()
                .setFont(
                        new Font(
                                "SansSerif",
                                Font.BOLD,
                                14
                        )
                );


        contactTable
                .getTableHeader()
                .setForeground(
                        WHITE
                );


        contactTable
                .getTableHeader()
                .setBackground(
                        TABLE_HEADER
                );


        contactTable
                .getTableHeader()
                .setOpaque(
                        true
                );


        contactTable
                .getTableHeader()
                .setPreferredSize(
                        new Dimension(
                                0,
                                45
                        )
                );


        // =====================================================
        // PHONE COLUMN CENTER
        // =====================================================

        DefaultTableCellRenderer centerRenderer =
                new DefaultTableCellRenderer();


        centerRenderer.setHorizontalAlignment(
                SwingConstants.CENTER
        );


        contactTable
                .getColumnModel()
                .getColumn(1)
                .setCellRenderer(
                        centerRenderer
                );


        // =====================================================
        // COLUMN WIDTHS
        // =====================================================

        contactTable
                .getColumnModel()
                .getColumn(0)
                .setPreferredWidth(
                        180
                );


        contactTable
                .getColumnModel()
                .getColumn(1)
                .setPreferredWidth(
                        150
                );


        contactTable
                .getColumnModel()
                .getColumn(2)
                .setPreferredWidth(
                        300
                );


        // =====================================================
        // DOUBLE CLICK = EDIT
        // =====================================================

        contactTable.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseClicked(
                            MouseEvent e) {

                        if (
                                e.getClickCount() == 2
                        ) {

                            editSelectedContact();
                        }
                    }
                }
        );
    }


    // =========================================================
    // REFRESH TABLE
    // =========================================================

    private void refreshTable() {

        tableModel.setRowCount(0);


        for (
                Contact contact :
                manager.getContacts()
        ) {

            tableModel.addRow(
                    new Object[]{
                            contact.getName(),
                            contact.getPhone(),
                            contact.getEmail()
                    }
            );
        }


        updateContactCount();
    }


    // =========================================================
    // SEARCH
    // =========================================================

    private void performSearch() {

        String search =
                searchField
                        .getText()
                        .trim();


        tableModel.setRowCount(0);


        if (search.isEmpty()) {

            refreshTable();

            return;
        }


        ArrayList<Contact> results =
                manager.searchContacts(
                        search
                );


        for (
                Contact contact :
                results
        ) {

            tableModel.addRow(
                    new Object[]{
                            contact.getName(),
                            contact.getPhone(),
                            contact.getEmail()
                    }
            );
        }


        contactCountLabel.setText(
                results.size()
                + " contact(s) found"
        );
    }


    // =========================================================
    // ADD CONTACT DIALOG
    // =========================================================

    private void showAddDialog() {

        JTextField nameField =
                new JTextField();


        JTextField phoneField =
                new JTextField();


        JTextField emailField =
                new JTextField();


        JPanel panel =
                createFormPanel(
                        nameField,
                        phoneField,
                        emailField
                );


        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        panel,
                        "Add Contact",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );


        if (
                result !=
                JOptionPane.OK_OPTION
        ) {

            return;
        }


        String name =
                nameField
                        .getText()
                        .trim();


        String phone =
                phoneField
                        .getText()
                        .trim();


        String email =
                emailField
                        .getText()
                        .trim();


        if (
                !validateContact(
                        name,
                        phone,
                        email
                )
        ) {

            return;
        }


        if (
                !manager.addContact(
                        name,
                        phone,
                        email
                )
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "A contact with this phone number already exists.",
                    "Duplicate Contact",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        refreshTable();


        JOptionPane.showMessageDialog(
                this,
                "Contact added successfully! ✅",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
        );
    }


    // =========================================================
    // FORM PANEL
    // =========================================================

    private JPanel createFormPanel(
            JTextField nameField,
            JTextField phoneField,
            JTextField emailField) {

        JPanel panel =
                new JPanel(
                        new GridLayout(
                                3,
                                2,
                                10,
                                15
                        )
                );


        panel.setBackground(
                WHITE
        );


        panel.setBorder(
                new EmptyBorder(
                        10,
                        10,
                        10,
                        10
                )
        );


        JLabel nameLabel =
                new JLabel(
                        "Name:"
                );


        JLabel phoneLabel =
                new JLabel(
                        "Phone:"
                );


        JLabel emailLabel =
                new JLabel(
                        "Email:"
                );


        nameLabel.setForeground(
                TEXT
        );

        phoneLabel.setForeground(
                TEXT
        );

        emailLabel.setForeground(
                TEXT
        );


        panel.add(
                nameLabel
        );

        panel.add(
                nameField
        );

        panel.add(
                phoneLabel
        );

        panel.add(
                phoneField
        );

        panel.add(
                emailLabel
        );

        panel.add(
                emailField
        );


        // Make text fields readable

        JTextField[] fields = {
                nameField,
                phoneField,
                emailField
        };


        for (
                JTextField field :
                fields
        ) {

            field.setForeground(
                    TEXT
            );

            field.setBackground(
                    WHITE
            );

            field.setCaretColor(
                    TEXT
            );
        }


        return panel;
    }


    // =========================================================
    // EDIT CONTACT
    // =========================================================

    private void editSelectedContact() {

        int row =
                contactTable
                        .getSelectedRow();


        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a contact first.",
                    "No Contact Selected",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        String currentName =
                tableModel
                        .getValueAt(
                                row,
                                0
                        )
                        .toString();


        String currentPhone =
                tableModel
                        .getValueAt(
                                row,
                                1
                        )
                        .toString();


        String currentEmail =
                tableModel
                        .getValueAt(
                                row,
                                2
                        )
                        .toString();


        int actualIndex =
                findContactIndex(
                        currentPhone
                );


        if (actualIndex == -1) {

            return;
        }


        JTextField nameField =
                new JTextField(
                        currentName
                );


        JTextField phoneField =
                new JTextField(
                        currentPhone
                );


        JTextField emailField =
                new JTextField(
                        currentEmail
                );


        JPanel panel =
                createFormPanel(
                        nameField,
                        phoneField,
                        emailField
                );


        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        panel,
                        "Edit Contact",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );


        if (
                result !=
                JOptionPane.OK_OPTION
        ) {

            return;
        }


        String name =
                nameField
                        .getText()
                        .trim();


        String phone =
                phoneField
                        .getText()
                        .trim();


        String email =
                emailField
                        .getText()
                        .trim();


        if (
                !validateContact(
                        name,
                        phone,
                        email
                )
        ) {

            return;
        }


        if (
                !manager.updateContact(
                        actualIndex,
                        name,
                        phone,
                        email
                )
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Another contact already uses this phone number.",
                    "Duplicate Phone",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        refreshTable();


        JOptionPane.showMessageDialog(
                this,
                "Contact updated successfully! ✅",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
        );
    }


    // =========================================================
    // DELETE CONTACT
    // =========================================================

    private void deleteSelectedContact() {

        int row =
                contactTable
                        .getSelectedRow();


        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a contact first.",
                    "No Contact Selected",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        String name =
                tableModel
                        .getValueAt(
                                row,
                                0
                        )
                        .toString();


        String phone =
                tableModel
                        .getValueAt(
                                row,
                                1
                        )
                        .toString();


        int confirmation =
                JOptionPane.showConfirmDialog(
                        this,
                        "Delete contact \"" +
                                name +
                                "\"?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );


        if (
                confirmation !=
                JOptionPane.YES_OPTION
        ) {

            return;
        }


        int actualIndex =
                findContactIndex(
                        phone
                );


        if (actualIndex != -1) {

            manager.deleteContact(
                    actualIndex
            );


            refreshTable();


            JOptionPane.showMessageDialog(
                    this,
                    "Contact deleted successfully! 🗑",
                    "Deleted",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }


    // =========================================================
    // FIND CONTACT
    // =========================================================

    private int findContactIndex(
            String phone) {

        ArrayList<Contact> contacts =
                manager.getContacts();


        for (
                int i = 0;
                i < contacts.size();
                i++
        ) {

            if (
                    contacts.get(i)
                            .getPhone()
                            .equals(phone)
            ) {

                return i;
            }
        }


        return -1;
    }


    // =========================================================
    // SORT CONTACTS
    // =========================================================

    private void showSortDialog() {

        String[] options = {
                "Name (A-Z)",
                "Name (Z-A)",
                "Phone Number"
        };


        String selected =
                (String) JOptionPane.showInputDialog(
                        this,
                        "Sort contacts by:",
                        "Sort Contacts",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        options,
                        options[0]
                );


        if (selected == null) {

            return;
        }


        ArrayList<Contact> contacts =
                manager.getContacts();


        switch (selected) {

            case "Name (A-Z)":

                contacts.sort(
                        (c1, c2) ->
                                c1.getName()
                                        .compareToIgnoreCase(
                                                c2.getName()
                                        )
                );

                break;


            case "Name (Z-A)":

                contacts.sort(
                        (c1, c2) ->
                                c2.getName()
                                        .compareToIgnoreCase(
                                                c1.getName()
                                        )
                );

                break;


            case "Phone Number":

                contacts.sort(
                        (c1, c2) ->
                                c1.getPhone()
                                        .compareTo(
                                                c2.getPhone()
                                        )
                );

                break;
        }


        manager.saveContacts();


        refreshTable();
    }


    // =========================================================
    // VALIDATE CONTACT
    // =========================================================

    private boolean validateContact(
            String name,
            String phone,
            String email) {


        // Name

        if (name.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Name cannot be empty.",
                    "Invalid Name",
                    JOptionPane.ERROR_MESSAGE
            );

            return false;
        }


        if (
                !name.matches(
                        "[a-zA-Z ]+"
                )
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Name can contain only letters and spaces.",
                    "Invalid Name",
                    JOptionPane.ERROR_MESSAGE
            );

            return false;
        }


        // Phone

        if (
                !phone.matches(
                        "\\d{10}"
                )
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Phone number must contain exactly 10 digits.",
                    "Invalid Phone",
                    JOptionPane.ERROR_MESSAGE
            );

            return false;
        }


        // Email

        if (
                !email.matches(
                        "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
                )
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid email address.",
                    "Invalid Email",
                    JOptionPane.ERROR_MESSAGE
            );

            return false;
        }


        return true;
    }


    // =========================================================
    // CONTACT COUNT
    // =========================================================

    private void updateContactCount() {

        int count =
                manager
                        .getContacts()
                        .size();


        if (count == 1) {

            contactCountLabel.setText(
                    "1 contact"
            );

        } else {

            contactCountLabel.setText(
                    count +
                    " contacts"
            );
        }
    }


    // =========================================================
    // PRIMARY BUTTON
    // =========================================================

    private JButton createPrimaryButton(
            String text) {

        JButton button =
                new JButton(
                        text
                );


        button.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        14
                )
        );


        button.setForeground(
                WHITE
        );


        button.setBackground(
                PRIMARY
        );


        button.setOpaque(
                true
        );


        button.setContentAreaFilled(
                true
        );


        button.setBorderPainted(
                false
        );


        button.setFocusPainted(
                false
        );


        button.setBorder(
                new EmptyBorder(
                        10,
                        20,
                        10,
                        20
                )
        );


        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );


        button.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseEntered(
                            MouseEvent e) {

                        button.setBackground(
                                PRIMARY_HOVER
                        );

                        button.setForeground(
                                WHITE
                        );
                    }


                    @Override
                    public void mouseExited(
                            MouseEvent e) {

                        button.setBackground(
                                PRIMARY
                        );

                        button.setForeground(
                                WHITE
                        );
                    }
                }
        );


        return button;
    }


    // =========================================================
    // DANGER BUTTON
    // =========================================================

    private JButton createDangerButton(
            String text) {

        JButton button =
                new JButton(
                        text
                );


        button.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        14
                )
        );


        button.setForeground(
                WHITE
        );


        button.setBackground(
                DANGER
        );


        button.setOpaque(
                true
        );


        button.setContentAreaFilled(
                true
        );


        button.setBorderPainted(
                false
        );


        button.setFocusPainted(
                false
        );


        button.setBorder(
                new EmptyBorder(
                        10,
                        20,
                        10,
                        20
                )
        );


        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );


        button.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseEntered(
                            MouseEvent e) {

                        button.setBackground(
                                DANGER_HOVER
                        );

                        button.setForeground(
                                WHITE
                        );
                    }


                    @Override
                    public void mouseExited(
                            MouseEvent e) {

                        button.setBackground(
                                DANGER
                        );

                        button.setForeground(
                                WHITE
                        );
                    }
                }
        );


        return button;
    }


    // =========================================================
    // MAIN
    // =========================================================

    public static void main(
            String[] args) {

        /*
         * We intentionally DON'T use:
         *
         * UIManager.setLookAndFeel(
         *     UIManager.getSystemLookAndFeelClassName()
         * );
         *
         * This allows our custom colors to remain visible.
         */


        SwingUtilities.invokeLater(
                () -> {

                    ContactGUI app =
                            new ContactGUI();

                    app.setVisible(true);
                }
        );
    }
}