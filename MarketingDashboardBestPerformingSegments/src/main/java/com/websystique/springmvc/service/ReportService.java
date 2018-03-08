package com.websystique.springmvc.service;



import java.util.List;

import com.websystique.springmvc.model.Reports;



public interface ReportService {
	
	String extractReports(String metric,String dateRange, String campaignId, String channel, String details, String queryfield,String download);
	
}
