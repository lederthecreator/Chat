/*
 * Created by JFormDesigner on Tue Apr 18 18:02:04 MSK 2023
 */
package ru.leder.gui

import com.google.gson.Gson
import com.intellij.uiDesigner.core.GridConstraints
import com.intellij.uiDesigner.core.GridLayoutManager
import ru.leder.gui.dto.Dto
import ru.leder.gui.enums.MessageType
import java.awt.*
import java.awt.event.ActionEvent
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import javax.swing.*
import javax.swing.border.CompoundBorder
import javax.swing.border.EmptyBorder
import javax.swing.border.TitledBorder
import javax.swing.text.*


/**
 * @author User
 */
class MainWindow(val callback: (Dto) -> Unit) : JFrame() {
    fun signUpReceiver(login: String) {
        JOptionPane.showMessageDialog(this,
            "Вы успешно зарегистрировались. Добро пожаловать, $login. Снова.")

        passwordTextBox?.isEnabled = false
        loginTextBox?.text = login

        messageTextBox?.isEnabled = true
        messageTextBox?.isEditable = true
        sendMessageButton?.isEnabled = true
    }

    fun loginReceiver(login: String) {
        JOptionPane.showMessageDialog(this,
            "Вы успешно вошли. Добро пожаловать, $login. Снова.")

        passwordTextBox?.isEnabled = false
        loginTextBox?.text = login

        messageTextBox?.isEnabled = true
        messageTextBox?.isEditable = true
        sendMessageButton?.isEnabled = true
    }

    fun messageReceiver(type: MessageType, data: String) {
        val color = when (type) {
            MessageType.SystemMessage -> {
                Color.blue
            }
            MessageType.UserMessage -> {
                Color.black
            }
            MessageType.ErrorMessage -> {
                Color.red
            }
        }

        appendToPane(chatArea!!, "$data\n", color)
    }

    fun errorReceiver(operation: String, data: String) {
        JOptionPane.showMessageDialog(this,
            data)
    }

