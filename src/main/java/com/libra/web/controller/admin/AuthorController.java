package com.libra.web.controller.admin;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.libra.core.entities.Author;
import com.libra.core.services.IAuthorService;
import com.libra.core.utils.FileUploadUtil;
import com.libra.exception.BadResourceException;
import com.libra.exception.ResourceNotFoundException;
import com.libra.web.dto.AuthorDTO;
import com.libra.web.message.MessageResponse;

@Controller
@RequestMapping("/admin/author")
public class AuthorController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IAuthorService authorService;
	@Autowired
	private ModelMapper modelMapper;
	
	// get all
	/*
	 * @GetMapping public String authorPage(Model model) { List<Author> authorList =
	 * authorService.findAll();
	 * 
	 * model.addAttribute("author", authorList); model.addAttribute("title",
	 * "Tác Giả"); return "admin/author/authorPage"; }
	 */
	@GetMapping
	public String viewPage(Model model) {
		return authorPage(model, 1);
	}
	// pagination
	@GetMapping("/page/{pageNo}")
	public String authorPage(Model model, @PathVariable("pageNo") int currentPage) {
		Page<Author> page = authorService.findByPageable(currentPage);
		long totalItems = page.getTotalElements(); // tổng số tác giả
		int totalPages = page.getTotalPages();
		
		List<Author> authorList = page.getContent();
		
		model.addAttribute("currentPage", currentPage); // trang hiện tại
		model.addAttribute("totalItems", totalItems); // tổng số tác giả
		model.addAttribute("totalPages", totalPages);
		
		model.addAttribute("author", authorList);
		model.addAttribute("title","Tác Giả");
		
		return "admin/author/authorPage";
	}
	
	// new form
	@GetMapping("/new")
	public String authorForm(@ModelAttribute AuthorDTO authorDTO , Model model) {
		
		model.addAttribute("author", authorDTO);
		model.addAttribute("title", "Thêm Tác Giả");
		return "admin/author/authorNew";
	}
	// new action
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String newAuthor(
			@Valid @ModelAttribute("author") AuthorDTO authorDTO ,
		BindingResult bindingResult,
		@RequestParam("imageFile") MultipartFile multipartFile,
		Model model,
		HttpSession session ) {
		
		try {
			// Kiểm tra dữ liệu hợp lệ
			if(bindingResult.hasErrors()) {
				System.out.println("Author: " + bindingResult.toString());
				//model.addAttribute("author", authorDTO);
				return "admin/author/authorNew";
			}
			
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		
			authorDTO.setImage(fileName);				

			Author author = new Author();
			//chuuyen kieu thanh entity
			modelMapper.map(authorDTO, author);
			this.authorService.save(author);
			
			String uploadDir = "avatar/author/" + author.getId();
			
			//lưu đường dẫn
			FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
			
			System.out.println("Thêm Tác giả thành công!!");
			
			model.addAttribute("author", authorDTO);
			session.setAttribute("message", new MessageResponse("Thêm Tác Giả thành công!!", "success"));
			return "redirect:/admin/author";
		} catch (Exception e) {
			System.out.println("Thêm Tác giả thất bại!!");
			String errorMessage = e.getMessage();
			session.setAttribute("message", new MessageResponse(errorMessage, "danger"));
			LOGGER.error(errorMessage);
			return "admin/author/authorNew";
		}
	}
	//findById
	@GetMapping("/findById/{id}")
	public String authorEdit(@PathVariable("id") Integer id , Model model, HttpSession session) throws ResourceNotFoundException {
		// kiểm tra id có giá trị ko
		System.out.println("id: " + id);
		
		if(id != null) {
			Optional<Author> authorOtp = this.authorService.findById(id);
			Author author = authorOtp.get();
			
			model.addAttribute("author", author);
			model.addAttribute("title", "Chỉnh Sửa Tác Giả");
			return "admin/author/authorEdit";
		}
		session.setAttribute("message", new MessageResponse("Không tìm thấy Tác Giả!!, vui lòng thử lại!", "danger"));
		return "admin/author/authorPage";
	}
	// update action
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateAuthor(
			@Valid @ModelAttribute("author") Author author ,
		BindingResult bindingResult,
		@RequestParam("imageFile") MultipartFile multipartFile,
		Model model,
		HttpSession session ) {
		
		try {
			// Kiểm tra dữ liệu hợp lệ
			if(bindingResult.hasErrors()) {
				System.out.println("Author: " + bindingResult.toString());
				return "admin/author/authorUpdate";
			}

			//lấy dữ liệu cũ
			Author oldAuthor = authorService.findById(author.getId()).get();
			
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			
			if(!multipartFile.isEmpty()) {
				//delete image
				String deleteFile = "avatar/author/" + author.getId();
				File file = new File(deleteFile, oldAuthor.getImage());
				file.delete();
				
				//thêm ảnh mới
				author.setImage(fileName);	
				String uploadDir = "avatar/author/" + author.getId();
				FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
			} 
			authorService.update(author);
			System.out.println("Thêm Tác giả thành công!!");
			
			model.addAttribute("author", author);
			session.setAttribute("message", new MessageResponse("Cập nhật Tác Giả thành công!!", "success"));
			return "redirect:/admin/author";
		} catch (UsernameNotFoundException | IOException | BadResourceException | ResourceNotFoundException e) {
			System.out.println("Cập nhật Tác giả thất bại!!");
			String errorMessage = e.getMessage();
			session.setAttribute("message", new MessageResponse( e.getMessage(), "danger"));
			LOGGER.error(errorMessage);
			return "admin/author/authorEdit";
		}
	}
	
