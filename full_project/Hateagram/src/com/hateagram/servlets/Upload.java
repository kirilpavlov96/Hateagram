package com.hateagram.servlets;
 
import java.io.File;
import java.io.IOException;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.hateagram.DAO.MySQLUtil;
import com.hateagram.model.Picture;
 
@WebServlet("/Upload")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
                 maxFileSize=1024*1024*10,      // 10MB
                 maxRequestSize=1024*1024*50)   // 50MB
public class Upload extends HttpServlet {

    private static final String SAVE_DIR = "img";
     
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // gets absolute path of the web application
        String appPath = request.getServletContext().getRealPath("");
        // constructs path of the directory to save uploaded file
        String savePath = appPath + File.separator + SAVE_DIR;
         
        // creates the save directory if it does not exists
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
         
        for (Part part : request.getParts()) {
        	String username = (String)request.getSession(false).getAttribute("username");
        	int numberOfPost = MySQLUtil.getUserPostsCount(username);
        	String origFileName = extractFileName(part);
        	if(!origFileName.substring(origFileName.length()-3, origFileName.length()).equals("jpg")){
        		throw new ServletException("The file is not .jpg");
        	}
            String fileName = username+numberOfPost+".jpg";
            MySQLUtil.addNewPicture(new Picture(0, username, null, fileName));
            part.write(savePath + File.separator + fileName);
        }
 
        request.getRequestDispatcher("/Index").forward(request, response);
    }
 
    /**
     * Extracts file name from HTTP header content-disposition
     */
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }
}