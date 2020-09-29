package com.dendeberia;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Picture extends JLabel {

    private Dimension originalImageDimension;

    private BufferedImage originalImage;
    private BufferedImage currentImage;
    private int currentCutImage;

    private List<BufferedImage> cutImageList = new ArrayList<>();

    private JCheckBox checkBoxCut;
    private JCheckBox checkBoxGrid;

    private int scaledWidth;
    private int scaledHeight;

    private int scaledCutImageWidth = 64;
    private int scaledCutImageHeight = 64;

    public int getCurrentCutImage() {
        return currentCutImage;
    }

    public void setCurrentCutImage(int currentCutImage) {
        this.currentCutImage = currentCutImage;
    }

    public BufferedImage getOriginalImage() {
        return originalImage;
    }

    public void setOriginalImage(BufferedImage originalImage) {
        this.originalImage = originalImage;
    }

    public BufferedImage getCurrentImage() {
        return currentImage;
    }

    public void setCurrentImage(BufferedImage currentImage) {
        this.currentImage = currentImage;
    }

    public void setCheckBoxGrid(JCheckBox checkBoxGrid, JCheckBox checkBoxCut) {
        this.checkBoxGrid = checkBoxGrid;
        this.checkBoxCut = checkBoxCut;
    }

    public List<BufferedImage> getCutImageList() {
        return cutImageList;
    }

    public int getScaledWidth() {
        return scaledWidth;
    }

    public void setScaledWidth(int scaledWidth) {
        this.scaledWidth = scaledWidth;
    }

    public int getScaledHeight() {
        return scaledHeight;
    }

    public int getScaledCutImageWidth() {
        return scaledCutImageWidth;
    }

    public void setScaledCutImageWidth(int scaledCutImageWidth) {
        this.scaledCutImageWidth = scaledCutImageWidth;
    }

    public int getScaledCutImageHeight() {
        return scaledCutImageHeight;
    }

    public void setScaledCutImageHeight(int scaledCutImageHeight) {
        this.scaledCutImageHeight = scaledCutImageHeight;
    }

    public void setScaledHeight(int scaledHeight) {
        this.scaledHeight = scaledHeight;
    }

    public void setOriginalImageDimension(Dimension originalImageDimension) {
        this.originalImageDimension = originalImageDimension;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if(checkBoxGrid.isSelected()){

            g.setColor(Color.cyan);

            if(checkBoxCut.isSelected()){
                for(int w = 0; w < 64; w++){
                    g.drawLine(w * scaledCutImageWidth / 64, 0, w * scaledCutImageWidth / 64, scaledCutImageHeight - 1);
                }

                for(int h = 0; h < 64; h++){
                    g.drawLine(0, h * scaledCutImageHeight / 64, scaledCutImageWidth - 1, h * scaledCutImageHeight / 64);
                }
            } else {
                for(int w = 0; w < originalImageDimension.width; w++){

                    g.drawLine(w * scaledWidth / originalImageDimension.width, 0, w * scaledWidth / originalImageDimension.width, scaledHeight - 1);

                }

                g.setColor(Color.BLACK);

                for(int w = 0; w < originalImageDimension.width; w += 64){

                    g.drawLine(w * scaledWidth / originalImageDimension.width, 0, w * scaledWidth / originalImageDimension.width, scaledHeight - 1);

                }

                g.setColor(Color.cyan);

                for(int h = 0; h < originalImageDimension.height; h++){

                    g.drawLine(0, h * scaledHeight / originalImageDimension.height, scaledWidth - 1, h * scaledHeight / originalImageDimension.height);

                }

                g.setColor(Color.BLACK);

                for(int h = 0; h < originalImageDimension.height; h += 64){

                    g.drawLine(0, h * scaledHeight / originalImageDimension.height, scaledWidth - 1, h * scaledHeight / originalImageDimension.height);

                }
            }

        }

    }

    public void updatePictureLabelIcon(){
        if(currentImage == null){
            return;
        }

        int scaledW = scaledWidth;
        int scaledH = scaledHeight;

        if(checkBoxCut.isSelected()){
            scaledW = scaledCutImageWidth;
            scaledH = scaledCutImageHeight;
        }

        this.setIcon(new ImageIcon(currentImage.getScaledInstance(scaledW, scaledH, Image.SCALE_DEFAULT)));
    }

    public void updateLabelCut(JLabel labelCut){
        labelCut.setText(currentCutImage + 1 + "/" + cutImageList.size());
    }
}
