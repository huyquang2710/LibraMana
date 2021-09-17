package com.libra.web.controller;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.libra.core.entities.User;
import com.libra.core.services.IUserService;
import com.libra.core.utils.Utility;
import com.libra.web.message.MessageResponse;

import net.bytebuddy.utility.RandomString;

@Controller
public class ForgotPasswordController {
	
	@Autowired
	private IUserService userService;
	@Autowired
	private JavaMailSender mailSender;
	
	@GetMapping("/forgot_password")
	public String showForgotPasswordForm(Model model) {
		
		model.addAttribute("title", "Quên Mật Khẩu");
		return "forgot_password_form";
	}
	@PostMapping("/forgot_password")
	public String processForgotPasswordForm(HttpServletRequest request, Model model, HttpSession session) {
		String email = request.getParameter("email");
		String token = RandomString.make(45); // sinh ra mã token 45 chữ ngẫu nhiên
		
		System.out.println("email: " + email);
		System.out.println("token: " + token);
		   
	    try {
	        userService.updateResetPassword(token, email);
	        String resetPasswordLink = Utility.getSiteURL(request) + "/reset_password?token=" + token;
	        sendMail(email, resetPasswordLink);
	        session.setAttribute("message", new MessageResponse("Chúng tôi đã gửi mã xác nhận đến gmail của bạn! Vui lòng kiểm tra!!, vui lòng thử lại!", "danger"));
	    } catch (UsernameNotFoundException ex) {
	        model.addAttribute("error", ex.getMessage());
	    } catch (UnsupportedEncodingException | MessagingException e) {
	        model.addAttribute("error", "Có lỗi khi thực hiện!!");
	    }

		return "forgot_password_form";
	}
	// gui mail
	private void sendMail(String recipientEmail, String resetPasswordLink) throws UnsupportedEncodingException, MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		helper.setFrom("contact@librarybook.com", "LibraryManager Support");
		helper.setTo(recipientEmail);
		
		String subject = "đường link để reset password của bạn";
	     
	    String content = "<p>Xin chào, </p>"
	            + "<p>Bạn đã yêu cầu đặt lại mật khẩu của mình. </p>"
	            + "<p>Nhấp vào liên kết bên dưới để thay đổi mật khẩu của bạn: </p>"
	            + "<p><a href=\"" + resetPasswordLink + "\">Thay đổi mật khẩu của tôi </a></p>"
	            + "<br>"
	            + "<p>Bỏ qua email này nếu bạn nhớ mật khẩu của mình, "
	            + "hoặc bạn đã không thực hiện yêu cầu.</p>";
	     
	    helper.setSubject(subject);
	     
	    helper.setText(content, true);
	     
	    mailSender.send(message);
	}
	
	//Khi user  nhấp vào link trong mail sẽ redirect đến đây
	@GetMapping("/reset_password")
	public String showResetPasswordForm(@Param(value = "token") String token, Model model, HttpSession session) {
	    User user = userService.getByResetPasswordToken(token);
	    model.addAttribute("token", token);
	     
	    if (user == null) {
	        session.setAttribute("message", new MessageResponse("Mã Không Hợp Lệ", "danger"));
	        return "message";
	    }
	     
	    return "reset_password_form";
	}
	
	//reset pass handler
	@PostMapping("/reset_password")
	public String processResetPassword(HttpServletRequest request, Model model, HttpSession session) {
		 String token = request.getParameter("token");
		 String password = request.getParameter("password");
		 
		 User user = userService.getByResetPasswordToken(token);
		 model.addAttribute("title", "Đổi Mật Khẩu");
		    if (user == null) {
		        session.setAttribute("message", new MessageResponse("Mã Không Hợp Lệ", "danger"));
		        return "message";
		    } else {           
		        userService.updateNewPassword(user, password);
		        session.setAttribute("message", new MessageResponse("Bạn đã thay đổi thành công mật khẩu ", "success"));
		    }  
		    return "redirect:/login";
	}
}
