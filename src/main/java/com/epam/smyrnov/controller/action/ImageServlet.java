package com.epam.smyrnov.controller.action;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

@WebServlet("/img/*")
public class ImageServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String img = req.getPathInfo().substring(1);
		BufferedImage bufferedImage = ImageIO.read(new File(getServletContext().getRealPath("/img") + "\\" + img));
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ImageIO.write(bufferedImage, "jpg", byteArrayOutputStream);
		byte[] image = byteArrayOutputStream.toByteArray();
		resp.setContentType(getServletContext().getMimeType(img));
		resp.setContentLength(image.length);
		resp.getOutputStream().write(image);
	}
}
