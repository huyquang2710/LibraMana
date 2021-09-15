package com.libra.core.services;

import java.util.List;

import com.libra.core.entities.Publisher;

public interface IPublisherService extends GenericService<Publisher>{

	List<Publisher> searchAuthorByNameLike(String value);

}
