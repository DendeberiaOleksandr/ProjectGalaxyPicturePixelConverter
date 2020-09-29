package com.dendeberia;

import java.awt.*;
import java.awt.color.ColorSpace;

public class ColorService {

    //Array that represents the available brush color values
    public static final int[][] COLORS_BRUSH = {
            {204, 204, 204},
            {0, 0, 0},
            {68, 68, 68},
            {0, 0, 136},
            {68, 68, 204},
            {0, 136, 0},
            {68, 204, 68},
            {0, 136, 136},
            {68, 204, 204},
            {136, 0, 0},
            {204, 68, 68},
            {136, 0, 136},
            {204, 68, 204},
            {137, 68, 0},
            {206, 202, 68},
            {136, 136, 136}
    };

    public static final int[][] COLORS_PALETTE = {
            {204, 204, 204},
            {0, 0, 0},
            {68, 68, 68},
            {0, 0, 136},
            {68, 68, 204},
            {0, 139, 0},
            {69, 204, 68},
            {0, 136, 136},
            {68, 204, 204},
            {0, 136, 136},
            {68, 207, 207},
            {136, 0, 0},
            {204, 68, 68},
            {136, 0, 136},
            {204, 68, 204},
            {136, 136, 136},
            {7, 127, 188},
            {2, 103, 159},
            {7, 66, 134},
            {0, 46, 144},
            {4, 19, 76},
            {0, 78, 46},
            {2, 101, 54},
            {1, 49, 37},
            {0, 44, 45},
            {119, 1, 17},
            {184, 10, 11},
            {159, 3, 33},
            {106, 14, 29},
            {120, 20, 58},
            {172, 49, 70},
            {169, 9, 99},
            {204, 19, 112},
            {197, 31, 129},
            {167, 117, 126},
            {174, 117, 108},
            {194, 140, 163},
            {192, 187, 105},
            {249, 235, 2},
            {202, 163, 0},
            {170, 139, 31},
            {143, 97, 3},
            {201, 68, 3},
            {180, 47, 4},
            {139, 36, 5},
            {175, 160, 117},
            {147, 155, 116},
            {150, 139, 111}
    };

    public static Color getMoreSimilarColor(Color color, ColorsSet colorsSet){

        int[][] colors = null;

        switch (colorsSet){
            case BRUSH:
                colors = COLORS_BRUSH;
                break;

            case PALETTE:
                colors=COLORS_PALETTE;
        }

        int[] absValues = new int[colors.length];

        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();

        for (int i = 0; i < colors.length; i++){
            absValues[i] = (int) Math.sqrt(Math.pow(colors[i][0] - red, 2) + Math.pow(colors[i][1] - green, 2) + Math.pow(colors[i][2] - blue, 2));
            absValues[i] = Math.abs(colors[i][0] - red) + Math.abs(colors[i][1] - green) + Math.abs(colors[i][2] - blue);
        }

        int minAbsValue = absValues[0];
        int[] moreSimilarColor = {colors[0][0], colors[0][1], colors[0][2]};

        for(int i = 1; i < absValues.length; i++){
            if(absValues[i] < minAbsValue){
                minAbsValue = absValues[i];

                moreSimilarColor[0] =  colors[i][0];
                moreSimilarColor[1] =  colors[i][1];
                moreSimilarColor[2] =  colors[i][2];
            }
        }

        return new Color(moreSimilarColor[0], moreSimilarColor[1], moreSimilarColor[2]);
    }

    enum ColorsSet{
        PALETTE, BRUSH;
    }
}
