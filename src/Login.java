import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.*;


/**
 * @author Đăng Nguyện và Thành Nhân
 */

public class Login extends JFrame {
    private Connection conn;
    private Statement stm;

    public Login() {
        initComponents();

    }
    private void showMessage(String mess){
        JOptionPane.showMessageDialog(this,mess);

    }
       public View view;
    String tenbacsi,mabacsi,matkhau;
    String[] thongtinbacsi = new String[0];

    private void dangnhap(ActionEvent e) {
        boolean check = false;
        try{

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/quanlyhosobenhan","dangnguyen","123456");
            stm = conn.createStatement();
            ResultSet rst = stm.executeQuery("SELECT * FROM doctors ");

            while (rst.next()) {
                if (rst.getString(1).equals(tfUserName.getText())) {
                    matkhau = rst.getString(2);
                }
                if (rst.getString(1).equals(tfUserName.getText()) && rst.getString(2).equals(String.valueOf(password.getPassword()))) {
                    thongtinbacsi = new String[]{rst.getString(3), rst.getString(4), rst.getString(7), rst.getString(6), rst.getString(8), rst.getString(9),rst.getString(10),rst.getString(11)};
                    tenbacsi = rst.getString(3);
                    mabacsi = rst.getString(1);
                    check = true;
                    break;
                }
            }
            if (check) {
                setVisible(false);
                view = new View(tenbacsi,mabacsi,thongtinbacsi);
                view.setVisible(true);
                view.setDefaultCloseOperation(3);

            } else {
                showMessage("ĐĂNG NHẬP THẤT BẠI!");
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void exit(ActionEvent e) {
        System.exit(0);
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        dialogPane = new JPanel();
        panel2 = new JPanel();
        label1 = new JLabel();
        background = new JLabel();
        panel1 = new JPanel();
        clock = new JLabel();
        btLogin = new JButton();
        btExit = new JButton();
        password = new JPasswordField();
        icon = new JLabel();
        lbPassword = new JLabel();
        tfUserName = new JTextField();
        lbUserName = new JLabel();

        //======== this ========
        setTitle("\u0110\u0102NG NH\u1eacP");
        setIconImage(new ImageIcon(getClass().getResource("/img/medical-team.png")).getImage());
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setLayout(null);

            //======== panel2 ========
            {
                panel2.setLayout(null);

                //---- label1 ----
                label1.setText("\u0110\u0102NG NH\u1eacP");
                label1.setFont(new Font("Montserrat Black", Font.PLAIN, 40));
                label1.setForeground(new Color(238, 238, 238));
                label1.setIcon(new ImageIcon(getClass().getResource("/img/enter.png")));
                panel2.add(label1);
                label1.setBounds(270, 5, 320, 65);

                //---- background ----
                background.setIcon(new ImageIcon(getClass().getResource("/img/13221321.png")));
                panel2.add(background);
                background.setBounds(0, 5, 590, 500);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < panel2.getComponentCount(); i++) {
                        Rectangle bounds = panel2.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = panel2.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    panel2.setMinimumSize(preferredSize);
                    panel2.setPreferredSize(preferredSize);
                }
            }
            dialogPane.add(panel2);
            panel2.setBounds(0, -5, 590, 505);

            //======== panel1 ========
            {
                panel1.setBackground(new Color(238, 238, 238));
                panel1.setLayout(null);

                //---- clock ----
                clock.setFont(new Font("Montserrat Black", Font.PLAIN, 14));
                clock.setForeground(new Color(51, 153, 255));
                panel1.add(clock);
                clock.setBounds(30, 10, 335, 45);

                //---- btLogin ----
                btLogin.setText("\u0110\u0102NG NH\u1eacP");
                btLogin.setFont(new Font("Montserrat Black", Font.PLAIN, 14));
                btLogin.setBackground(new Color(51, 153, 255));
                btLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                btLogin.setForeground(new Color(238, 238, 238));
                btLogin.setBorderPainted(false);
                btLogin.addActionListener(e -> dangnhap(e));
                panel1.add(btLogin);
                btLogin.setBounds(200, 410, 150, 40);

                //---- btExit ----
                btExit.setText("THO\u00c1T");
                btExit.setFont(new Font("Montserrat Black", Font.PLAIN, 14));
                btExit.setBackground(new Color(252, 50, 0));
                btExit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                btExit.setForeground(new Color(238, 238, 238));
                btExit.setBorderPainted(false);
                btExit.addActionListener(e -> exit(e));
                panel1.add(btExit);
                btExit.setBounds(30, 410, 165, 40);

                //---- password ----
                password.setBackground(new Color(204, 204, 204));
                password.setFont(new Font("Tahoma", Font.PLAIN, 18));
                password.setForeground(Color.black);
                password.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
                panel1.add(password);
                password.setBounds(30, 355, 325, 30);

                //---- icon ----
                icon.setIcon(new ImageIcon(getClass().getResource("/img/medical-team.png")));
                panel1.add(icon);
                icon.setBounds(130, 90, 125, 118);

                //---- lbPassword ----
                lbPassword.setText("M\u1eacT KH\u1ea8U :");
                lbPassword.setFont(new Font("Montserrat Black", Font.PLAIN, 18));
                lbPassword.setForeground(new Color(51, 153, 255));
                lbPassword.setIcon(new ImageIcon(getClass().getResource("/img/key.png")));
                lbPassword.setLabelFor(password);
                panel1.add(lbPassword);
                lbPassword.setBounds(30, 295, 175, 78);

                //---- tfUserName ----
                tfUserName.setBackground(new Color(204, 204, 204));
                tfUserName.setFont(new Font("Montserrat", Font.PLAIN, 18));
                tfUserName.setForeground(Color.black);
                tfUserName.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
                panel1.add(tfUserName);
                tfUserName.setBounds(30, 280, 325, 30);

                //---- lbUserName ----
                lbUserName.setText("T\u00caN \u0110\u0102NG NH\u1eacP");
                lbUserName.setFont(new Font("Montserrat Black", Font.PLAIN, 18));
                lbUserName.setForeground(new Color(51, 153, 255));
                lbUserName.setIcon(new ImageIcon(getClass().getResource("/img/user.png")));
                lbUserName.setLabelFor(tfUserName);
                panel1.add(lbUserName);
                lbUserName.setBounds(30, 220, 240, 78);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < panel1.getComponentCount(); i++) {
                        Rectangle bounds = panel1.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = panel1.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    panel1.setMinimumSize(preferredSize);
                    panel1.setPreferredSize(preferredSize);
                }
            }
            dialogPane.add(panel1);
            panel1.setBounds(590, 0, 380, 500);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        setSize(975, 540);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }





    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel dialogPane;
    private JPanel panel2;
    private JLabel label1;
    private JLabel background;
    private JPanel panel1;
    public JLabel clock;
    private JButton btLogin;
    private JButton btExit;
    private JPasswordField password;
    private JLabel icon;
    private JLabel lbPassword;
    private JTextField tfUserName;
    private JLabel lbUserName;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
