package com.hiringcoders.controll.infrastructure.vtex.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class CommitItemFeedOrderVtex implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<String> handles;
	
	public CommitItemFeedOrderVtex() {
		this.handles = new ArrayList<String>();
	}
	
	public void addHandle(String handle) {
		this.handles.add(handle);
	}
	
	public void addHandles(List<String> handles) {
		this.handles.addAll(handles);
	}

}
