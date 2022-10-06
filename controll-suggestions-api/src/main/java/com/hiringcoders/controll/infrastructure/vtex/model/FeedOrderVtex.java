package com.hiringcoders.controll.infrastructure.vtex.model;

import java.io.Serializable;
import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class FeedOrderVtex implements Serializable {

	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	private String eventId;

	private String handle;

	private String domain;

	private String state;

	private String orderId;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING
			, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSSSSSS][.SSSSSS][.SSSSS][.SSSS][.SSS][.SS][.S]'Z'"
			, timezone = "UTC")
	private ZonedDateTime currentChange;
	
}
