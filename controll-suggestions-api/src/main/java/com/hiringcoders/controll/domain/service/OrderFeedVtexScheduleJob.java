package com.hiringcoders.controll.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class OrderFeedVtexScheduleJob {
	
	private OrderFeedVtexJobService orderFeedJobService;
	
	@Autowired
	public OrderFeedVtexScheduleJob(OrderFeedVtexJobService orderFeedJobService) {
		this.orderFeedJobService = orderFeedJobService;
	}
	
	@Scheduled(cron = "0 0/1 * * * ?")
	public void orderFeedJob() {
		orderFeedJobService.doJob();
	}

}
