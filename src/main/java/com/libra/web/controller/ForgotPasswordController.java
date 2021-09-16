package com.libra.web.controller;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
	public String processForgotPasswordForm(Model model, HttpServletRequest request, HttpSession session) {
		String email = request.getParameter("email");
		String token = RandomString.make(45); // sinh ra mã token 45 chữ ngẫu nhiên
		
		System.out.println("email: " + email);
		System.out.println("token: " + token);
		
		try {
			// database sẽ sinh được add 1 fotgot_pass_token
			userService.updateResetPassword(token, email);
			
			String resetPasswordLink = Utility.getSiteURL(request) + "/reset_password?token=" + token;
			System.out.println(resetPasswordLink);
			
			// thuc hien gui mail
			try {
				sendMail(email, resetPasswordLink);
				session.setAttribute("message", new MessageResponse("Chúng tôi đã gửi mã xác nhận đến gmail của bạn! Vui lòng kiểm tra", "alert-success"));
			} catch (UnsupportedEncodingException | MessagingException e) {
				e.printStackTrace();
				session.setAttribute("message", new MessageResponse("Có lỗi khi thực hiện!!", "alert-danger"));
			}
			
		} catch (UsernameNotFoundException e) {
			session.setAttribute("message", new MessageResponse(e.getMessage(), "alert-error"));
		}
		
		return "forgot_password_form";
	}
	// gui mail
	private void sendMail(String email, String resetPasswordLink) throws UnsupportedEncodingException, MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		helper.setFrom("contact@librarybook.com", "LibraryManager Support");
		helper.setTo(email);
		
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
}
