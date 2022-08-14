package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Field extends JFrame implements ActionListener {
    public JButton[] buttons = new JButton[9];
    JPanel game = new JPanel();
    //Do tej tablicy wprowadzamy dane dotyczące pól .Wprowadzamy liczby 0 - NULL,1 - X,2 - O
    int[] areaInfo = new int[9];
    int player = 1; // 1 - X  2 - O
    int nrCliks = 0;
    Dimension but = new Dimension(42,40 );
    JLabel text, currentPlayer;
    JButton buttonReset;

    Field() {
        this.getContentPane().add(game);
        this.setResizable(false);
        this.setBounds(760, 340,400,400);
        this.setVisible(true);
        game.setVisible(true);
        game.setBackground(Color.gray);
        game.setBounds(200,200,150,130);
        this.setTitle("Kółko i krzyżyk");
        this.setDefaultCloseOperation(3);
        for (int i = 0; i < 9; i++) {
            areaInfo[i] = 0;
            buildButton(i, but);
        }
        createArea();
    }

    void createArea() {
        GroupLayout layout = new GroupLayout(game);
        game.setLayout(layout);
        game.setLocation(100,100);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup().addComponent(buttons[0]).addComponent(buttons[3]).addComponent(buttons[6])).addGroup(layout.createParallelGroup().addComponent(buttons[1]).addComponent(buttons[4]).addComponent(buttons[7])).addGroup(layout.createParallelGroup().addComponent(buttons[2]).addComponent(buttons[5]).addComponent(buttons[8]))
                .addContainerGap(80, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup().addComponent(buttons[0]).addComponent(buttons[1]).addComponent(buttons[2])).addGroup(layout.createParallelGroup().addComponent(buttons[3]).addComponent(buttons[4]).addComponent(buttons[5])).addGroup(layout.createParallelGroup().addComponent(buttons[6]).addComponent(buttons[7]).addComponent(buttons[8]))
                .addContainerGap(80, Short.MAX_VALUE)

        );

        text = new JLabel();
        text.setSize(200, 40);
        text.setLocation(100, 250);
        game.add(text);
        currentPlayer = new JLabel("Gracz nr 1 ma ruch");
        currentPlayer.setSize(200, 40);
        currentPlayer.setLocation(200, 40);
        game.add(currentPlayer);
        buttonReset = new JButton("Reset");
        game.add(buttonReset);
        buttonReset.setActionCommand("Reset");
        buttonReset.addActionListener(this);
        buttonReset.setVisible(false);
        buttonReset.setSize(new Dimension(70,30));
        buttonReset.setLocation(300, 300);
    }

    void buildButton(int id, Dimension size) {
        buttons[id] = new JButton("");
        buttons[id].setMinimumSize(size);
        buttons[id].setPreferredSize(size);
        buttons[id].setVisible(true);
        buttons[id].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (player == 1) {
                    buttons[id].setText("X");
                    buttons[id].setEnabled(false);
                    areaInfo[id] = 1;
                    checkArea();
                    player = 2;
                } else {
                    buttons[id].setText("O");
                    buttons[id].setEnabled(false);
                    areaInfo[id] = 2;
                    checkArea();
                    player = 1;
                }
                currentPlayer.setText("Gracz nr " + player + " ma ruch");
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if ("Reset".equals(cmd)) {
            resetGame();
        }
    }

    void checkArea() {
        nrCliks = nrCliks + 1;
        for (int i = 0; i < 3; i++) {
            if (((areaInfo[i * 3] == 1) && (areaInfo[1 + (i * 3)] == 1) && (areaInfo[2 + (i * 3)] == 1)) || ((areaInfo[i * 3] == 2) && (areaInfo[1 + (i * 3)] == 2) && (areaInfo[2 + (i * 3)] == 2))) {
                text.setText("Wygrałeś poziomo 1");
                turnOfButtons();
                buttonReset.setVisible(true);
                break;
            } else if (((areaInfo[i] == 1) && (areaInfo[i + 3] == 1) && (areaInfo[i + 6] == 1)) || ((areaInfo[i] == 2) && (areaInfo[i + 3] == 2) && (areaInfo[i + 6] == 2))) {
                text.setText("Wygrałeś pionowo 2");
                turnOfButtons();
                buttonReset.setVisible(true);
                break;
            } else if (((areaInfo[0] == 1) && (areaInfo[4] == 1) && (areaInfo[8] == 1)) || ((areaInfo[0] == 2) && (areaInfo[4] == 2) && (areaInfo[8] == 2))) {
                text.setText("Wygrałeś po przekątenej 1");
                turnOfButtons();
                buttonReset.setVisible(true);
                break;
            } else if (((areaInfo[2] == 1) && (areaInfo[4] == 1) && (areaInfo[6] == 1)) || ((areaInfo[2] == 2) && (areaInfo[4] == 2) && (areaInfo[6] == 2))) {
                text.setText("Wygrałeś po przekątenej 2");
                turnOfButtons();
                buttonReset.setVisible(true);
                break;
            } else if (nrCliks == 9) {
                buttonReset.setVisible(true);
                text.setText("Remis");
                break;
            }
        }

    }
    void turnOfButtons() {
        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }
    }

    void resetGame() {
        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(true);
            buttons[i].setText("  ");
            areaInfo[i] = 0;
        }
        player = 1;
        nrCliks = 0;
        currentPlayer.setText("Gracz nr " + player + " ma ruch");
        text.setText("");
        buttonReset.setVisible(false);
    }

}


