package com.hiringcoders.controll.api.v1.model.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hiringcoders.controll.api.v1.model.ProductModel;
import com.hiringcoders.controll.domain.model.Product;

@Component
public class ProductModelAssembler {
	
	private ModelMapper modelMapper;
	
	@Autowired
	public ProductModelAssembler(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	public ProductModel toModel(Product product) {
		return modelMapper.map(product, ProductModel.class);
	}

	public List<ProductModel> toCollectionModel(List<Product> products) {
		return products.stream()
				.map(product -> toModel(product))
				.collect(Collectors.toList());
	}

}
