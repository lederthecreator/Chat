/*
 * Created by JFormDesigner on Tue Apr 18 18:56:38 MSK 2023
 */

package ru.leder.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.intellij.uiDesigner.core.*;

/**
 * @author User
 */
public class MainWindow1 extends JFrame {
    public MainWindow1() {
        initComponents();
    }

    private void signUpHandler(ActionEvent e) {
        // TODO add your code here
    }

    private void loginHandler(ActionEvent e) {
        // TODO add your code here
    }

    private void sendMessageHandler(ActionEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Alexandr
        panel3 = new JPanel();
        label2 = new JLabel();
        loginTextBox = new JTextField();
        label3 = new JLabel();
        passwordTextBox = new JTextField();
        button4 = new JButton();
        button5 = new JButton();
        panel2 = new JPanel();
        scrollPane1 = new JScrollPane();
        chatArea = new JTextArea();
        messageTextBox = new JTextField();
        button3 = new JButton();

        //======== this ========
        setTitle("Chat");
        setMinimumSize(new Dimension(310, 510));
        setMaximumSize(new Dimension(310, 510));
        var contentPane = getContentPane();
        contentPane.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));

        //======== panel3 ========
        {
            panel3.setMinimumSize(new Dimension(291, 100));
            panel3.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new
            javax. swing. border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmDes\u0069gner \u0045valua\u0074ion", javax
            . swing. border. TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java
            .awt .Font ("D\u0069alog" ,java .awt .Font .BOLD ,12 ), java. awt
            . Color. red) ,panel3. getBorder( )) ); panel3. addPropertyChangeListener (new java. beans.
            PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062order" .
            equals (e .getPropertyName () )) throw new RuntimeException( ); }} );
            panel3.setLayout(new GridLayoutManager(3, 3, new Insets(0, 0, 0, 0), -1, -1));

            //---- label2 ----
            label2.setText("Login");
            panel3.add(label2, new GridConstraints(0, 0, 1, 1,
                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                null, null, null));
            panel3.add(loginTextBox, new GridConstraints(0, 1, 1, 2,
                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                null, null, null));

            //---- label3 ----
            label3.setText("Password");
            panel3.add(label3, new GridConstraints(1, 0, 1, 1,
                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                null, null, null));
            panel3.add(passwordTextBox, new GridConstraints(1, 1, 1, 2,
                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                null, null, null));

            //---- button4 ----
            button4.setText("SignUp");
            button4.addActionListener(e -> signUpHandler(e));
            panel3.add(button4, new GridConstraints(2, 1, 1, 1,
                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                null, null, null));

            //---- button5 ----
            button5.setText("Login");
            button5.addActionListener(e -> loginHandler(e));
            panel3.add(button5, new GridConstraints(2, 2, 1, 1,
                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                null, null, null));
        }
        contentPane.add(panel3, new GridConstraints(0, 0, 1, 1,
            GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));

        //======== panel2 ========
        {
            panel2.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));

            //======== scrollPane1 ========
            {

                //---- chatArea ----
                chatArea.setFont(new Font("JetBrains Mono", Font.PLAIN, 16));
                chatArea.setLineWrap(true);
                chatArea.setEditable(false);
                chatArea.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
                scrollPane1.setViewportView(chatArea);
            }
            panel2.add(scrollPane1, new GridConstraints(0, 0, 1, 2,
                GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_BOTH,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                new Dimension(0, 300), null, null));
            panel2.add(messageTextBox, new GridConstraints(1, 0, 1, 1,
                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_CAN_SHRINK,
                null, null, null));

            //---- button3 ----
            button3.setText("Send");
            button3.addActionListener(e -> sendMessageHandler(e));
            panel2.add(button3, new GridConstraints(1, 1, 1, 1,
                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_FIXED,
                GridConstraints.SIZEPOLICY_CAN_SHRINK,
                null, null, null));
        }
        contentPane.add(panel2, new GridConstraints(1, 0, 1, 1,
            GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Alexandr
    private JPanel panel3;
    private JLabel label2;
    private JTextField loginTextBox;
    private JLabel label3;
    private JTextField passwordTextBox;
    private JButton button4;
    private JButton button5;
    private JPanel panel2;
    private JScrollPane scrollPane1;
    private JTextArea chatArea;
    private JTextField messageTextBox;
    private JButton button3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
