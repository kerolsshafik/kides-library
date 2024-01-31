
package library;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

/**
 *
 * @author Kerlos
 */
public class pf {

    public static int index, book;
    public static int loc;
    public static File bookDir;
    public static int pages;
    public static void add(LinkedList<user> u, String name, String pass, String qa) {
        user n = new user(name, pass, qa);
        u.add(n);
    }

    public static void delb(LinkedList<user> u, int book) {
        
            (u.get(index)).books.remove(book);           
        }
    

    public static boolean checks(LinkedList<user> u, String n) {
        for (int i = 0; i < u.size(); i++) {
            if ((u.get(i).name).equals(n)) {
                return true;
            }
        }
        return false;

    }

    public static boolean checkl(LinkedList<user> u, String n, String p) {
        for (int i = 0; i < u.size(); i++) {
            if ((u.get(i).name).equals(n) && (u.get(i).pass).equals(p)) {
                index = i;

                return true;
            }
        }
        return false;

    }

    /*public static void addbooks(LinkedList<user> u, LinkedList<book> b, int index) {
        for (int i = 0; i < (u.get(index).books).size(); i++) {
            //b.add(u.get(index).books.get(index));
        }
    }*/

    public static boolean resetpass(LinkedList<user> u, String dob, String un, String newpass) {
        for (int i = 0; i < u.size(); i++) {
            if (u.get(i).name.equals(un) && u.get(i).qa.equals(dob)) {
                u.get(i).pass = newpass;
                return true;
            }
        }
        return false;
    }

    public static void read(String Filedes,String name) {
        try {
            String sourceDir = Filedes; // Pdf files are read from this folder
            String destinationDir = name; // converted images from pdf document are saved here

            File sourceFile = new File(sourceDir);
            File destinationFile = new File(destinationDir);
            if (!destinationFile.exists()) {
                destinationFile.mkdir();
                System.out.println("Folder Created -> " + destinationFile.getAbsolutePath());
                //exit(0);
            }
            if (sourceFile.exists()) {
                System.out.println("Images copied to Folder Location: " + destinationFile.getAbsolutePath());
                try (PDDocument document = PDDocument.load(sourceFile)) {
                    PDFRenderer pdfRenderer = new PDFRenderer(document);

                    int numberOfPages = document.getNumberOfPages();
                    pages=numberOfPages;
                    System.out.println("Total files to be converting -> " + numberOfPages);
                    
                    //String fileName = sourceFile.getName().replace(".pdf", "");
                    String fileExtension = "png";

                    int dpi = 600;// use less dpi for to save more space in harddisk. For professional usage you can use more than 300dpi

                    for (int i = 0; i < numberOfPages; ++i) {
                        File outPutFile = new File(name+"/"+i+"."+fileExtension);
                        BufferedImage bImage = pdfRenderer.renderImageWithDPI(i, dpi, ImageType.RGB);
                        ImageIO.write(bImage, fileExtension, outPutFile);
                    }
                }
                System.out.println("Converted Images are saved at -> " + destinationFile.getAbsolutePath());
            } else {
                System.err.println(sourceFile.getName() + " File not exists");
            }
        } catch (IOException e) {
            
        }
    }

    public static void searchn(LinkedList<user> d, int u, String a, Button b1, Button b2, Button b3, Button b4, Button b5) {
        b1.setText("");
        b2.setText("");
        b3.setText("");
        b4.setText("");
        b5.setText("");
        for (int i = 0; i < d.get(u).books.size(); i++) {
            if (d.get(u).books.get(i).name.contains(a)) {
                if (b1.getText().isEmpty()) {
                    b1.setText(d.get(u).books.get(i).name);
                } else if (b2.getText().isEmpty()) {
                    b2.setText(d.get(u).books.get(i).name);
                } else if (b3.getText().isEmpty()) {
                    b3.setText(d.get(u).books.get(i).name);
                } else if (b4.getText().isEmpty()) {
                    b4.setText(d.get(u).books.get(i).name);
                } else if (b5.getText().isEmpty()) {
                    b5.setText(d.get(u).books.get(i).name);
                } else {
                    break;
                }
            }
        }

    }

    public static void searchau(LinkedList<user> d, int u, String a, Button b1, Button b2, Button b3, Button b4, Button b5) {
        b1.setText("");
        b2.setText("");
        b3.setText("");
        b4.setText("");
        b5.setText("");
        for (int i = 0; i < d.get(u).books.size(); i++) {
            if (d.get(u).books.get(i).author.contains(a)) {
                if (b1.getText().isEmpty()) {
                    b1.setText(d.get(u).books.get(i).name);
                } else if (b2.getText().isEmpty()) {
                    b2.setText(d.get(u).books.get(i).name);
                } else if (b3.getText().isEmpty()) {
                    b3.setText(d.get(u).books.get(i).name);
                } else if (b4.getText().isEmpty()) {
                    b4.setText(d.get(u).books.get(i).name);
                } else if (b5.getText().isEmpty()) {
                    b5.setText(d.get(u).books.get(i).name);
                } else {
                    break;
                }
            }
        }

    }

    public static void searchreld(LinkedList<user> d, int u, String a, Button b1, Button b2, Button b3, Button b4, Button b5) {
        b1.setText("");
        b2.setText("");
        b3.setText("");
        b4.setText("");
        b5.setText("");
        for (int i = 0; i < d.get(u).books.size(); i++) {
            if (d.get(u).books.get(i).releasedate.contains(a)) {
                if (b1.getText().isEmpty()) {
                    b1.setText(d.get(u).books.get(i).name);
                } else if (b2.getText().isEmpty()) {
                    b2.setText(d.get(u).books.get(i).name);
                } else if (b3.getText().isEmpty()) {
                    b3.setText(d.get(u).books.get(i).name);
                } else if (b4.getText().isEmpty()) {
                    b4.setText(d.get(u).books.get(i).name);
                } else if (b5.getText().isEmpty()) {
                    b5.setText(d.get(u).books.get(i).name);
                } else {
                    break;
                }
            }
        }

    }

    public static void book(LinkedList<user> d, String a) {
        for (int i = 0; i < d.get(index).books.size(); i++) {
            if (d.get(index).books.get(i).name.equals(a)) {
                book = i;
            }
        }
    }

    public static void deletedir(File file) {
        //throws IOException{
        if (file.isDirectory()) {
            //directory is empty, then delete it
            if (file.list().length == 0) {
                file.delete();
            } else {
                //list all the directory contents
                String files[] = file.list();
                for (String temp : files) {
                    //construct the file structure
                    File fileDelete = new File(file, temp);
                    //recursive delete
                    deletedir(fileDelete);
                }
                //check the directory again, if empty then delete it
                if (file.list().length == 0) {
                    file.delete();
                }
            }
        } else {
            //if file, then delete it
            file.delete();
        }
    }
    public static void nextp(Image i,ImageView iv){
        if(pf.loc<pages-1){
            pf.loc++;
         i=new Image(new File(pf.book+"/"+pf.loc+".png").toURI().toString());
         iv.setImage(i);
        }
    }
    public static void prep(Image i,ImageView iv){
        if(pf.loc>0){
        pf.loc--;
         i=new Image(new File(pf.book+"/"+pf.loc+".png").toURI().toString());
         iv.setImage(i);
        }
    }
    
}