//	// update action
//		@RequestMapping(value = "/update", method = RequestMethod.POST)
//		public String updateAuthor(
//				@Valid @ModelAttribute("author") AuthorDTO authorDTO ,
//			BindingResult bindingResult,
//			@RequestParam("imageFile") MultipartFile multipartFile,
//			Model model,
//			HttpSession session ) {
//			
//			try {
//				// Kiểm tra dữ liệu hợp lệ
//				if(bindingResult.hasErrors()) {
//					System.out.println("Author: " + bindingResult.toString());
//					//model.addAttribute("author", authorDTO);
//					return "admin/author/authorUpdate";
//				}
//				// chuyển dto thành entity
//				Author authorEntity =  modelMapper.map(authorDTO, Author.class);
//				
//				//lấy dữ liệu cũ
//				Author oldAuthor = authorService.findById(authorEntity.getId()).get();
//				
//				String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
//				
//				if(!multipartFile.isEmpty()) {
//					System.out.println("File ảnh tồn tại !!");
//					//xóa ảnh cũ
//					String deleteFile = "avatar/author/" + authorEntity.getId();
//					File deleteAction = new File(deleteFile, oldAuthor.getImage());
//					deleteAction.delete();
//					
//					//thêm ảnh mới
//					String uploadDir = "avatar/author/" + authorEntity.getId();
//					FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
//				} else {
//					// dùng ảnh cũ
//					authorEntity.setImage(oldAuthor.getImage());
//				}
//				this.authorService.update(authorEntity);
//				System.out.println("Thêm Tác giả thành công!!");
//				
//				//chuyển entity thành dto
//				AuthorDTO authorResponse = modelMapper.map(authorEntity, AuthorDTO.class);
//				
//				model.addAttribute("author", authorResponse);
//				session.setAttribute("message", new MessageResponse("Cập nhật Tác Giả thành công!!", "success"));
//				return "redirect:/admin/author";
//			} catch (Exception e) {
//				System.out.println("Cập nhật Tác giả thất bại!!");
//				session.setAttribute("message", new MessageResponse("Cập nhật Tác Giả thất bại!!, vui lòng thử lại!", "danger"));
//				String errorMessage = e.getMessage();
//				LOGGER.error(errorMessage);
//				return "admin/author/authorEdit";
//			}
//		}
//		
	@GetMapping("/delete/{id}")
	public String deleteAuthor(@PathVariable("id") Integer id, HttpSession session) {
		try {
			authorService.delete(id);
			
			session.setAttribute("message", new MessageResponse("Xóa Thành Công!!,!", "success"));
			return "redirect:/admin/author";
		} catch (ResourceNotFoundException e) {
			String errorMessage = e.getMessage();
	        LOGGER.error(errorMessage);
	        session.setAttribute("message", new MessageResponse("Xóa Thất Bại!!,!", "danger"));
	        return "admin/author/authorPage";
		}
	}
}
