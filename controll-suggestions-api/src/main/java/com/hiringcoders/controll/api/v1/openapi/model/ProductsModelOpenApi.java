package com.hiringcoders.controll.api.v1.openapi.model;

import com.hiringcoders.controll.api.v1.model.ProductModel;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "ProductsModelPage")
public class ProductsModelOpenApi extends PagedModelOpenApi<ProductModel> {

}
