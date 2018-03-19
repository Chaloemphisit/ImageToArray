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

    JPanel p, p1, pMain, pTxtArea, pMenu, pTop, pBottom;
    JTextArea txtAreaResult;
    JTextField txtFilePath;
    JButton btnOpenImg, btnConvertV1, btnConvertV2, btnConvertV3, btnConvertV4;
    JLabel lblCredit;
    JScrollPane areaScrollPane;

    static Frame frame;

    String FileName;

    static BufferedImage img = null;
    static JFileChooser fileopen;
    static int height;
    static int width;

    boolean isHelp = true;

    public ImageToArray(String title) {
        super(title);
        //new Main panel
        p = new JPanel();
        p.setLayout(new BorderLayout());

        p1 = new JPanel();
        p1.setLayout(new BorderLayout());

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
        pMenu.setLayout(new GridLayout(1, 5));

        //new TextArea
        txtAreaResult = new JTextArea("");
        txtAreaResult.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        txtAreaResult.setFont(new Font("Serif", Font.PLAIN, 14));

        areaScrollPane = new JScrollPane(txtAreaResult, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        areaScrollPane.setVisible(false);

        //new textField File Path
        txtFilePath = new JTextField("File Path: ");
        txtFilePath.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        txtFilePath.setFont(new Font("Serif", Font.PLAIN, 15));

        //new Label Credit
        lblCredit = new JLabel("Developed by CSAG", SwingConstants.CENTER);
        lblCredit.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));

        //new Button Open Image
        btnOpenImg = new JButton("Open Image");
        btnOpenImg.addActionListener(new ButtonOpenListener());

        //new Button Save
        btnConvertV3 = new JButton("Convert to Int RGB V1");
        btnConvertV3.addActionListener(new ButtonConvertV3Listener());
        btnConvertV3.setEnabled(false);

        //new Button Convert to Array [Int RGB]
        btnConvertV4 = new JButton("Convert to Int RGB V2");
        btnConvertV4.addActionListener(new ButtonConvertV4Listener());
        btnConvertV4.setEnabled(false);

        //new Button Convert to Array [Int RGB]
        btnConvertV1 = new JButton("Convert to Int RGB (Java Array)");
        btnConvertV1.addActionListener(new ButtonConvertV1Listener());
        btnConvertV1.setEnabled(false);

        //new Button Setting
        btnConvertV2 = new JButton("Convert to RGB");
        btnConvertV2.addActionListener(new ButtonConvertV2Listener());
        btnConvertV2.setEnabled(false);

        p.add(pTop, BorderLayout.NORTH);
        pTop.add(p1, BorderLayout.NORTH);
        p1.add(txtFilePath, BorderLayout.NORTH);
        pTop.add(pMenu, BorderLayout.SOUTH);
        pMenu.add(btnOpenImg, BorderLayout.WEST);
        pMenu.add(btnConvertV3, BorderLayout.EAST);
        pMenu.add(btnConvertV4, BorderLayout.EAST);
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
                    img = ImageIO.read(new File(txtFilePath.getText()));
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage() + "\nคำแนะนำ : ให้ตรวจสอบที่อยู่ของรูปภาพที่เลือกก่อน\n"
                            + "ถ้าหากที่อยู่ของไฟล์ไม่มีนามสกุล(File Name Extension)\n"
                            + "ให้เพิ่มต่อท้ายที่ช่องด้านบนก่อนทำรายการ", "พบข้อผิดพลาด!", JOptionPane.ERROR_MESSAGE);
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
                    img = ImageIO.read(new File(txtFilePath.getText()));
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage() + "\nคำแนะนำ : ให้ตรวจสอบที่อยู่ของรูปภาพที่เลือกก่อน\n"
                            + "ถ้าหากที่อยู่ของไฟล์ไม่มีนามสกุล(File Name Extension)\n"
                            + "ให้เพิ่มต่อท้ายที่ช่องด้านบนก่อนทำรายการ", "พบข้อผิดพลาด!", JOptionPane.ERROR_MESSAGE);
                }
                height = img.getHeight();
                width = img.getWidth();
                txtAreaResult.setText("");
                convertToArray(0);
            }
        }
    }

    private class ButtonConvertV3Listener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnConvertV3) {
                try {
                    /*   Load image to Bufferimage*/
                    img = ImageIO.read(new File(txtFilePath.getText()));
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage() + "\nคำแนะนำ : ให้ตรวจสอบที่อยู่ของรูปภาพที่เลือกก่อน\n"
                            + "ถ้าหากที่อยู่ของไฟล์ไม่มีนามสกุล(File Name Extension)\n"
                            + "ให้เพิ่มต่อท้ายที่ช่องด้านบนก่อนทำรายการ", "พบข้อผิดพลาด!", JOptionPane.ERROR_MESSAGE);

                }
                height = img.getHeight();
                width = img.getWidth();
                txtAreaResult.setText("");
                convertToArray(2);
            }
        }
    }

    private class ButtonConvertV4Listener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnConvertV4) {
                try {
                    /*   Load image to Bufferimage*/
                    img = ImageIO.read(new File(txtFilePath.getText()));
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage() + "\nคำแนะนำ : ให้ตรวจสอบที่อยู่ของรูปภาพที่เลือกก่อน\n"
                            + "ถ้าหากที่อยู่ของไฟล์ไม่มีนามสกุล(File Name Extension)\n"
                            + "ให้เพิ่มต่อท้ายที่ช่องด้านบนก่อนทำรายการ", "พบข้อผิดพลาด!", JOptionPane.ERROR_MESSAGE);
                }
                height = img.getHeight();
                width = img.getWidth();
                txtAreaResult.setText("");
                convertToArray(3);
            }
        }
    }

    private class ButtonOpenListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnOpenImg) {
                txtAreaResult.setText(null);
                txtFilePath.setText(null);
                FileName = null;

                fileopen = new JFileChooser();
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
                    btnConvertV3.setEnabled(true);
                    btnConvertV4.setEnabled(true);
                    isHelp = true;
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
//        System.out.println(types);
        if (types > 0 && isHelp) {
            final JDialog dlgHelp = new JDialog(this, "คำแนะนำ", true);

            String text = "คำแนะนำ\n"
                    + "แนะนำให้ใช้ Int RGB เพราะจะนำไปใช้งานง่ายที่สุด\n"
                    + "ผลลัพธ์ที่ได้จากฟังก์ชั่นนี้จะอยู่ในรูปแบบของ Interger RGB\n"
                    + "เมื่อต้องการนำไปใช้งานซึ่งอาจจะต้องการใช้งานที่อยู่ในรูปแบบของ Red, Green, Blue\n"
                    + "จะใช้คำสั่งดังนี้\n\n"
                    + "\trgb = imgArr[i][j]; //imgArr คือ Array ของผลลัพธ์ที่ได้จากโปรแกรมนี้ \n\n"
                    + "\tred = (rgb >> 16) & 0x000000FF;\n"
                    + "\tgreen = (rgb >> 8) & 0x000000FF;\n"
                    + "\tblue = (rgb) & 0x000000FF;";

            JTextArea txtHelp = new JTextArea(text);
            txtHelp.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            txtHelp.setFont(new Font("Serif", Font.PLAIN, 15));

            dlgHelp.add(BorderLayout.CENTER, txtHelp);
            dlgHelp.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); // prevent the user from closing the dialog
            dlgHelp.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
            dlgHelp.setSize(600, 400);
            dlgHelp.setLocationRelativeTo(this);
            dlgHelp.setVisible(true);
        }
    }

    Void printArr(int[][] imgArr, int types, JLabel lblStatus) {

//        Thread t = new Thread(new Runnable() {
//
//            @Override
//            public void run() {
        txtAreaResult.setText(null);
        String st = lblStatus.getText();
        double destination = imgArr.length * imgArr[0].length;
        double x = 0;
        areaScrollPane.setVisible(false);

        int rgb;
        int red;
        int green;
        int blue;

        if (types != 2 && types != 3) {
            txtAreaResult.append("{");
        }
        int i = 0, j = 0;
        for (i = 0; i < imgArr.length; i++) {
            if (types != 2 && types != 3) {
                txtAreaResult.append("{");
            }

            for (j = 0; j < imgArr[0].length; j++) {
                rgb = imgArr[i][j];
                red = (rgb >> 16) & 0x000000FF;
                green = (rgb >> 8) & 0x000000FF;
                blue = (rgb) & 0x000000FF;

                if (types == 0) {
                    txtAreaResult.append(String.format("{%3d, %3d, %3d}", red, green, blue) + "");
                } else {
                    txtAreaResult.append(imgArr[i][j] + "");
                }

                if (j != imgArr[0].length - 1) {
                    if (types != 2) {
                        txtAreaResult.append(", ");
                    } else {
                        txtAreaResult.append(" ");
                    }
                }

                lblStatus.setText(String.format("%s \nComplete [%3.0f%%]\n", st, ((x * 100.0f) / destination)));

                x++;

            }
            if (types != 2 && types != 3) {
                txtAreaResult.append("}");
            }

            if (i != imgArr.length - 1) {

                if (types != 2 && types != 3) {
                    txtAreaResult.append(",");
                }

                txtAreaResult.append(newline);
            }

        }
        if (types != 2 && types != 3) {
            txtAreaResult.append("};");
        }
//            }
//        });
//
//        t.start();
//        try {
//            t.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        areaScrollPane.setVisible(true);
        return (null);
    }

    void progress(int[][] imgArr, int types) {
        //true means that the dialog created is modal
        final JDialog dlgProgress = new JDialog(this, "Please wait...", true);
        JLabel lblStatus = new JLabel("Converting to Array...");
        JLabel lblStatus1 = new JLabel("");// this is just a label in which you can indicate the state of the processing
        lblStatus.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        lblStatus1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JButton btnCancel = new JButton("Cancel");

        JProgressBar pbProgress = new JProgressBar(0, 100);
        pbProgress.setIndeterminate(true); //we'll use an indeterminate progress bar

        dlgProgress.add(BorderLayout.NORTH, lblStatus);
        dlgProgress.add(BorderLayout.CENTER, lblStatus1);
        dlgProgress.add(BorderLayout.SOUTH, pbProgress);
        dlgProgress.add(BorderLayout.EAST, btnCancel);

        dlgProgress.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE); // prevent the user from closing the dialog
        dlgProgress.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        dlgProgress.setSize(300, 100);
        dlgProgress.setLocationRelativeTo(this);

        SwingWorker<Void, Void> sw = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                return printArr(imgArr, types, lblStatus1);
            }

            @Override
            protected void done() {
                dlgProgress.dispose();//close the modal dialog
            }
        };

        sw.execute(); // this will start the processing on a separate thread

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (ae.getSource() == btnCancel) {
                    int n = JOptionPane.showConfirmDialog(
                            null,
                            "ต้องการยกเลิกใช่หรือไม่ ?",
                            "Question",
                            JOptionPane.YES_NO_OPTION);
                    if (n == 0) {
//                        System.exit(0);
                        isHelp = false;
                        txtAreaResult.setText(null);
                        txtFilePath.setText(null);
                        FileName = null;
                        sw.cancel(true);
                    }
                }
            }
        });
        dlgProgress.setVisible(true); //this will block user input as long as the processing task is working
    }

    public static void main(String[] args) {

        ImageToArray f = new ImageToArray("Convert Image to Array");

        frame = f;

        f.setSize(1100, 600);
        Dimension DimMax = Toolkit.getDefaultToolkit().getScreenSize();
        f.setMaximumSize(DimMax);
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

}
