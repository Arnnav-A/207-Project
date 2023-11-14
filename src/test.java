import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;



import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


class ComboBoxExample extends JFrame {

    public ComboBoxExample() {
        setTitle("下拉选取框示例");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        // 创建一个面板来容纳组件
        JPanel panel = new JPanel();

        // 创建一个下拉选取框
        String[] options = {"please choose a filter", "选项1", "选项2", "选项3"};
        String[] options1 = {"选项4", "选项5", "选项6"};
        String[] options2 = {"选项7", "选项8", "选项9"};
        String[] options3 = {"选项10", "选项11", "选项12"};
        JComboBox<String> comboBox = new JComboBox<>(options);
        JComboBox<String> comboBox1 = new JComboBox<>(options1);
        JComboBox<String> comboBox2 = new JComboBox<>(options2);
        JComboBox<String> comboBox3 = new JComboBox<>(options3);

        // 创建一个标签用于显示选择的选项
        JLabel selectionLabel = new JLabel("选择的选项：");

        // 创建一个按钮，当点击时获取选中的选项并显示在标签上
        JButton submitButton = new JButton("获取选项");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) comboBox.getSelectedItem();
                selectionLabel.setText("选择的选项：" + selectedOption);
            }
        });

        // 将组件添加到面板
        panel.add(comboBox);
        panel.add(comboBox1);
        panel.add(comboBox2);
        panel.add(comboBox3);
        panel.add(submitButton);
        panel.add(selectionLabel);

        // 将面板添加到框架
        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ComboBoxExample frame = new ComboBoxExample();
                frame.setVisible(true);
            }
        });
    }
}



class MultipleFramesExample {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
    }

    private static void createAndShowGUI() {
        JFrame frame1 = createFrame("Window 1");
        JFrame frame2 = createFrame("Window 2");

        JButton closeFrame1Button = new JButton("Close Window 1");
        closeFrame1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame1.dispose(); // Close Window 1
            }
        });

        JButton closeFrame2Button = new JButton("Close Window 2");
        closeFrame2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame2.dispose(); // Close Window 2
            }
        });

        JPanel panel = new JPanel();
        panel.add(closeFrame1Button);
        panel.add(closeFrame2Button);

        JFrame mainFrame = createFrame("Main Window");
        mainFrame.getContentPane().add(panel);
    }

    private static JFrame createFrame(String title) {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        return frame;
    }
}




class ComboBoxChangeTrackingExample {

    public static void main(String[] args) {
        JFrame frame = new JFrame("ComboBox Change Tracking Example");
        frame.setLayout(new FlowLayout());

        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.addItem("Item 1");
        comboBox.addItem("Item 2");
        comboBox.addItem("Item 3");

        JLabel label = new JLabel("Selected Item: ");

        // Add an ItemListener to track changes using item state events
        comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                // Ensure the event source is our JComboBox
                if (e.getSource() == comboBox) {
                    // Check if the item state changed to SELECTED
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        // Retrieve the selected item
                        String selectedItem = (String) comboBox.getSelectedItem();
                        label.setText("Selected Item: " + selectedItem);
                    }
                }
            }
        });

        frame.add(comboBox);
        frame.add(label);

        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
