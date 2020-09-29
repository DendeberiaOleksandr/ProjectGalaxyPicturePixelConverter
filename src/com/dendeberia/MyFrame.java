package com.dendeberia;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MyFrame extends JFrame {

    private Container contentPane = getContentPane();

    private JToolBar toolBar = new JToolBar();
    private JButton buttonOpen = new JButton("Open");

    private JPanel panelInternalFrames = new JPanel();

    private JInternalFrame internalFramePicture = new JInternalFrame("Picture");
    private JInternalFrame internalFrameConfig = new JInternalFrame("Configs");

    private JPanel picturePanel = new JPanel();
    private JScrollPane scrollPanePicture = new JScrollPane(picturePanel);
    private Picture pictureLabel = new Picture();

    private JPanel checkBoxesConfigPanel = new JPanel();

    private JPanel panelBrush = new JPanel();
    private JCheckBox checkBoxBrush = new JCheckBox();
    private JLabel labelBrush = new JLabel("brush colors");

    private JPanel panelPalette = new JPanel();
    private JCheckBox checkBoxPalette = new JCheckBox();
    private JLabel labelPalette = new JLabel("palette colors");

    private JPanel panelCut = new JPanel();
    private JLabel labelCut = new JLabel("cut");
    private JCheckBox checkBoxCut = new JCheckBox();

    private JPanel panelGrid = new JPanel();
    private JCheckBox checkBoxGrid = new JCheckBox();
    private JLabel labelGrid = new JLabel("grid");

    private JLabel cutImages = new JLabel();

    private JButton buttonConvert = new JButton("Convert");

    private final JPanel buttonsLeftRightPanel = new JPanel();

    private final JButton buttonLeft = new JButton("Left");
    private final JButton buttonRight = new JButton("Right");

    private ColorPanel colorsPanel = new ColorPanel();

    private JFileChooser fileChooser = new JFileChooser();
    private FileNameExtensionFilter fileNameExtensionFilter = new FileNameExtensionFilter(
            "JPG & PNG & GIF Images", "jpg", "gif", "png");

    private ActionListener actionListener = new MyActionListener();
    private final MouseWheelListener mouseWheelListener = new MouseWheelListener();

    public MyFrame() {
        super("ProjectGalaxyPictureConverter");
        setSize(1920, 1080);
        setMaximumSize(new Dimension(1920, 1080));
        setVisible(true);

        panelInternalFrames.setLayout(new BoxLayout(panelInternalFrames, BoxLayout.X_AXIS));
        panelInternalFrames.add(internalFramePicture);
        panelInternalFrames.add(internalFrameConfig);
        panelInternalFrames.setAlignmentY(Component.TOP_ALIGNMENT);

        toolBar.setAlignmentY(Component.TOP_ALIGNMENT);
        toolBar.add(buttonOpen);
        buttonOpen.addActionListener(actionListener);
        toolBar.setVisible(true);

        internalFramePicture.getContentPane().add(scrollPanePicture);
        picturePanel.add(pictureLabel);
        pictureLabel.addMouseListener(new MyMouseListener());
        pictureLabel.addMouseWheelListener(mouseWheelListener);
        pictureLabel.setCheckBoxGrid(checkBoxGrid, checkBoxCut);
        internalFramePicture.setMaximumSize(new Dimension(1440, 580));
        internalFramePicture.setVisible(true);

        scrollPanePicture.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPanePicture.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        panelBrush.setLayout(new BoxLayout(panelBrush, BoxLayout.X_AXIS));
        checkBoxBrush.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelBrush.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelBrush.add(checkBoxBrush);
        panelBrush.add(labelBrush);

        panelPalette.setLayout(new BoxLayout(panelPalette, BoxLayout.X_AXIS));
        checkBoxPalette.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelPalette.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelPalette.add(checkBoxPalette);
        panelPalette.add(labelPalette);

        panelGrid.setLayout(new BoxLayout(panelGrid, BoxLayout.X_AXIS));
        checkBoxGrid.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelGrid.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelGrid.add(checkBoxGrid);
        panelGrid.add(labelGrid);
        checkBoxGrid.addActionListener(actionListener);

        panelCut.setLayout(new BoxLayout(panelCut, BoxLayout.X_AXIS));
        panelCut.add(checkBoxCut);
        panelCut.add(labelCut);
        checkBoxCut.addActionListener(actionListener);

        buttonConvert.setMaximumSize(new Dimension(120, 20));
        buttonConvert.setAlignmentX(0.5f);
        buttonConvert.addActionListener(actionListener);

        buttonLeft.addActionListener(actionListener);
        buttonRight.addActionListener(actionListener);
        buttonsLeftRightPanel.setLayout(new BoxLayout(buttonsLeftRightPanel, BoxLayout.X_AXIS));
        buttonsLeftRightPanel.add(buttonLeft);
        buttonsLeftRightPanel.add(buttonRight);

        cutImages.setAlignmentX(Component.CENTER_ALIGNMENT);

        internalFrameConfig.getContentPane().setLayout(new BoxLayout(internalFrameConfig.getContentPane(), BoxLayout.Y_AXIS));

        internalFrameConfig.setMaximumSize(new Dimension(480, 580));
        internalFrameConfig.setMinimumSize(new Dimension(480, 580));

        Dimension checkBoxesPanelsDimension = new Dimension(150, 25);

        panelBrush.setMaximumSize(checkBoxesPanelsDimension);
        panelBrush.setMinimumSize(checkBoxesPanelsDimension);

        panelPalette.setMaximumSize(checkBoxesPanelsDimension);
        panelPalette.setMinimumSize(checkBoxesPanelsDimension);

        panelGrid.setMaximumSize(checkBoxesPanelsDimension);
        panelGrid.setMinimumSize(checkBoxesPanelsDimension);

        panelCut.setMaximumSize(checkBoxesPanelsDimension);
        panelCut.setMinimumSize(checkBoxesPanelsDimension);

        checkBoxesConfigPanel.setLayout(new BoxLayout(checkBoxesConfigPanel, BoxLayout.Y_AXIS));

        setVisibleCutImagesComponents(false);

        checkBoxesConfigPanel.add(panelBrush);
        checkBoxesConfigPanel.add(panelPalette);
        checkBoxesConfigPanel.add(panelGrid);
        checkBoxesConfigPanel.add(panelCut);
        checkBoxesConfigPanel.setBorder(BorderFactory.createTitledBorder("Parameters"));

        internalFrameConfig.getContentPane().add(checkBoxesConfigPanel);
        internalFrameConfig.getContentPane().add(colorsPanel);
        internalFrameConfig.getContentPane().add(cutImages);
        internalFrameConfig.getContentPane().add(buttonsLeftRightPanel);
        internalFrameConfig.getContentPane().add(buttonConvert);

        internalFrameConfig.setVisible(true);

        colorsPanel.setAlignmentY(Component.TOP_ALIGNMENT);
        colorsPanel.setMinimumSize(new Dimension(266, 44));
        colorsPanel.setMaximumSize(new Dimension(266, 62));

        contentPane.add(toolBar, BorderLayout.NORTH);
        contentPane.add(panelInternalFrames, BorderLayout.CENTER);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        fileChooser.setFileFilter(fileNameExtensionFilter);
    }

    private void openImage(File image){
        try {

            BufferedImage originalImage = ImageIO.read(image);

            if(originalImage.getWidth() > 1280 || originalImage.getHeight() > 720){
                JOptionPane.showMessageDialog(MyFrame.this, "Please choose smaller image. Maximum size is 1280x720");
                return;
            }

            pictureLabel.setOriginalImage(originalImage);

            pictureLabel.setIcon(new ImageIcon(originalImage));
            internalFramePicture.setTitle(image.getAbsolutePath());

            pictureLabel.setOriginalImageDimension(new Dimension(originalImage.getWidth(), originalImage.getHeight()));

            pictureLabel.setScaledWidth(originalImage.getWidth());
            pictureLabel.setScaledHeight(originalImage.getHeight());

            pictureLabel.setCurrentImage(originalImage);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class MyActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();

            if(source == buttonOpen){
                int i = fileChooser.showOpenDialog(MyFrame.this);

                if(i == JFileChooser.APPROVE_OPTION){

                    openImage(fileChooser.getSelectedFile());
                    pictureLabel.getCutImageList().clear();
                    colorsPanel.setColorsSet(null);

                    setVisibleCutImagesComponents(false);

                }
            } else if(source == buttonConvert){

                if(checkBoxBrush.isSelected() && checkBoxPalette.isSelected()){
                    JOptionPane.showMessageDialog(MyFrame.this, "Please choose only one option!");

                } else if(!checkBoxPalette.isSelected() && !checkBoxBrush.isSelected()){

                    JOptionPane.showMessageDialog(MyFrame.this, "Please choose the option!");

                } else if(pictureLabel.getOriginalImage() == null){

                    JOptionPane.showMessageDialog(MyFrame.this, "Please open the image!");

                } else if(checkBoxPalette.isSelected()){

                    BufferedImage originalImage = pictureLabel.getOriginalImage();

                    BufferedImage projectGalaxyColorsImage = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(), BufferedImage.TYPE_INT_RGB);

                    for(int h = 0; h < originalImage.getHeight(); h++){
                        for(int w = 0; w < originalImage.getWidth(); w++){

                            Color pixelColor = new Color(originalImage.getRGB(w, h));

                            projectGalaxyColorsImage.setRGB(w, h, ColorService.getMoreSimilarColor(pixelColor, ColorService.ColorsSet.PALETTE).getRGB());
                        }
                    }

                    pictureLabel.setCurrentImage(projectGalaxyColorsImage);

                    ImageIcon imageIcon = new ImageIcon();

                    imageIcon.setImage(projectGalaxyColorsImage.getScaledInstance(pictureLabel.getScaledWidth(), pictureLabel.getScaledHeight(), Image.SCALE_DEFAULT));

                    pictureLabel.setIcon(imageIcon);

                    BufferedImage bufferedImage = new BufferedImage((int) (Math.ceil((double)projectGalaxyColorsImage.getWidth() / 64) * 64), (int) (Math.ceil((double)projectGalaxyColorsImage.getHeight() / 64) * 64), BufferedImage.TYPE_INT_RGB);

                    for(int h = 0; h < projectGalaxyColorsImage.getHeight(); h++){
                        for(int w = 0; w < projectGalaxyColorsImage.getWidth(); w++){
                            bufferedImage.setRGB(w, h, projectGalaxyColorsImage.getRGB(w, h));
                        }
                    }

                    pictureLabel.getCutImageList().clear();

                    for(int h = 0; h < bufferedImage.getHeight() - 1; h += 64){
                        for(int w = 0; w < bufferedImage.getWidth() - 1; w += 64){

                            pictureLabel.getCutImageList().add(bufferedImage.getSubimage(w, h, 64, 64));

                        }
                    }

                    colorsPanel.setColorsSet(ColorService.ColorsSet.PALETTE);

                } else if(checkBoxBrush.isSelected()){

                    BufferedImage originalImage = pictureLabel.getOriginalImage();

                    BufferedImage projectGalaxyColorsImage = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(), BufferedImage.TYPE_INT_RGB);

                    for(int h = 0; h < originalImage.getHeight(); h++){
                        for(int w = 0; w < originalImage.getWidth(); w++){

                            Color pixelColor = new Color(originalImage.getRGB(w, h));

                            projectGalaxyColorsImage.setRGB(w, h, ColorService.getMoreSimilarColor(pixelColor, ColorService.ColorsSet.BRUSH).getRGB());
                        }
                    }

                    pictureLabel.setCurrentImage(projectGalaxyColorsImage);

                    ImageIcon imageIcon = new ImageIcon();

                    imageIcon.setImage(projectGalaxyColorsImage.getScaledInstance(pictureLabel.getScaledWidth(), pictureLabel.getScaledHeight(), Image.SCALE_DEFAULT));

                    pictureLabel.setIcon(imageIcon);

                    BufferedImage bufferedImage = new BufferedImage((int) (Math.ceil((double)projectGalaxyColorsImage.getWidth() / 64) * 64), (int) (Math.ceil((double)projectGalaxyColorsImage.getHeight() / 64) * 64), BufferedImage.TYPE_INT_RGB);

                    for(int w = 0; w < projectGalaxyColorsImage.getWidth(); w++){
                        for(int h = 0; h < projectGalaxyColorsImage.getHeight(); h++){

                            bufferedImage.setRGB(w, h, projectGalaxyColorsImage.getRGB(w, h));

                        }
                    }

                    pictureLabel.getCutImageList().clear();

                    for(int h = 0; h < bufferedImage.getHeight() - 1; h += 64){
                        for(int w = 0; w < bufferedImage.getWidth() - 1; w += 64){

                            pictureLabel.getCutImageList().add(bufferedImage.getSubimage(w, h, 64, 64));

                        }
                    }

                    pictureLabel.setCurrentCutImage(0);
                    colorsPanel.setColorsSet(ColorService.ColorsSet.BRUSH);
                }
            } else if(source == checkBoxCut){
                if(checkBoxCut.isSelected()) {
                    if(pictureLabel.getCutImageList().isEmpty()){
                        return;
                    }

                    pictureLabel.setCurrentImage(pictureLabel.getCutImageList().get(0));

                    setVisibleCutImagesComponents(true);

                    } else {

                    setVisibleCutImagesComponents(false);

                    if(pictureLabel.getOriginalImage() != null){
                        pictureLabel.setCurrentImage(pictureLabel.getOriginalImage());
                    }
                }
            } else if(source == buttonRight){
                if(pictureLabel.getCurrentCutImage() + 1 == pictureLabel.getCutImageList().size()){

                    pictureLabel.setCurrentCutImage(0);

                } else {

                    pictureLabel.setCurrentCutImage(pictureLabel.getCurrentCutImage() + 1);

                }

                pictureLabel.setCurrentImage(pictureLabel.getCutImageList().get(pictureLabel.getCurrentCutImage()));


            } else if(source == buttonLeft){
                if(pictureLabel.getCurrentCutImage() - 1 == -1){

                    pictureLabel.setCurrentCutImage(pictureLabel.getCutImageList().size() - 1);

                } else {

                    pictureLabel.setCurrentCutImage(pictureLabel.getCurrentCutImage() - 1);

                }

                pictureLabel.setCurrentImage(pictureLabel.getCutImageList().get(pictureLabel.getCurrentCutImage()));

            }

            pictureLabel.updateLabelCut(cutImages);
            pictureLabel.updatePictureLabelIcon();
            repaint();

        }
    }

    private class MyMouseListener implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            if(pictureLabel.getCurrentImage() == null){
                return;
            }

            int x = 0;
            int y = 0;

            if(checkBoxCut.isSelected()){
                if(pictureLabel.getScaledCutImageWidth() < 64){

                    x = (int)(e.getX() * (double)(64 / pictureLabel.getScaledCutImageWidth()));
                    y = (int)(e.getY() * (double)(64 / pictureLabel.getScaledCutImageHeight()));

                } else {

                    x = (int)(e.getX() / (double)(pictureLabel.getScaledCutImageWidth() / 64));
                    y = (int)(e.getY() / (double)(pictureLabel.getScaledCutImageHeight() / 64));

                }
            } else {
                return;
            }

            colorsPanel.setChosenColor(new Color(pictureLabel.getCurrentImage().getRGB(x, y)));
            repaint();
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    private class MouseWheelListener implements java.awt.event.MouseWheelListener{

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {

            int wheelRotation = e.getWheelRotation();

            if(checkBoxCut.isSelected()){
                if(wheelRotation > 0){
                    if(pictureLabel.getScaledCutImageWidth() <= 32){
                        return;
                    }

                    pictureLabel.setScaledCutImageWidth(pictureLabel.getScaledCutImageWidth() - 16);
                    pictureLabel.setScaledCutImageHeight(pictureLabel.getScaledCutImageHeight() - 16);

                } else {
                    if(pictureLabel.getScaledCutImageWidth() >= 768){
                        return;
                    }

                    pictureLabel.setScaledCutImageWidth(pictureLabel.getScaledCutImageWidth() + 32);
                    pictureLabel.setScaledCutImageHeight(pictureLabel.getScaledCutImageHeight() + 32);
                }

            } else {

                int originalImageWidth = pictureLabel.getOriginalImage().getWidth();
                int originalImageHeight = pictureLabel.getOriginalImage().getHeight();

                if(wheelRotation > 0){
                    //DOWN

                    if(pictureLabel.getScaledWidth() <= originalImageWidth / 2){
                        return;
                    }

                    pictureLabel.setScaledWidth(pictureLabel.getScaledWidth() - (int)Math.floor(originalImageWidth / 4));
                    pictureLabel.setScaledHeight(pictureLabel.getScaledHeight() - (int)Math.floor(originalImageHeight / 4));

                } else {
                    //UP

                    if(pictureLabel.getScaledHeight() >= originalImageWidth * 12){
                        return;
                    }

                    pictureLabel.setScaledWidth(pictureLabel.getScaledWidth() + (int)Math.floor(originalImageWidth / 2));
                    pictureLabel.setScaledHeight(pictureLabel.getScaledHeight() + (int)Math.floor(originalImageHeight / 2));

                }

            }

            pictureLabel.updatePictureLabelIcon();

            }
        }

        private void setVisibleCutImagesComponents(boolean flag){
            colorsPanel.setVisible(flag);
            buttonsLeftRightPanel.setVisible(flag);
            cutImages.setVisible(flag);
        }
    }

