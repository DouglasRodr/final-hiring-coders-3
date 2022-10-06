package com.hiringcoders.controll.api.v1.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hiringcoders.controll.api.v1.model.ProductSummaryModel;
import com.hiringcoders.controll.api.v1.model.assembler.ProductSummaryModelAssembler;
import com.hiringcoders.controll.api.v1.openapi.controller.ProductSuggestionControllerOpenApi;
import com.hiringcoders.controll.domain.model.ProductCombination;
import com.hiringcoders.controll.domain.repository.ProductCombinationRepository;
import com.hiringcoders.controll.domain.service.ProductRegistrationService;

@RestController
@RequestMapping(path = "/v1/products/{productId}/suggestions", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductSuggestionController implements ProductSuggestionControllerOpenApi {
	
	@Autowired
	private ProductRegistrationService productRegistration;
	
	@Autowired
	private ProductCombinationRepository productCombinationRepository;
	
	@Autowired
	private ProductSummaryModelAssembler productSummaryModelAssembler;
	
	@PreAuthorize("hasAuthority('SCOPE_ADMIN') or hasAuthority('SCOPE_STORE')")
	@GetMapping
	public ResponseEntity<List<ProductSummaryModel>> listSuggestionsFromProduct(@PathVariable Long productId) {
		var product = productRegistration.findProductById(productId);

		var suggestions = productCombinationRepository
				.findActiveByProductId(product.getId())
				.stream().map(ProductCombination::getCombinedProduct)
				.collect(Collectors.toList());

		return ResponseEntity.ok()
			      .cacheControl(CacheControl.maxAge(1, TimeUnit.MINUTES))
			      .body(productSummaryModelAssembler.toCollectionModel(suggestions));
	}

}
