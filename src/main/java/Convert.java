import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.rendering.*;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class Convert {

    private static String filepath="C:/Users/Dasha/Desktop/res/";
    private static String extension="JPG";

    public static void ConvertPDF(String filename) throws IOException {

        System.out.print("started\n");

        PDDocument document = PDDocument.load(new File(filepath+filename));
        filename=filename.substring(0, filename.length()-4);;
        PDFRenderer pdfRenderer = new PDFRenderer(document);
        ImageIO.setCacheDirectory(new File("C:/Users/Dasha/Desktop/res/"));
        for (int page = 0; page < document.getNumberOfPages(); ++page) {
            BufferedImage bim = pdfRenderer.renderImageWithDPI(
                    page, 300, ImageType.RGB);
            ImageIOUtil.writeImage(
                    bim, String.format(filepath+"JPG/"+filename+"-%d.%s", page + 1, extension), 300);

            System.out.print("page"+page+"\n");
        }
        document.close();
    }
}


