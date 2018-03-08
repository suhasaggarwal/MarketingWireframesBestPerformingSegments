package com.websystique.springmvc.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.websystique.springmvc.model.Reports;
import com.websystique.springmvc.service.ReportService;

//Ideal Targeting paramters
//APIs to display Ideal Targeting parameters corresponding to campaign


@RestController
public class ReportRestController {

	@Autowired
	ReportService reportService;  //Service which will do all data retrieval/manipulation work

	//-------------------Retrieve Report with Id--------------------------------------------------------
	
	@RequestMapping(value = "/report/idealtargetingparams/{QueryField}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public ResponseEntity<String> getReport(@PathVariable("QueryField") String queryfield,
			@RequestParam("dateRange") String dateRange,
			@RequestParam(value = "metric", required = false) String metric,
			@RequestParam(value = "campaign_id", required = false) String campaignId,
			@RequestParam(value = "channel", required = false) String channel,
			@RequestParam(value = "details", required = false) String details
			
			) {
		System.out.println("Fetching Ideal Targeting Parameter Report with metric " + metric);
		dateRange = "2016-05-05,2016-05-05";
	    String report = reportService.extractReports(metric,dateRange,campaignId,channel,details,queryfield,"false");
		if (report == null) {
			System.out.println("Report with Ideal Targeting Parameters with metric" +metric + " not found");
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>(report, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/report/idealtargetingparams/{QueryField}/downloadCSV", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@CrossOrigin
	public void getIdealReportCSV(@PathVariable("QueryField") String queryfield,
			@RequestParam("dateRange") String dateRange,
			@RequestParam(value = "metric", required = false) String metric,
			@RequestParam(value = "campaign_id", required = false) String campaignId,
			@RequestParam(value = "channel", required = false) String channel,
			@RequestParam(value = "details", required = false) String details,
			HttpServletResponse response
			) {
		System.out.println("Fetching Ideal Targeting Parameter Report with metric " + metric);
		dateRange = "2016-05-05,2016-05-05";
	   
		
		System.out.println("Fetching CSV with"+queryfield);
		 
		String filename = "";
		try {
		      // get your file as InputStream
			 
			if(queryfield.equals("city"))
			{
				
			    filename = "city.csv";
			
			}
			
			else if (queryfield.equals("gender"))		
			{
				
                filename = "gender.csv";
			}
				
			else if (queryfield.equals("age"))
			{
				
			    filename = "agegroup.csv";
			}
			
			else if (queryfield.equals("device"))
			{
			
			    filename = "device.csv";
			}
			
			else if (queryfield.equals("os"))
			{
				
				
			    filename = "os.csv";
			
			}
			
			else if (queryfield.equals("audience_segment"))
			{
				
				queryfield = "Audience_Segment";
			    filename = "audiencesegment.csv";
			}
			
			
			response.setContentType("text/csv");      
			response.setHeader("Content-Disposition", "attachment; filename="+filename+'"'); 
			 String report = reportService.extractReports(metric,dateRange,campaignId,channel,details,queryfield,"true");
			 File initialFile = new File(filename);
			 InputStream is = new FileInputStream(initialFile);
		      // copy it to response's OutputStream
		     IOUtils.copy(is, response.getOutputStream());
		      response.flushBuffer();
		    } catch (IOException ex) {
		     
		    }
	}
	

}
