import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class MatchCards {
    class Card{
            String cardName ;
            ImageIcon cardImageIcon;
            Card(String cardName,ImageIcon cardImageIcon){
                this.cardImageIcon = cardImageIcon;
                this.cardName = cardName;
            }
            public String toString(){
                return cardName;
            }
    }

    String [] cardList ={
            "darkness",
            "double",
            "fairy",
            "fighting",
            "fire",
            "grass",
            "lightning",
            "metal",
            "psychic",
            "water"
    };

    int rows = 4;
    int columns = 5;
    int cardWidth = 90;
    int cardHeight = 128;

    ArrayList<Card> cardSet;
    ImageIcon cardBackImageIcon;

    int boardWidth = cardWidth * columns; // 5 * 128 = 640
    int boardHeight = cardHeight * rows; // 4 * 90 = 360

    JFrame frame = new JFrame("Pokemon Match Cards");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();
    JPanel restartGamePanel = new JPanel();
    JButton restartButton = new JButton();
     int errorCounter = 0;

     ArrayList<JButton> board;

    MatchCards(){
        setupCards();
        shuffleCards();
//        frame.setVisible(true);
        frame.setSize(boardWidth,boardHeight);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setIconImage(new ImageIcon("D:\\GitHub\\My_Projects\\CardMemoryGame\\src\\img\\back.jpg").getImage());

        textLabel.setFont(new Font("Arial",Font.PLAIN,20));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Errors: "+ errorCounter);

        textPanel.setPreferredSize(new Dimension(boardWidth,30));
        textPanel.add(textLabel);

        frame.add(textPanel,BorderLayout.NORTH);

        // card game board
        board = new ArrayList<JButton>();

        boardPanel.setLayout(new GridLayout(rows,columns));

        for(int i = 0;i < cardSet.size();i++){
            JButton tile  = new JButton();
            tile.setPreferredSize(new Dimension(cardWidth,cardHeight));
            tile.setOpaque(true);
            tile.setIcon(cardSet.get(i).cardImageIcon);
            tile.setFocusable(false);
            board.add(tile);
            boardPanel.add(tile);
        }

        frame.add(boardPanel);

        restartButton.setFont(new Font("Arial",Font.PLAIN,20));
        restartButton.setText("Restart Game");
        restartButton.setPreferredSize(new Dimension(boardWidth,30));
        restartButton.setFocusable(false);

        restartGamePanel.add(restartButton);

        frame.add(restartGamePanel,BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);

    }

    void setupCards(){
        cardSet = new ArrayList<>();
        for(String cardName : cardList){
            // load card image
            Image cardImg = new ImageIcon(Objects.requireNonNull(getClass().getResource("./img/" + cardName + ".jpg"))).getImage();
            ImageIcon cardImageIcon = new ImageIcon(cardImg.getScaledInstance(cardWidth,cardHeight, Image.SCALE_SMOOTH));

            // create card object and add to set

            Card card = new Card(cardName,cardImageIcon);
            cardSet.add(card);
        }
        cardSet.addAll(cardSet);
        Image cardBackImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("./img/back.jpg"))).getImage();
        cardBackImageIcon = new ImageIcon(cardBackImage.getScaledInstance(cardWidth,cardHeight, Image.SCALE_SMOOTH));

    }
    void shuffleCards(){
        System.out.println(cardSet);
        // shuffle
        for(int i = 0;i<cardSet.size();i++){
            /// get random index
            int j = (int) (Math.random() * cardSet.size());
            Card temp = cardSet.get(i);
            cardSet.set(i,cardSet.get(j));
            cardSet.set(j,temp);
        }
        System.out.println(cardSet);
    }
}
