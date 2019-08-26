import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUICalc2 {

    // method create labels
    public static JLabel[] CreateLabels() {
        JLabel[] labels = new JLabel[LabelsNames.values().length];
        int i = 0;
        for (LabelsNames s : LabelsNames.values()) {
            labels[i] = new JLabel(s.GetMyLabel());
            // add empty border to set margin for all labels
            Border border = labels[i].getBorder();
            Border margin = new EmptyBorder(1,5,1,1);
            labels[i].setBorder(new CompoundBorder(border, margin));
            i++;
        }
        return labels;
    }

    // method create text fields with default value
    public static JTextField[] CreateTextFields() {
        JTextField[] textFields = new JTextField[LabelsNames.values().length];
        int i = 0;
        for (LabelsNames k : LabelsNames.values()) {
            // create textfield
            textFields[i] = new JTextField();
            // set horizontal text alignment to RIGHT
            textFields[i].setHorizontalAlignment(JTextField.RIGHT);
            // get default value for text field (defined in enum)
            textFields[i].setText(String.valueOf(k.GetDefaultText()));
            // set input verified for each text fields
            textFields[i].setInputVerifier(new MyInputVerifier());
            // set font
            textFields[i].setFont(new Font("Arial", Font.BOLD,12));
            i++;
        }
        return textFields;
    }

    public static Double[] getTextFieldValue (JTextField[] myTextFields) {
        Double[] myValues = new Double[myTextFields.length];

        for(int i = 0; i < myTextFields.length; i++) {
            myValues[i] = Double.parseDouble(myTextFields[i].getText());
            System.out.println(myValues[i]);
        }
        return myValues;
    }

    public static void GenerateGui () {
        // add frame
        JFrame frame = new JFrame();
        frame.setLayout(new GridLayout(2,1));

        // generate labels
        JLabel[] labels = CreateLabels();
        // generate text fields with default values
        JTextField[] textFields = CreateTextFields();

        // add two panels
        JPanel panel = new JPanel(new GridLayout(labels.length,2));
        JPanel panel2 = new JPanel(new GridLayout(1,2));
        panel.setPreferredSize(new Dimension(400,400));
        panel.setBackground(Color.WHITE);

        // add start button
        JButton button = new JButton("Start");
        button.setPreferredSize(new Dimension(200,200));
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // add text area for calculation results
        JTextArea resultsDisplay = new JTextArea(10,10);
        // add line wrap = true
        resultsDisplay.setLineWrap(true);
        // line wraper will focus on words instead of characters
        resultsDisplay.setWrapStyleWord(true);
        // add borders
        resultsDisplay.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        // add prefered size
        resultsDisplay.setPreferredSize(new Dimension(200,200));

        // add action lister for button
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // initialize variable for outpu
                String outPut = "";
                // get output
                outPut = Calculation.calculate_output(labels, textFields);
                // display output
                resultsDisplay.setText(outPut);
            }
        });

        // ADD scrolling to text area --> does not work
        JScrollPane scrollPane = new JScrollPane(resultsDisplay);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setSize(100,100);

        // add text area and button to second panel
        panel2.add(resultsDisplay);
        panel2.add(button);

        // add labels and text fields
        // pair labels and text fields
        for (int i = 0; i < labels.length; i++) {
            panel.add(labels[i]);
            // make label + text field pair
            labels[i].setLabelFor(textFields[i]);
            panel.add(textFields[i]);
        }

        // add panels 1 and 2
        frame.add(panel);
        frame.add(panel2);
        // set prefered size of main frame
        frame.setPreferredSize(new Dimension(450,800));
        // set what to do when click X
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // pack all main frame componets to fitt componets content
        frame.pack();
        // frame is invisible by default
        frame.setVisible(true);

    }


    public static void main (String[] arguments) {
        GenerateGui();
    }


}
