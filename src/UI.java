import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionListener;

/**
 * Created by cgev on 11/10/16.
 */
public class UI extends JFrame{
    private JLabel textLbl = new JLabel("Text");
    private JLabel KeyLbl = new JLabel("Key");
    private JTextField text = new JTextField();
    private JTextField key = new JTextField();
    private JComboBox comboBox = new JComboBox();
    private JTextArea resultTextArea = new JTextArea();
    private JButton button = new JButton("START!!!");
    private JPanel mainPanel = new JPanel();

    public UI() {
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

        JPanel panelLbl = new JPanel();
        panelLbl.setLayout(new BoxLayout(panelLbl, BoxLayout.X_AXIS));
        panelLbl.add(textLbl);
        panelLbl.add(text);

        JPanel panelKeyLbl = new JPanel();
        panelKeyLbl.setLayout(new BoxLayout(panelKeyLbl, BoxLayout.X_AXIS));
        panelKeyLbl.add(KeyLbl);
        panelKeyLbl.add(key);


        JPanel panelText = new JPanel();
        panelText.setLayout(new BoxLayout(panelText,BoxLayout.Y_AXIS));
        panelText.add(panelKeyLbl);
        panelText.add(panelLbl);

        JScrollPane scroll = new JScrollPane(resultTextArea);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setMaximumSize(new Dimension(200,200));
        panelText.add(scroll);
        text.setMaximumSize(new Dimension(100,20));
        text.setMinimumSize(new Dimension(100,20));
        key.setMaximumSize(new Dimension(100,20));
        key.setMinimumSize(new Dimension(100,20));
        resultTextArea.setMaximumSize(new Dimension(200,200));
        resultTextArea.setMinimumSize(new Dimension(200,200));


        JPanel pannelBtn = new JPanel();
        pannelBtn.setLayout(new BoxLayout(pannelBtn,BoxLayout.Y_AXIS));
        pannelBtn.add(comboBox);
        comboBox.setMaximumSize(new Dimension(100,20));
        pannelBtn.add(button);
        text.setMaximumSize(new Dimension(100,20));
        comboBox.addItem("Encrypt");
        comboBox.addItem("Decrypt");


        mainPanel.add(panelLbl);
        mainPanel.add(panelText);
        mainPanel.add(pannelBtn);
        add(mainPanel);
        pack();
        setSize(500,500);
        setVisible(true);


        button.addActionListener(e -> {

            resultTextArea.setText("");
            if(comboBox.getSelectedItem() == comboBox.getItemAt(0)){
               String textStr = text.getText();
                int c = 8 - textStr.length() % 8 ;
                StringBuilder str = new StringBuilder(textStr);
                for(int i = 0 ; i < c; ++i)
                {
                    str.append(' ');
                }
                textStr = str.toString();
                for(int i = 0 ; i < textStr.length()/8; ++i) {
                    String[] arr = MainClass.encrypt(textStr.substring(i*8,i*8+8), key.getText().substring(0, 7));
                    for (String a : arr) {
                        resultTextArea.append(a);
                    }
                }
            }
            else {
                String textStr = text.getText();
                for (int j = 0; j < textStr.length() / 64; ++j) {
                    String[] str = new String[8];
                    for (int i = 0; i < 8; ++i) {
                        str[i] = text.getText().substring(j*64,j*64+64).substring(i * 8, i * 8 + 8);
                    }
                    String arr = MainClass.decode(str, key.getText());
                    StringBuilder strB = new StringBuilder(arr);
                    while(' ' == strB.charAt(strB.length()-1)){
                        strB.deleteCharAt(strB.length()-1);
                    }
                    arr = strB.toString();
                    resultTextArea.append(arr);

                }
            }
        });
    }

    static public void main(String[] args) {
        UI ui = new UI();

        //String[] s = encrypt("1234sd78","1234567");
        //String text = decode(s,"1234567");
        //System.out.println(text);
    }
}
