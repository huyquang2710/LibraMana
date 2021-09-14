package com.libra.web.controller.admin;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.libra.core.entities.Publisher;
import com.libra.core.services.IPublisherService;
import com.libra.exception.ResourceNotFoundException;
import com.libra.web.dto.PublisherDTO;
import com.libra.web.message.MessageResponse;

@Controller
@RequestMapping("/admin/publisher")
public class PublisherController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IPublisherService publisherService;
	@Autowired
	private ModelMapper modelMapper;
	
	// get all
	/*
	 * @GetMapping public String publisherPage(Model model) { List<publisher> publisherList =
	 * publisherService.findAll();
	 * 
	 * model.addAttribute("publisher", publisherList); model.addAttribute("title",
	 * "Nhà Xuất Bản"); return "admin/publisher/publisherPage"; }
	 */
	@GetMapping
	public String viewPage(Model model) {
		return publisherPage(model, 1);
	}
	// pagination
	@GetMapping("/page/{pageNo}")
	public String publisherPage(Model model, @PathVariable("pageNo") int currentPage) {
		Page<Publisher> page = publisherService.findByPageable(currentPage);
		long totalItems = page.getTotalElements(); // tổng số Nhà Xuất Bản
		int totalPages = page.getTotalPages();
		
		List<Publisher> publisherList = page.getContent();
		
		model.addAttribute("currentPage", currentPage); // trang hiện tại
		model.addAttribute("totalItems", totalItems); // tổng số Nhà Xuất Bản
		model.addAttribute("totalPages", totalPages);
		
		model.addAttribute("publisher", publisherList);
		model.addAttribute("title","Nhà Xuất Bản");
		
		return "admin/publisher/publisherPage";
	}
	
	// new form
	@GetMapping("/new")
	public String publisherForm(@ModelAttribute PublisherDTO publisherDTO , Model model) {
		
		model.addAttribute("publisher", publisherDTO);
		model.addAttribute("title", "Thêm Nhà Xuất Bản");
		return "admin/publisher/publisherNew";
	}
	// new action
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String newPublisher(
			@Valid @ModelAttribute("publisher") PublisherDTO publisherDTO ,
		BindingResult bindingResult,
		@RequestParam("imageFile") MultipartFile fileImage,
		Model model,
		HttpSession session ) {
		
		try {
			// Kiểm tra dữ liệu hợp lệ
			if(bindingResult.hasErrors()) {
				System.out.println("publisher: " + bindingResult.toString());
				//model.addAttribute("publisher", publisherDTO);
				return "admin/publisher/publisherNew";
			}
			if(fileImage.isEmpty()) {
				System.out.println("File ảnh trống !!");
				
				//set ảnh mặc định
				publisherDTO.setImage("avatar.png");
			} else {
				//set anh
				publisherDTO.setImage(fileImage.getOriginalFilename());
				File saveFile = new ClassPathResource("static/image/publisher").getFile();
				Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + fileImage.getOriginalFilename());
				
				Files.copy(fileImage.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
				System.out.println("Thêm ảnh thành công!!");
			}
			Publisher publisher = new Publisher();
			//chuuyen kieu thanh entity
			modelMapper.map(publisherDTO, publisher);
			this.publisherService.save(publisher);
			
			System.out.println("Thêm Nhà Xuất Bản thành công!!");
			
			model.addAttribute("publisher", publisherDTO);
			session.setAttribute("message", new MessageResponse("Thêm Nhà Xuất Bản thành công!!", "success"));
			return "redirect:/admin/publisher";
		} catch (Exception e) {
			System.out.println("Thêm Nhà Xuất Bản thất bại!!");
			session.setAttribute("message", new MessageResponse("Thêm Nhà Xuất Bản thất bại!!, vui lòng thử lại!", "danger"));
			String errorMessage = e.getMessage();
			LOGGER.error(errorMessage);
			return "redirect:/admin/publisher/new";
		}
	}
	//findById
	@GetMapping("/findById/{id}")
	public String publisherEdit(@PathVariable("id") Integer id , Model model, HttpSession session) throws ResourceNotFoundException {
		// kiểm tra id có giá trị ko
		System.out.println("id: " + id);
		
		Optional<Publisher> publisherOtp = this.publisherService.findById(id);
		Publisher publisher = publisherOtp.get();
		
		model.addAttribute("publisher", publisher);
		model.addAttribute("title", "Chỉnh Sửa Nhà Xuất Bản");
		return "admin/publisher/publisherEdit";
	}
	// update action
		@RequestMapping(value = "/update", method = RequestMethod.POST)
		public String updatepublisher(
				@Valid @ModelAttribute("publisher") Publisher publisherDTO ,
			BindingResult bindingResult,
			@RequestParam("imageFile") MultipartFile fileImage,
			Model model,
			HttpSession session ) {
			
			try {
				// Kiểm tra dữ liệu hợp lệ
				if(bindingResult.hasErrors()) {
					System.out.println("publisher: " + bindingResult.toString());
					//model.addAttribute("publisher", publisherDTO);
					return "admin/publisher/publisherUpdate";
				}
				// chuyển dto thành entity
				Publisher publisherEntity =  modelMapper.map(publisherDTO, Publisher.class);
				
				//lấy dữ liệu cũ
				Publisher oldpublisher = publisherService.findById(publisherEntity.getId()).get();
				
				if(!fileImage.isEmpty()) {
					System.out.println("File ảnh tồn tại !!");
					//xóa ảnh cũ
					File deleteFile = new ClassPathResource("static/image/publisher").getFile();
					File deleteAction = new File(deleteFile, oldpublisher.getImage());
					deleteAction.delete();
					
					//thêm ảnh mới
					publisherEntity.setImage(fileImage.getOriginalFilename());
					File saveFile =new ClassPathResource("static/image/publisher").getFile();
					Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + fileImage.getOriginalFilename());
					Files.copy(fileImage.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
					publisherEntity.setImage(fileImage.getOriginalFilename());
				} else {
					// dùng ảnh cũ
					publisherEntity.setImage(oldpublisher.getImage());
				}
				this.publisherService.update(publisherEntity);
				System.out.println("Thêm Nhà Xuất Bản thành công!!");
				
				//chuyển entity thành dto
				Publisher publisherResponse = modelMapper.map(publisherEntity, Publisher.class);
				
				model.addAttribute("publisher", publisherResponse);
				session.setAttribute("message", new MessageResponse("Cập nhật Nhà Xuất Bản thành công!!", "success"));
				return "redirect:/admin/publisher";
			} catch (Exception e) {
				System.out.println("Cập nhật Nhà Xuất Bản thất bại!!");
				session.setAttribute("message", new MessageResponse("Cập nhật Nhà Xuất Bản thất bại!!, vui lòng thử lại!", "danger"));
				String errorMessage = e.getMessage();
				LOGGER.error(errorMessage);
				return "admin/publisher/publisherEdit";
			}
		}
	@GetMapping("/delete/{id}")
	public String deleteOublisher(@PathVariable("id") Integer id, HttpSession session) {
		try {
			publisherService.delete(id);
			
			session.setAttribute("message", new MessageResponse("Xóa Thành Công!!,!", "success"));
			return "redirect:/admin/publisher";
		} catch (ResourceNotFoundException e) {
			String errorMessage = e.getMessage();
	        LOGGER.error(errorMessage);
	        session.setAttribute("message", new MessageResponse("Xóa Thất Bại!!,!", "danger"));
	        return "admin/publisher/publisherPage";
		}
	}
}
