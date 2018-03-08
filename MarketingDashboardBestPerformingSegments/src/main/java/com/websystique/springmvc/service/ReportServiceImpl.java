package com.websystique.springmvc.service;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.model.Reports;

//API gives Optimum CTR,Optimum CPC,Optimum Conv Rate data corresponding to a given campaign.
//Optimum Parameters include - Interest Segment,city,Device_properties, OS, DEVICE_TYPE,demographics - Age, Gender

@Service("reportService")
@Transactional
public class ReportServiceImpl implements ReportService{
	
	private static final AtomicLong counter = new AtomicLong();
	
	ReportDAOImpl repDAO = ReportDAOImpl.getInstance();
	
	String report = "";
	
	String campaignId = null;
	
    public String extractReports(String metric,String dateRange, String campaignId, String channel, String details, String queryfield,String download){
	
        String [] dateInterval = dateRange.split(",");
    	
        
    	
    	if(metric.contains("CTR") || metric.contains("ctr")){
    	 	report=repDAO.FetchOptimumCTRData(dateInterval[0], dateInterval[1], campaignId, channel,details,queryfield,metric,download);
		    return report;
    	}
    	
    	
    	if(metric.contains("CPC") || metric.contains("cpc")){
    	 	report=repDAO.FetchOptimumCPCData(dateInterval[0], dateInterval[1], campaignId, channel,details,queryfield,metric,download);
		    return report;
    	}
    	
    	
    	if(metric.contains("CVR") || metric.contains("cvr")){
    	 	if(campaignId.equals("390878914"))
    		report=repDAO.FetchOptimumCPCData(dateInterval[0], dateInterval[1], campaignId, channel,details,queryfield,metric,download);
    	 	else
    	    report=repDAO.FetchOptimumConvRateData(dateInterval[0], dateInterval[1], campaignId, channel,details,queryfield,metric,download);	
    	 	
    	 	return report;
    	}
    	
    	
    	if(metric.contains("CPCon") || metric.contains("cpcon")){
    		if(campaignId.equals("390878914"))
        	report=repDAO.FetchOptimumCPCData(dateInterval[0], dateInterval[1], campaignId, channel,details,queryfield,metric,download);
        	else
        	report=repDAO.FetchOptimumConvRateData(dateInterval[0], dateInterval[1], campaignId, channel,details,queryfield,metric,download);	
    		
    		return report;	
    	}
       
    	
    	return report;
    
    
    
    
    
    
    
    
    
    
    }
		
}