    private fun appendToPane(tp: JTextPane, msg: String, c: Color) {
        val sc = StyleContext.getDefaultStyleContext()
        var aset: AttributeSet? = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c)

        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Jetbrains Mono")
        aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED)

        val len = tp.document.length
        val doc = tp.styledDocument
        tp.caretPosition = len
        tp.setCharacterAttributes(aset, false)
        doc.setCharacterAttributes(len, msg.length, aset, false)
        doc.insertString(doc.length, msg, aset)
    }

    private fun signUpHandler(e: ActionEvent) {
        val dto = Dto(
            operation = "SIGNUP",
            data = object {
                val login = loginTextBox?.text?.trim()
                val password = passwordTextBox?.text?.trim()
            }
        )

        callback(dto)
    }

    private fun loginHandler(e: ActionEvent) {
        val dto = Dto(
            operation = "LOGIN",
            data = object {
                val login = loginTextBox?.text?.trim()
                val password = passwordTextBox?.text?.trim()
            }
        )

        callback(dto)
    }

    private fun sendMessageHandler(e: ActionEvent?) {
        val dto = Dto(
            operation = "MESSAGE",
            data = messageTextBox?.text
        )

        messageTextBox?.text = ""
        callback(dto)
    }

    private fun sendMessageByEnterHandler(e: KeyEvent) {

    }

    private fun initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Alexandr
    panel3 = JPanel()
        label2 = JLabel()
        loginTextBox = JTextField()
        label3 = JLabel()
        passwordTextBox = JPasswordField()
        button4 = JButton()
        button5 = JButton()
        panel2 = JPanel()
        scrollPane1 = JScrollPane()
        chatArea = JTextPane()
        messageTextBox = JTextField()
        sendMessageButton = JButton()
        
        //======== this ========
        title = "Chat"
        minimumSize = Dimension(310, 510)
        maximumSize = Dimension(310, 510)
        defaultCloseOperation = EXIT_ON_CLOSE
         val contentPane = contentPane
        contentPane.layout = GridLayoutManager(2, 1, Insets(0, 0, 0, 0), -1, -1)
        
        //======== panel3 ========
        run{
        panel3!!.minimumSize = Dimension(291, 100)
        panel3!!.border = CompoundBorder(
        TitledBorder(
            EmptyBorder(0, 0, 0, 0),
            "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn",
            TitledBorder.CENTER,
            TitledBorder.BOTTOM,
            Font(
                "Dia\u006cog", Font.BOLD, 12
            ),
            Color.red
        ), panel3!!.border
    )
        panel3!!.addPropertyChangeListener{ e -> if ("\u0062ord\u0065r" == e.propertyName)throw RuntimeException() }
        panel3!!.layout = GridLayoutManager(3, 3, Insets(0, 0, 0, 0), -1, -1)
        
            //---- label2 ----
        label2!!.text = "Login"
        panel3!!.add(label2, GridConstraints(0, 0, 1, 1, 
        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, 
        GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW, 
        GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW, 
        null, null, null))
        panel3!!.add(loginTextBox, GridConstraints(0, 1, 1, 2, 
        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, 
        GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW, 
        GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW, 
        null, null, null))
        
            //---- label3 ----
        label3!!.text = "Password"
        panel3!!.add(label3, GridConstraints(1, 0, 1, 1, 
        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, 
        GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW, 
        GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW, 
        null, null, null))
        panel3!!.add(passwordTextBox, GridConstraints(1, 1, 1, 2, 
        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, 
        GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW, 
        GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW, 
        null, null, null))
        
            //---- button4 ----
        button4!!.text = "SignUp"
        button4!!.addActionListener{e:ActionEvent -> signUpHandler(e)}
        panel3!!.add(button4, GridConstraints(2, 1, 1, 1, 
        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, 
        GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW, 
        GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW, 
        null, null, null))
        
            //---- button5 ----
        button5!!.text = "Login"
        button5!!.addActionListener{e:ActionEvent -> loginHandler(e)}
        panel3!!.add(button5, GridConstraints(2, 2, 1, 1, 
        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, 
        GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW, 
        GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW, 
        null, null, null))
        }
        contentPane.add(panel3, GridConstraints(0, 0, 1, 1, 
        GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, 
        GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW, 
        GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW, 
        null, null, null))
        
        //======== panel2 ========
        run{
        panel2!!.layout = GridLayoutManager(2, 2, Insets(0, 0, 0, 0), -1, -1)
        
            //======== scrollPane1 ========
        run{
        

                //---- chatArea ----
        chatArea!!.font = Font("JetBrains Mono", Font.PLAIN, 16)
        chatArea!!.isEditable = false
        chatArea!!.cursor = Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR)
        scrollPane1!!.setViewportView(chatArea)
        }
        panel2!!.add(scrollPane1, GridConstraints(0, 0, 1, 2, 
        GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_BOTH, 
        GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW or GridConstraints.SIZEPOLICY_WANT_GROW, 
        GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW or GridConstraints.SIZEPOLICY_WANT_GROW, 
        Dimension(0, 300), null, null))
        panel2!!.add(messageTextBox, GridConstraints(1, 0, 1, 1, 
        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, 
        GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW, 
        GridConstraints.SIZEPOLICY_CAN_SHRINK, 
        null, null, null))

        messageTextBox?.addKeyListener(
            object : KeyListener {
                override fun keyTyped(e: KeyEvent?) { }

                override fun keyPressed(e: KeyEvent?) { }

                override fun keyReleased(e: KeyEvent?) {
                    if (e == null) {
                        return
                    }

                    if (e.keyCode == KeyEvent.VK_ENTER) {
                        sendMessageHandler(null)
                    }
                }
            }
        )

            //---- button3 ----
        sendMessageButton!!.text = "Send"
        sendMessageButton!!.addActionListener{e:ActionEvent -> sendMessageHandler(e)}
        panel2!!.add(sendMessageButton, GridConstraints(1, 1, 1, 1,
        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, 
        GridConstraints.SIZEPOLICY_FIXED, 
        GridConstraints.SIZEPOLICY_CAN_SHRINK, 
        null, null, null))
        }
        contentPane.add(panel2, GridConstraints(1, 0, 1, 1, 
        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, 
        GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW, 
        GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW, 
        null, null, null))
        pack()
        setLocationRelativeTo(owner)
            // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Alexandr
    private var panel3:JPanel? = null
    private var label2:JLabel? = null
    private var loginTextBox:JTextField? = null
    private var label3:JLabel? = null
    private var passwordTextBox:JPasswordField? = null
    private var button4:JButton? = null
    private var button5:JButton? = null
    private var panel2:JPanel? = null
    private var scrollPane1:JScrollPane? = null
    private var chatArea:JTextPane? = null
    private var messageTextBox:JTextField? = null
    private var sendMessageButton: JButton? =
        null // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    init {
        initComponents()
    }
}
