package imagetoarray;

import java.awt.BorderLayout;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.*;

public class ImageToArray extends JFrame {

    private final static String newline = "\n";

    JPanel p, pMain, pTxtArea, pMenu, pTop, pBottom;
    JTextArea txtAreaResult;
    JTextField txtFilePath;
    JButton btnOpenImg, btnConvertV1, btnConvertV2;
    JLabel lblCredit;
    JScrollPane areaScrollPane;

    String FileName;

    static BufferedImage img = null;
    static File file = null;
    static int height;
    static int width;

    public ImageToArray(String title) {
        super(title);

        //new Main panel
        p = new JPanel();
        p.setLayout(new BorderLayout());

        pMain = new JPanel();
        pMain.setLayout(new BorderLayout());

        //new panel for contain textArea
        pTxtArea = new JPanel();
        pTxtArea.setLayout(new GridLayout(1, 1));

        pTop = new JPanel();
        pTop.setLayout(new BorderLayout());
        pBottom = new JPanel();
        pBottom.setLayout(new BorderLayout());

        //new panel for contain option menu
        pMenu = new JPanel();
        pMenu.setLayout(new GridLayout(1, 4));

        //new TextArea
        txtAreaResult = new JTextArea("");
        txtAreaResult.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        txtAreaResult.setFont(new Font("Serif", Font.PLAIN, 14));

        areaScrollPane = new JScrollPane(txtAreaResult, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        areaScrollPane.setVisible(false);

        //new textField File Path
        txtFilePath = new JTextField("File Path: ");
        txtFilePath.setEnabled(false);

        //new Label Credit
        lblCredit = new JLabel("Developed by CSAG", SwingConstants.CENTER);
        lblCredit.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));

        //new Button Open Image
        btnOpenImg = new JButton("Open Image");
        btnOpenImg.addActionListener(new ButtonOpenListener());

        //new Button Save
        btnConvertV1 = new JButton("Convert to Array [Int RGB]");
        btnConvertV1.addActionListener(new ButtonConvertV1Listener());
        btnConvertV1.setEnabled(false);

        //new Button Setting
        btnConvertV2 = new JButton("Convert to Array [RGB]");
        btnConvertV2.addActionListener(new ButtonConvertV2Listener());
        btnConvertV2.setEnabled(false);

        p.add(pTop, BorderLayout.NORTH);
        pTop.add(txtFilePath, BorderLayout.NORTH);
        pTop.add(pMenu, BorderLayout.SOUTH);
        pMenu.add(btnOpenImg, BorderLayout.WEST);
        pMenu.add(btnConvertV1, BorderLayout.CENTER);
        pMenu.add(btnConvertV2, BorderLayout.EAST);

        p.add(pTxtArea, BorderLayout.CENTER);
        pTxtArea.add(areaScrollPane, BorderLayout.CENTER);

        p.add(pBottom, BorderLayout.SOUTH);
        pBottom.add(lblCredit);

