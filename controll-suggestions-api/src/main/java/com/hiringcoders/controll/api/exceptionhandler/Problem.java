package com.hiringcoders.controll.api.exceptionhandler;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@JsonInclude(Include.NON_NULL)
@Getter
@Builder
@Schema(name = "ProblemInfo")
public class Problem {

	@Schema(example = "404")
	private Integer status;

	@Schema(example = "2022-07-15T11:21:50.902245498Z")
	private OffsetDateTime timestamp;

	@Schema(example = "Recurso não encontrado")
	private String title;

	@Schema(example = "Não existe um Produto com Id 1")
	private String detail;

	@Schema(example = "Não existe um Produto com Id 1")
	private String userMessage;

}