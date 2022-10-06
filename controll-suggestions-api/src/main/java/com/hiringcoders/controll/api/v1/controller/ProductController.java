package com.hiringcoders.controll.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hiringcoders.controll.api.v1.model.ProductModel;
import com.hiringcoders.controll.api.v1.model.assembler.ProductModelAssembler;
import com.hiringcoders.controll.api.v1.openapi.controller.ProductControllerOpenApi;
import com.hiringcoders.controll.domain.filter.ProductFilter;
import com.hiringcoders.controll.domain.repository.ProductRepository;
import com.hiringcoders.controll.domain.service.ProductRegistrationService;

@RestController
@RequestMapping(path = "/v1/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController implements ProductControllerOpenApi {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductModelAssembler productModelAssembler;

	@Autowired
	private ProductRegistrationService productRegistration;

	@PreAuthorize("hasAuthority('SCOPE_ADMIN')")
	@GetMapping
	public Page<ProductModel> listProducts(ProductFilter filter, @PageableDefault(size = 10) Pageable pageable) {
		var productsPage = productRepository.findUsingFilter(filter, pageable);

		var productsModelList = productModelAssembler.toCollectionModel(productsPage.getContent());

		var productsModelPage = new PageImpl<>(productsModelList, pageable, productsPage.getTotalElements());

		return productsModelPage;
	}

	@PreAuthorize("hasAuthority('SCOPE_ADMIN')")
	@GetMapping(path = "/{productId}")
	public ProductModel findById(@PathVariable Long productId) {
		var product = productRegistration.findProductById(productId);

		return productModelAssembler.toModel(product);
	}

}
