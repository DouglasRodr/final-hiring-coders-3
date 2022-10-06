package com.hiringcoders.controll.api.v1.model.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hiringcoders.controll.api.v1.model.ProductCombinationModel;
import com.hiringcoders.controll.domain.model.ProductCombination;

@Component
public class ProductCombinationModelAssembler {
	
	private ModelMapper modelMapper;
	
	@Autowired
	public ProductCombinationModelAssembler(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	public ProductCombinationModel toModel(ProductCombination productCombination) {
		return modelMapper.map(productCombination, ProductCombinationModel.class);
	}

	public List<ProductCombinationModel> toCollectionModel(List<ProductCombination> productCombinations) {
		return productCombinations.stream()
				.map(productCombination -> toModel(productCombination))
				.collect(Collectors.toList());
	}

}
