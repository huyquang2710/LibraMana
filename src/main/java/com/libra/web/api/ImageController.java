//package com.libra.web.api;
//
//import java.io.File;
//import java.io.IOException;
//
//import javax.annotation.Resource;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.libra.core.config.ImageResourceHttpRequestHandler;
//
//@RestController
//@RequestMapping("/images")
//public class ImageController {
//	@Resource
//	private ImageResourceHttpRequestHandler handler;
//	
//	@GetMapping("/download")
//    public void download(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
//            throws ServletException, IOException {
//        File file = new File("/images");
//        httpServletRequest.setAttribute(ImageResourceHttpRequestHandler.ATTRIBUTE_FILE, file);
//        handler.handleRequest(httpServletRequest, httpServletResponse);
//    }
//}
