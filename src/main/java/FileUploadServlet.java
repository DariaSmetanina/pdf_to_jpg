import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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

        //for example, you can copy the uploaded file to the server
        //note that you probably don't want to do this in real life!
        //upload it to a file host like S3 or GCS instead
        File fileToSave = new File("C:/Users/Dasha/Desktop/res/" + filePart.getSubmittedFileName());
        Files.copy(fileInputStream, fileToSave.toPath(), StandardCopyOption.REPLACE_EXISTING);

        //You can get other form data too
        String name = request.getParameter("name");

        //create output HTML that uses the
        response.getOutputStream().println("<p>File uploaded</p>");
        }
}
