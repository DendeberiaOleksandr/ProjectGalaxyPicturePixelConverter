package com.dendeberia;

import javax.swing.*;
import java.awt.*;

public class ColorPanel extends JPanel {
    private ColorService.ColorsSet colorsSet;
    private Color chosenColor = new Color(204, 204, 204);

    @Override
    public void paint(Graphics g) {

        super.paint(g);

        if(colorsSet == null){
            return;
        }

        switch (colorsSet){
            case PALETTE:
                int[][] colors = ColorService.COLORS_PALETTE;

                for(int i = 0; i < 16; i++){

                    g.setColor(new Color(colors[i][0], colors[i][1], colors[i][2]));
                    g.fillRect(i * 16 + 10, 10, 14, 14);

                    if(chosenColor.equals(new Color(colors[i][0], colors[i][1], colors[i][2]))){
                        g.setColor(Color.DARK_GRAY);
                        g.drawRect(i * 16 + 8, 8, 18, 18);
                    }
                }

                for(int i = 0; i < 16; i++){
                    g.setColor(new Color(colors[i + 16][0], colors[i + 16][1], colors[i + 16][2]));
                    g.fillRect(i * 16 + 10, 28, 14, 14);

                    if(chosenColor.equals(new Color(colors[i + 16][0], colors[i + 16][1], colors[i + 16][2]))){
                        g.setColor(Color.darkGray);
                        g.drawRect(i * 16 + 8, 26, 18, 18);
                    }
                }

                for(int i = 0; i < 16; i++){
                    g.setColor(new Color(colors[i + 32][0], colors[i + 32][1], colors[i + 32][2], 50));
                    g.fillRect(i * 16 + 10, 46, 14, 14);

                    if(chosenColor.equals(new Color(colors[i + 32][0], colors[i + 32][1], colors[i + 32][2]))){
                        g.setColor(Color.darkGray);
                        g.drawRect(i * 16 + 8, 44, 18, 18);
                    }
                }


                break;

            case BRUSH:

                int[][] colorsBrush = ColorService.COLORS_BRUSH;

                for(int i = 0; i < 16; i++){

                    g.setColor(new Color(colorsBrush[i][0], colorsBrush[i][1], colorsBrush[i][2]));
                    g.fillRect(i * 16 + 10, 10, 14, 14);

                    if(chosenColor.equals(new Color(colorsBrush[i][0], colorsBrush[i][1], colorsBrush[i][2]))){
                        g.setColor(Color.DARK_GRAY);
                        g.drawRect(i * 16 + 8, 8, 18, 18);

                    }
                }

                break;

        }
    }

    public void setColorsSet(ColorService.ColorsSet colorsSet) {
        this.colorsSet = colorsSet;
    }

    public void setChosenColor(Color chosenColor) {
        this.chosenColor = chosenColor;
    }
}
