package com.libra.core.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.libra.core.entities.Publisher;
import com.libra.core.repositoies.PublisherRepository;
import com.libra.core.services.IPublisherService;
import com.libra.exception.BadResourceException;
import com.libra.exception.ResourceAlreadyExistsException;
import com.libra.exception.ResourceNotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PublisherServiceImpl implements IPublisherService{

	@Autowired
	private PublisherRepository publisherRepo;
	
	private boolean existsById(Integer id) {
		return publisherRepo.existsById(id);
	}
	//find all
	@Override
	public List<Publisher> findAll() {
		return publisherRepo.findAll();
	}
	// pagination	
	@Override
	public Page<Publisher> findByPageable(int pageNo) {
		Pageable pageable = PageRequest.of(pageNo - 1, 5);
		return publisherRepo.findAll(pageable);
	}


	@Override
	public Optional<Publisher> findById(Integer id) throws ResourceNotFoundException {
		Publisher publisher = publisherRepo.findById(id).get();
		if(publisher == null) {
			throw new ResourceNotFoundException("Không tìm thấy id: " + id);
		}
		Optional<Publisher> publisherOpt = Optional.ofNullable(publisher);
		return publisherOpt;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Publisher save(Publisher publisher) throws BadResourceException, ResourceAlreadyExistsException{
		
		// kiểm tra id + name tồn tại
		if(!StringUtils.isEmpty(publisher.getName())) {
			if(publisher.getId() != null && existsById(publisher.getId())) {
				throw new ResourceAlreadyExistsException("Nhà Xuất Bản với id: " + publisher.getId() + " đã tồn tại");
			}
			return publisherRepo.save(publisher);
		} else {
			BadResourceException exc = new BadResourceException("Lỗi!!. Không thể lưu Tác Giả");
			exc.addErrorMessage("Nhà Xuất Bản trống hoặc rỗng!!");
			throw exc;
		}
		
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void update(Publisher publisher) throws BadResourceException, ResourceNotFoundException {
		if(!StringUtils.isEmpty(publisher.getName())) {
			if(!existsById(publisher.getId())) {
				throw new ResourceNotFoundException("Không tìm thấy id: " + publisher.getId());
			}
			publisherRepo.save(publisher);
		} else {
			BadResourceException exc = new BadResourceException("Lỗi!!. Không thể lưu Tác Giả");
			exc.addErrorMessage("Nhà Xuất Bản trống hoặc rỗng!!");
			throw exc;
		}
		
	}
	
	// xóa
	@Override
	public void delete(Integer id) throws ResourceNotFoundException {
		if(!existsById(id)) {
			throw new ResourceNotFoundException("Không tìm thấy id: " + id);
		}
		publisherRepo.deleteById(id);
	}
	//tìm kiếm
	@Override
	public List<Publisher> findByNameContaining(String name) {
		return publisherRepo.findByNameContaining(name);
	}
	@Override
	public List<Publisher> searchPublisherByNameLike(String value) {
			return publisherRepo.findByNameContainingIgnoreCase(value);
	}


}
