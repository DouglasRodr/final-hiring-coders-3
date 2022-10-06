package com.hiringcoders.controll.api.v1.model.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hiringcoders.controll.api.v1.model.ProductSummaryModel;
import com.hiringcoders.controll.domain.model.Product;

@Component
public class ProductSummaryModelAssembler {
	
	private ModelMapper modelMapper;
	
	@Autowired
	public ProductSummaryModelAssembler(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	public ProductSummaryModel toModel(Product product) {
		return modelMapper.map(product, ProductSummaryModel.class);
	}

	public List<ProductSummaryModel> toCollectionModel(List<Product> products) {
		return products.stream()
				.map(product -> toModel(product))
				.collect(Collectors.toList());
	}

}
