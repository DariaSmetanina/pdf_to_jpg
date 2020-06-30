import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.rendering.*;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

import java.awt.image.BufferedImage;
import java.io.*;

public class main {

    public static void main(String[] args) throws IOException {
        String filepath="C:\\Users\\Dasha\\Desktop\\Практика\\src\\main\\java\\";
        String filename="Пример.pdf";
        String extension="JPG";

        System.out.print("started\n");

        PDDocument document = PDDocument.load(new File(filepath+filename));
        filename=filename.substring(0, filename.length()-4);;
        PDFRenderer pdfRenderer = new PDFRenderer(document);
        for (int page = 0; page < document.getNumberOfPages(); ++page) {
            BufferedImage bim = pdfRenderer.renderImageWithDPI(
                    page, 300, ImageType.RGB);
            ImageIOUtil.writeImage(
                    bim, String.format("src/output/"+filename+"-%d.%s", page + 1, extension), 300);

            System.out.print("page"+page+"\n");
        }
        document.close();
    }
}


