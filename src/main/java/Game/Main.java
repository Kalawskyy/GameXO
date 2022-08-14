package Game;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
       try{
           SwingUtilities.invokeLater(Field::new);
    }catch (Exception ex){
           ex.printStackTrace();
       }

    }
}
