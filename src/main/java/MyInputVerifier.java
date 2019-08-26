import javax.swing.*;
import java.awt.*;

public class MyInputVerifier extends InputVerifier {

    @Override
    public boolean verify(JComponent input) {
        String text = ((JTextField) input).getText();
        try {
            Double.parseDouble(text);
            return true;
        } catch (NumberFormatException e) {
            ((JTextField) input).setBackground(Color.PINK);
            ((JTextField) input).setText("only number value allowed");
            return false;
        }
    }
}