        add(p);

    }

    private class ButtonConvertV1Listener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnConvertV1) {
                try {
                    /*   Load image to Bufferimage*/
                    img = ImageIO.read(file = new File(txtFilePath.getText()));
                } catch (IOException ex) {
                }
                height = img.getHeight();
                width = img.getWidth();
                txtAreaResult.setText("");
                convertToArray(1);
            }
        }
    }

    private class ButtonConvertV2Listener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnConvertV2) {
                try {
                    /*   Load image to Bufferimage*/
                    img = ImageIO.read(file = new File(txtFilePath.getText()));
                } catch (IOException ex) {
                }
                height = img.getHeight();
                width = img.getWidth();
                txtAreaResult.setText("");
                convertToArray(0);
            }
        }
    }

    private class ButtonOpenListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnOpenImg) {
                JFileChooser fileopen = new JFileChooser();
                fileopen.setAcceptAllFileFilterUsed(false);

                FileFilter filter = new FileFilter() {
                    //Accept all directories and all gif, jpg, tiff, or png files.
                    public boolean accept(File f) {
                        if (f.isDirectory()) {
                            return true;
                        }

                        String extension = getExtension(f);
                        if (extension != null) {
                            if (extension.equals("tiff")
                                    || extension.equals("tif")
                                    || extension.equals("gif")
                                    || extension.equals("jpeg")
                                    || extension.equals("jpg")
                                    || extension.equals("png")
                                    || extension.equals("bmp")) {
                                return true;
                            } else {
                                return false;
                            }
                        }

                        return false;
                    }

                    //The description of this filter
                    public String getDescription() {
                        return "Just Images *(jpg, jpeg, png, gif, bmp, tif, tiff)";
                    }

                    public String getExtension(File f) {
                        String ext = null;
                        String s = f.getName();
                        int i = s.lastIndexOf('.');

                        if (i > 0 && i < s.length() - 1) {
                            ext = s.substring(i + 1).toLowerCase();
                        }
                        return ext;
                    }
                };

                fileopen.addChoosableFileFilter(filter);

                int ret = fileopen.showDialog(null, "Choose Image");

                if (ret == JFileChooser.APPROVE_OPTION) {
                    txtFilePath.setText(fileopen.getSelectedFile().toString());
                    btnConvertV1.setEnabled(true);
                    btnConvertV2.setEnabled(true);
                }
            }
        }
    }

    public void convertToArray(int types) {
        int[][] imgArr = new int[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                imgArr[y][x] = img.getRGB(x, y);
            }
        }
        //Print Image Array
        progress(imgArr, types);
    }

    Void printArr(int[][] imgArr, int types, JLabel lblStatus) {

        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                String st = lblStatus.getText();
                double destination = imgArr.length * imgArr[0].length;
                double x = 0;
                areaScrollPane.setVisible(false);

                int rgb;
                int red;
                int green;
                int blue;

//        System.out.print("{");
                txtAreaResult.append("{");
                int i = 0, j = 0;
                for (i = 0; i < imgArr.length; i++) {
//            System.out.print("{");
                    txtAreaResult.append("{");

                    for (j = 0; j < imgArr[0].length; j++) {

                        rgb = imgArr[i][j];
                        red = (rgb >> 16) & 0x000000FF;
                        green = (rgb >> 8) & 0x000000FF;
                        blue = (rgb) & 0x000000FF;

                        //System.out.printf("[%d : R=%3d, G=%3d, B=%3d]", imgArr[i][j], red, green, blue);
//                    
                        if (types == 0) {
                            txtAreaResult.append(String.format("{%3d, %3d, %3d}", red, green, blue) + "");
                        } else {
                            txtAreaResult.append(imgArr[i][j] + "");
                        }
//                    System.out.print(imgArr[i][j]);

                        if (j != imgArr[0].length - 1) {
//                    System.out.print(",");
                            txtAreaResult.append(", ");
                        }
//                        lblStatus.setText(String.format("%s [%d/%.0f]", st, (i * j), destination));
                        lblStatus.setText(String.format("%s [%3.0f%%]\n", st, ((x * 100.0f) / destination)));

                        x++;
//                        System.out.printf("%s [%3.0f%%]\n", st, ((x * 100.0f) / destination));

                    }
//            System.out.print("}");
                    txtAreaResult.append("}");

                    if (i != imgArr.length - 1) {
//                System.out.print(",");
                        txtAreaResult.append(",");

//                System.out.println("");
                        txtAreaResult.append(newline);
                    }

                }
//        System.out.print("};");
                txtAreaResult.append("};");
            }
        });

        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        areaScrollPane.setVisible(true);
        return (null);
    }

    void progress(int[][] imgArr, int types) {
        //true means that the dialog created is modal
        final JDialog dlgProgress = new JDialog(this, "Please wait...", true);
        JLabel lblStatus = new JLabel("Converting to Array..."); // this is just a label in which you can indicate the state of the processing
        lblStatus.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JProgressBar pbProgress = new JProgressBar(0, 100);
        pbProgress.setIndeterminate(true); //we'll use an indeterminate progress bar

        dlgProgress.add(BorderLayout.NORTH, lblStatus);
        dlgProgress.add(BorderLayout.CENTER, pbProgress);
        dlgProgress.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE); // prevent the user from closing the dialog
        dlgProgress.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        dlgProgress.setSize(300, 100);
        dlgProgress.setLocationRelativeTo(this);

        SwingWorker<Void, Void> sw = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                return printArr(imgArr, types, lblStatus);
            }

            @Override
            protected void done() {
                dlgProgress.dispose();//close the modal dialog
            }
        };

        sw.execute(); // this will start the processing on a separate thread
        dlgProgress.setVisible(true); //this will block user input as long as the processing task is working
    }

    public static void main(String[] args) {
        ImageToArray f = new ImageToArray("Convert Image to Array");
        f.setSize(900, 550);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

}
