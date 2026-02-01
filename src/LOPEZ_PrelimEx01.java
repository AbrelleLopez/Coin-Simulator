import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class LOPEZ_PrelimEx01 {
    public static void main(String[] args) {

        Object[][] data = new Object[10][4];

        Random random = new Random();
        int noOfCoins = 3;
        int trials = 10;

        Runnable flip = () -> {
            for (int j = 0; j < trials; j++) {

                int headCount = 0;
                int tailCount = 0;
                StringBuilder result = new StringBuilder();

                for (int i = 0; i < noOfCoins; i++) {
                    boolean isHead = random.nextInt(2) == 0;
                    result.append(isHead? "H " : "T ");
                    if (isHead) headCount++; else tailCount++;
                }
                data[j][0] = j + 1;
                data[j][1] = result.toString();
                data[j][2] = headCount;
                data[j][3] = tailCount;
            }
        };

        JFrame frame = new JFrame("Coin Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);

        String[] columnNames = {"Trial", "Result", "No. of Heads", "No. of Tails"};
        JTable table = new JTable(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setEnabled(false);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 20, 740, 400);

        JButton button = new JButton("Flip!");
        button.setBounds(350, 380, 100, 30);
        button.setFocusable(false);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flip.run();
                table.repaint();
            }
        });

        frame.add(button);
        frame.add(scrollPane);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}