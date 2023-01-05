package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import model.Game;
import model.Coords;

public class GameView extends JPanel {
    Game game;
    PlateauView plateauView;
    PiocheView pioche;
    JPanel container;
    JLabel tour;
    ScoreboardView scoreboard;

    public GameView(Game game) {
        this.game = game;
        tour = new JLabel("C'est au tour de: " + game.peekPlayer().getName());
        plateauView = new PlateauView(this, game.plateau);

        container = new JPanel();
        scoreboard = new ScoreboardView(game.getPlayers());
        container.setLayout(new GridBagLayout());
        pioche = new PiocheView(game.sac);
        JButton defausse = new JButton("Défausser");
        JButton rotate = new JButton("Rotate");

        defausse.addActionListener((ActionEvent e) -> {
            defausser();
        });

        rotate.addActionListener((ActionEvent e) -> {
            rotate();
        });

        setLayout(new BorderLayout());

        add(plateauView, BorderLayout.CENTER);
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;
        gc.weightx = 2;
        container.add(tour, gc);

        gc.gridy = 1;
        gc.weightx = 1;
        gc.weighty = 2;

        container.add(pioche, gc);
        gc.gridx = 1;
        gc.weighty = 1;
        container.add(defausse, gc);
        gc.gridy = 2;
        container.add(rotate, gc);

        gc.gridx = 0;
        gc.gridy = 3;
        gc.weightx = 2;
        gc.weighty = 3;

        container.add(scoreboard, gc);
        add(container, BorderLayout.LINE_END);
    }

    public Game getGame() {
        return game;
    }

    public void defausser() {
        game.defausser(); // Ok
        plateauView.update();
        pioche.update();
        tour.setText("C'est au tour de: " + game.peekPlayer().getName());
        scoreboard.update();
    }

    public void rotate() {
        game.rotatePioche();
        plateauView.update();
        pioche.update();
        tour.setText("C'est au tour de: " + game.peekPlayer().getName());
        scoreboard.update();
    }

    public void place(Coords c) {
        game.place(c);
        plateauView.update();
        pioche.update();
        tour.setText("C'est au tour de: " + game.peekPlayer().getName());
        scoreboard.update();
        game.setLastTuile(game.getPlateau().getTuile(c));
    }
}
