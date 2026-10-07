import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;

public class TicTacToe extends JFrame {

    private final GameButton[] cells = new GameButton[9];

    private JLabel statusLabel;
    private JLabel playerScoreLabel;
    private JLabel computerScoreLabel;
    private JLabel drawScoreLabel;

    private int playerScore = 0;
    private int computerScore = 0;
    private int drawScore = 0;

    private boolean playerTurn = true;
    private boolean gameOver = false;

    private final Color BACKGROUND = new Color(15, 18, 30);
    private final Color PANEL = new Color(24, 28, 45);
    private final Color CELL = new Color(31, 36, 57);
    private final Color CELL_HOVER = new Color(42, 48, 75);

    private final Color X_COLOR = new Color(0, 220, 255);
    private final Color O_COLOR = new Color(255, 80, 150);
    private final Color TEXT = new Color(240, 243, 250);
    private final Color MUTED = new Color(150, 158, 180);

    public TicTacToe() {

        setTitle("Tic-Tac-Toe");
        setSize(500, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        createUI();

        setVisible(true);
    }

    private void createUI() {

        JPanel main = new JPanel();
        main.setBackground(BACKGROUND);
        main.setLayout(new BorderLayout(15, 15));

        main.setBorder(
                BorderFactory.createEmptyBorder(
                        25, 30, 25, 30
                )
        );

        // =========================
        // HEADER
        // =========================

        JPanel header = new JPanel();
        header.setOpaque(false);
        header.setLayout(
                new BoxLayout(header, BoxLayout.Y_AXIS)
        );

        JLabel title = new JLabel(
                "TIC-TAC-TOE"
        );

        title.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        34
                )
        );

        title.setForeground(TEXT);

        JLabel subtitle = new JLabel(
                "Classic game • Modern style"
        );

        subtitle.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        subtitle.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        subtitle.setForeground(MUTED);

        header.add(title);
        header.add(Box.createVerticalStrut(5));
        header.add(subtitle);

        main.add(
                header,
                BorderLayout.NORTH
        );

        // =========================
        // SCORE BOARD
        // =========================

        JPanel scorePanel = new JPanel(
                new GridLayout(1, 3, 12, 0)
        );

        scorePanel.setOpaque(false);

        playerScoreLabel =
                createScoreLabel("YOU", 0, X_COLOR);

        computerScoreLabel =
                createScoreLabel("COMPUTER", 0, O_COLOR);

        drawScoreLabel =
                createScoreLabel("DRAWS", 0, Color.WHITE);

        scorePanel.add(
                createCard(
                        playerScoreLabel,
                        X_COLOR
                )
        );

        scorePanel.add(
                createCard(
                        computerScoreLabel,
                        O_COLOR
                )
        );

        scorePanel.add(
                createCard(
                        drawScoreLabel,
                        Color.WHITE
                )
        );

        // =========================
        // GAME BOARD
        // =========================

        JPanel board = new JPanel(
                new GridLayout(
                        3,
                        3,
                        10,
                        10
                )
        );

        board.setOpaque(false);

        for (int i = 0; i < 9; i++) {

            final int index = i;

            cells[i] = new GameButton();

            cells[i].addActionListener(
                    e -> playerMove(index)
            );

            board.add(cells[i]);
        }

        // =========================
        // CENTER
        // =========================

        JPanel center = new JPanel();

        center.setOpaque(false);

        center.setLayout(
                new BorderLayout(0, 20)
        );

        center.add(
                scorePanel,
                BorderLayout.NORTH
        );

        center.add(
                board,
                BorderLayout.CENTER
        );

        main.add(
                center,
                BorderLayout.CENTER
        );

        // =========================
        // BOTTOM
        // =========================

        JPanel bottom = new JPanel();

        bottom.setOpaque(false);

        bottom.setLayout(
                new BoxLayout(
                        bottom,
                        BoxLayout.Y_AXIS
                )
        );

        statusLabel = new JLabel(
                "YOUR TURN  •  X"
        );

        statusLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        statusLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        17
                )
        );

        statusLabel.setForeground(X_COLOR);

        // BUTTON PANEL

        JPanel buttonPanel = new JPanel(
                new FlowLayout(
                        FlowLayout.CENTER,
                        12,
                        0
                )
        );

        buttonPanel.setOpaque(false);

        // RESET GAME

        JButton resetGame =
                createButton(
                        "↻  RESET GAME",
                        new Color(70, 75, 100)
                );

        resetGame.addActionListener(
                e -> resetGame()
        );

        // RESET SCORE

        JButton resetScore =
                createButton(
                        "⌫  RESET SCORE",
                        new Color(150, 55, 90)
                );

        resetScore.addActionListener(
                e -> resetScore()
        );

        buttonPanel.add(resetGame);
        buttonPanel.add(resetScore);

        bottom.add(statusLabel);

        bottom.add(
                Box.createVerticalStrut(15)
        );

        bottom.add(buttonPanel);

        main.add(
                bottom,
                BorderLayout.SOUTH
        );

        add(main);
    }

    // ==========================================
    // SCORE LABEL
    // ==========================================

    private JLabel createScoreLabel(
            String title,
            int score,
            Color color) {

        JLabel label = new JLabel(
                "<html><center>" +
                        "<font size='2'>" +
                        title +
                        "</font><br>" +
                        "<font size='6'>" +
                        score +
                        "</font>" +
                        "</center></html>",
                SwingConstants.CENTER
        );

        label.setForeground(color);

        return label;
    }

    // ==========================================
    // SCORE CARD
    // ==========================================

    private JPanel createCard(
            JLabel label,
            Color color) {

        JPanel panel =
                new JPanel(
                        new BorderLayout()
                );

        panel.setBackground(PANEL);

        panel.setBorder(
                BorderFactory.createLineBorder(
                        color,
                        1
                )
        );

        panel.add(
                label,
                BorderLayout.CENTER
        );

        return panel;
    }

    // ==========================================
    // BUTTON
    // ==========================================

    private JButton createButton(
            String text,
            Color color) {

        JButton button = new JButton(text);

        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        button.setForeground(Color.WHITE);
        button.setBackground(color);

        button.setFocusPainted(false);
        button.setBorderPainted(false);

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        button.setPreferredSize(
                new Dimension(155, 45)
        );

        return button;
    }

    // ==========================================
    // PLAYER MOVE
    // ==========================================

    private void playerMove(int index) {

        if (!playerTurn || gameOver)
            return;

        if (!cells[index].getText().equals(""))
            return;

        cells[index].setText("X");

        cells[index].setForeground(
                X_COLOR
        );

        String winner = checkWinner();

        if (winner != null) {

            finishGame(winner);

            return;
        }

        playerTurn = false;

        statusLabel.setText(
                "COMPUTER IS THINKING..."
        );

        statusLabel.setForeground(
                O_COLOR
        );

        Timer timer = new Timer(
                450,
                e -> computerMove()
        );

        timer.setRepeats(false);

        timer.start();
    }

    // ==========================================
    // COMPUTER MOVE
    // ==========================================

    private void computerMove() {

        if (gameOver)
            return;

        int[] empty = new int[9];

        int count = 0;

        for (int i = 0; i < 9; i++) {

            if (cells[i].getText().equals("")) {

                empty[count] = i;

                count++;
            }
        }

        if (count == 0)
            return;

        int selected =
                empty[
                        (int)
                        (Math.random() * count)
                ];

        cells[selected].setText("O");

        cells[selected].setForeground(
                O_COLOR
        );

        String winner = checkWinner();

        if (winner != null) {

            finishGame(winner);

            return;
        }

        playerTurn = true;

        statusLabel.setText(
                "YOUR TURN  •  X"
        );

        statusLabel.setForeground(
                X_COLOR
        );
    }

    // ==========================================
    // CHECK WINNER
    // ==========================================

    private String checkWinner() {

        int[][] wins = {

                {0, 1, 2},
                {3, 4, 5},
                {6, 7, 8},

                {0, 3, 6},
                {1, 4, 7},
                {2, 5, 8},

                {0, 4, 8},
                {2, 4, 6}
        };

        for (int[] w : wins) {

            String a =
                    cells[w[0]].getText();

            String b =
                    cells[w[1]].getText();

            String c =
                    cells[w[2]].getText();

            if (!a.equals("")
                    && a.equals(b)
                    && b.equals(c)) {

                highlightWinner(w);

                return a;
            }
        }

        boolean full = true;

        for (GameButton cell : cells) {

            if (cell.getText().equals("")) {

                full = false;

                break;
            }
        }

        if (full)
            return "DRAW";

        return null;
    }

    // ==========================================
    // WINNER HIGHLIGHT
    // ==========================================

    private void highlightWinner(
            int[] positions) {

        for (int position : positions) {

            cells[position].setBackground(
                    new Color(
                            60,
                            80,
                            115
                    )
            );
        }
    }

    // ==========================================
    // FINISH GAME
    // ==========================================

    private void finishGame(
            String winner) {

        gameOver = true;

        if (winner.equals("X")) {

            playerScore++;

            updateScoreLabels();

            statusLabel.setText(
                    "🎉  YOU WIN!"
            );

            statusLabel.setForeground(
                    X_COLOR
            );

        } else if (winner.equals("O")) {

            computerScore++;

            updateScoreLabels();

            statusLabel.setText(
                    "COMPUTER WINS!"
            );

            statusLabel.setForeground(
                    O_COLOR
            );

        } else {

            drawScore++;

            updateScoreLabels();

            statusLabel.setText(
                    "IT'S A DRAW!"
            );

            statusLabel.setForeground(
                    Color.WHITE
            );
        }

        for (GameButton cell : cells) {

            cell.setEnabled(false);
        }
    }

    // ==========================================
    // UPDATE SCORES
    // ==========================================

    private void updateScoreLabels() {

        playerScoreLabel.setText(
                "<html><center>" +
                        "YOU<br><font size='6'>" +
                        playerScore +
                        "</font></center></html>"
        );

        computerScoreLabel.setText(
                "<html><center>" +
                        "COMPUTER<br><font size='6'>" +
                        computerScore +
                        "</font></center></html>"
        );

        drawScoreLabel.setText(
                "<html><center>" +
                        "DRAWS<br><font size='6'>" +
                        drawScore +
                        "</font></center></html>"
        );
    }

    // ==========================================
    // RESET GAME
    // ==========================================

    private void resetGame() {

        for (GameButton cell : cells) {

            cell.setText("");

            cell.setEnabled(true);

            cell.setBackground(CELL);
        }

        playerTurn = true;

        gameOver = false;

        statusLabel.setText(
                "YOUR TURN  •  X"
        );

        statusLabel.setForeground(
                X_COLOR
        );
    }

    // ==========================================
    // RESET SCORE
    // ==========================================

    private void resetScore() {

        int answer = JOptionPane.showConfirmDialog(
                this,
                "Reset all scores?",
                "Reset Score",
                JOptionPane.YES_NO_OPTION
        );

        if (answer == JOptionPane.YES_OPTION) {

            playerScore = 0;

            computerScore = 0;

            drawScore = 0;

            updateScoreLabels();

            resetGame();
        }
    }

    // ==========================================
    // CUSTOM BUTTON
    // ==========================================

    class GameButton extends JButton {

        private boolean hover = false;

        public GameButton() {

            setFont(
                    new Font(
                            "Arial",
                            Font.BOLD,
                            55
                    )
            );

            setBackground(CELL);

            setFocusPainted(false);

            setBorderPainted(false);

            setCursor(
                    new Cursor(
                            Cursor.HAND_CURSOR
                    )
            );

            addMouseListener(
                    new MouseAdapter() {

                        @Override
                        public void mouseEntered(
                                MouseEvent e) {

                            hover = true;

                            if (getText().equals("")) {

                                setBackground(
                                        CELL_HOVER
                                );
                            }
                        }

                        @Override
                        public void mouseExited(
                                MouseEvent e) {

                            hover = false;

                            if (getText().equals("")) {

                                setBackground(
                                        CELL
                                );
                            }
                        }
                    }
            );
        }

        @Override
        protected void paintComponent(
                Graphics g) {

            Graphics2D g2 =
                    (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            g2.setColor(
                    hover && getText().equals("")
                            ? CELL_HOVER
                            : getBackground()
            );

            g2.fill(
                    new RoundRectangle2D.Float(
                            0,
                            0,
                            getWidth(),
                            getHeight(),
                            22,
                            22
                    )
            );

            super.paintComponent(g2);

            g2.dispose();
        }
    }

    // ==========================================
    // MAIN
    // ==========================================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
                TicTacToe::new
        );
    }
}