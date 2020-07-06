import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


@WebServlet("/upload")
@MultipartConfig
public class FileUploadServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        //get the file chosen by the user
        Part filePart = request.getPart("fileToUpload");

        //get the InputStream to store the file somewhere
        InputStream fileInputStream = filePart.getInputStream();

        String filename=filePart.getSubmittedFileName();
        String fileExtension=filename.substring(filename.length()-4, filename.length());;

        if(!fileExtension.equals(".pdf")){
            response.getOutputStream().println("<p>NOT PDF</p>");
            return;
        }
        //for example, you can copy the uploaded file to the server
        //note that you probably don't want to do this in real life!
        //upload it to a file host like S3 or GCS instead
        filename=decodeGetParameter(filename.substring(0,filename.length()-4))+".pdf";
        File fileToSave = new File("C:/Users/Dasha/Desktop/res/" + filename);
        Files.copy(fileInputStream, fileToSave.toPath(), StandardCopyOption.REPLACE_EXISTING);


        //create output HTML that uses the
        response.getOutputStream().println("<p>File uploaded</p>");

        Convert.ConvertPDF(filename);

        response.getOutputStream().println("<p>File converted</p>");
        }

    public static String decodeGetParameter(String parameter) throws UnsupportedEncodingException {
        return new String(parameter.getBytes("Windows-1251"),"UTF8");
    }

}
