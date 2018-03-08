package com.websystique.springmvc.service;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jsontocsv.parser.JSONFlattener;
import org.jsontocsv.writer.CSVWriter;

import com.cuberoot.util.DBConnector;
import com.cuberoot.util.DTOPopulator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.websystique.springmvc.model.Reports;


public class ReportDAOImpl {

	

	private static ReportDAOImpl INSTANCE;

	private static final Logger logger = Logger.getLogger(ReportDAOImpl.class);

	public static ReportDAOImpl getInstance() {
		
		if(INSTANCE == null)
			return new ReportDAOImpl();
		else
		return INSTANCE;
	}

	public String FetchOptimumCTRData(String startDate, String endDate,String campaignId, String channel, String details, String queryfield, String metric,String download) {
		Connection connection = null;
		Statement preparedStatement = null;
		ResultSet rs = null;
		String QueryString = null;
		List<Reports> obj1 = null;
		List<Reports> obj2 = null;
	//	JSONObject jo = new JSONObject();
		List<Reports> obj3 = null;
		int ReportCode = 1;
		String date; String audience_segment;
		String city; String os;String campId;
		String out = "";
		List<String> data = new ArrayList<String>();
		String filename = "";
		if(queryfield.equals("city"))
		{
			queryfield = "CITY";
		    filename = "city.csv";
		
		}
		
		else if (queryfield.equals("gender"))		
		{
			queryfield = "Gender";
            filename = "gender.csv";
		}
			
		else if (queryfield.equals("age"))
		{
			queryfield = "Age";
		    filename = "agegroup.csv";
		}
		
		else if (queryfield.equals("device"))
		{
			
			queryfield = "Device_Type";
		    filename = "device.csv";
		}
		
		else if (queryfield.equals("os"))
		{
			
			queryfield = "OS";
		    filename = "os.csv";
		
		}
		
		else if (queryfield.equals("audience_segment"))
		{
			
			queryfield = "Audience_Segment";
		    filename = "audiencesegment.csv";
		}
		
		
		
		
		try {
			DBConnector obj = new DBConnector();
			connection = obj.getPooledConnection();

			if (connection != null) {

			//	QueryString = "Select OptimisationValue,date,campaign_id,channel,CTR FROM idealtargetingparameters WHERE CTR = 1 AND date between "
			//			+ "'"+startDate + "' AND '" + endDate + "' GROUP by date,campaign_id,channel";
				if(campaignId.equals("all")){
					QueryString = "SELECT OptimumValues,AnalysedDate,CampaignId,Channel,CTR,OptimisationParameter FROM idealtargetingparameters WHERE OptimisationParameter Like '%"+queryfield+"%' AND CTR = 1 AND AnalysedDate LIKE "+"'%"+startDate+"%'"+" GROUP BY AnalysedDate,CampaignId,Channel,OptimisationParameter";
				}
				else{
				QueryString = "SELECT OptimumValues,AnalysedDate,CampaignId,Channel,CTR,OptimisationParameter FROM idealtargetingparameters WHERE OptimisationParameter Like '%"+queryfield+"%' AND CTR = 1 AND AnalysedDate LIKE "+"'%"+startDate+"%'"+" AND CampaignId in ("+campaignId+") GROUP BY AnalysedDate,CampaignId,Channel,OptimisationParameter";
				}
				System.out.println(QueryString+"\n");
				
				preparedStatement = connection.createStatement();
				
				rs = preparedStatement.executeQuery(QueryString);

			//	int count = 0;

			//	while (rs.next()) {
			//	    ++count;
				    // Get data from the current row and use it
			//	}
				
			//	System.out.println(count);
				
				
				obj1=DTOPopulator.populateDTO(rs,details);
               
				/*
				for(int i =0; i< obj3.size(); i++){
					date=obj3.get(i).getDate();
					
					channel=obj3.get(i).getChannel();
					audience_segment=obj3.get(i).getAudience_segment();
					city=obj3.get(i).getCity();
					os=obj3.get(i).getOs();
					campId = obj3.get(i).getCampaign_id();
					Map<String, Object> campData = null;
					campData=IndexCategoriesData.putJsonDocument(campId,date,channel, audience_segment,city,os, device_type, conversions);
					System.out.println(campData);
					IndexCategoriesData.postElasticSearch(campData,"reportdata");
				
				//	jo.put("data",obj1);
				
				}	//Resultset to json converter
			        */
			   
				out = new ObjectMapper().writeValueAsString(obj1);
			  
				out = out.replace("P1","Priority1").replace("P2","Priority2");
				/*	
               if(details.equals("true")){
					
					String [] parts = out.split("P2");
				    data.add("P1:"+parts[0].replace("P1","").replace(":",""));
				    data.add("P2:"+parts[1].replace("P2","").replace(":",""));
				    }
					
					else{
				    	
				    	out = out.substring(0,out.indexOf("P2")-1).replace("[{","");
				    	data.add(out);
				    }
			  */  	
				
				
				if(download.equals("true"))
				 {
				 List<Map<String, String>> flatJson = JSONFlattener.parseJson(out);
				// Using the default separator ','
				// If you want to use an other separator like ';' or '\t' use
				// CSVWriter.getCSV(flatJSON, separator) method
				 CSVWriter.writeToFile(CSVWriter.getCSV(flatJson),filename);
				 }
				
				
				
			
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
        
       finally{
    	   
    	    DBUtil.close(rs);
			DBUtil.close(preparedStatement);
			DBUtil.close(connection);
			
		} 
		
		
		return out;
	}

	public String FetchOptimumCPCData(String startDate, String endDate, String campaignId, String channel, String details, String queryfield, String metric,String download) {
		Connection connection = null;
		Statement preparedStatement = null;
		ResultSet rs = null;
		String QueryString = null;
		List<Reports> obj1 = null;
	//	JSONObject jo = new JSONObject();
		List<Reports> obj2 = null;
		//	JSONObject jo = new JSONObject();
	    List<Reports> obj3 = null;
	    String date;String impressions; String clicks; String audience_segment;
		String city; String os; String device_type; String conversions;String campId;
		String out = "";
		int ReportCode = 2;
		List<String> data = new ArrayList<String>();
		String filename = "";
		if(queryfield.equals("city"))
		{
			queryfield = "CITY";
		    filename = "city.csv";
		
		}
		
		else if (queryfield.equals("gender"))		
		{
			queryfield = "Gender";
            filename = "gender.csv";
		}
			
		else if (queryfield.equals("age"))
		{
			queryfield = "Age";
		    filename = "agegroup.csv";
		}
		
		else if (queryfield.equals("device"))
		{
			
			queryfield = "Device_Type";
		    filename = "device.csv";
		}
		
		else if (queryfield.equals("os"))
		{
			
			queryfield = "OS";
		    filename = "os.csv";
		
		}
		
		else if (queryfield.equals("audience_segment"))
		{
			
			queryfield = "Audience_Segment";
		    filename = "audiencesegment.csv";
		}
		
		try {
			DBConnector obj = new DBConnector();
			connection = obj.getPooledConnection();

			if (connection != null) {

				if(campaignId.equals("all")){
                QueryString = "SELECT OptimumValues,AnalysedDate,CampaignId,Channel,CPC,OptimisationParameter FROM idealtargetingparameters WHERE OptimisationParameter Like '%"+queryfield+"%' AND  CPC = 1 AND AnalysedDate LIKE "+"'%"+startDate+"%'"+" GROUP BY AnalysedDate,CampaignId,Channel,OptimisationParameter";
				}
				else{
					
					QueryString = "SELECT OptimumValues,AnalysedDate,CampaignId,Channel,CPC,OptimisationParameter FROM idealtargetingparameters WHERE OptimisationParameter Like '%"+queryfield+"%' AND CPC = 1 AND AnalysedDate LIKE "+"'%"+startDate+"%'"+" AND CampaignId in ("+campaignId+") GROUP BY AnalysedDate,CampaignId,Channel,OptimisationParameter";	
				}
				
				System.out.println(QueryString+"\n");
				
				preparedStatement = connection.createStatement();
				
				rs = preparedStatement.executeQuery(QueryString);

				obj1=DTOPopulator.populateDTO(rs,details);
					
				
				
				out = new ObjectMapper().writeValueAsString(obj1);
				out.replace("P1","Priority1").replace("P2","Priority2");
				
				
				if(metric.equals("CVR") || metric.equals("cvr")){
					
					
				 out=out.replace("Rourkela,Anand,Kannur,","").replace(",desktop","").replace("Jodhpur,Jammu","");
					
				}
				
				
				if(metric.equals("CPCon") || metric.equals("cpcon")){
					
					
					 out=out.replace("Rourkela,Anand,Kannur,","").replace("Namakkal,Eluru,Tezpur","").replace(",desktop","").replace("Jodhpur,Jammu","").replace("Pune,Thane","");
						
					}
				
			
				if(queryfield.equals("city")){
					
					out = out.replace("-","");
				}
				
				out=out.replace("P1","Priority1").replace("P2","Priority2");
				
				
				
				
				
				
				
				
				/*
                 if(details.equals("true")){
					
					String [] parts = out.split("P2");
				    data.add("[{P1:"+parts[0].replace("P1","").replace(":","")+"}]");
				    data.add("[{P2:"+parts[1].replace("P2","").replace(":","")+"}])");
				    }
					
					else{
				    	
				    	out = out.substring(0,out.indexOf("P2")-1)+"}]";
				    	data.add(out);
				    }
				  */  	
				// populate the array
			//	jo.put("data",obj1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

        finally{
        	
        	DBUtil.close(rs);
			DBUtil.close(preparedStatement);
			DBUtil.close(connection);
			
		} 
		
		
		
		return out;
	}

	public String FetchOptimumConvRateData(String startDate, String endDate, String campaignId, String channel, String details,String queryfield, String metric,String download) {
		Connection connection = null;
		Statement preparedStatement = null;
		ResultSet rs = null;
		String QueryString = null;
		List<Reports> obj1 = null;
		List<Reports> obj2 = null;
		//	JSONObject jo = new JSONObject();
		List<Reports> obj3 = null;
	    int ReportCode = 3;
		String out = "";
		List<String> data = new ArrayList<String>();
		
		String filename = "";
		if(queryfield.equals("city"))
		{
			queryfield = "CITY";
		    filename = "city.csv";
		
		}
		
		else if (queryfield.equals("gender"))		
		{
			queryfield = "Gender";
            filename = "gender.csv";
		}
			
		else if (queryfield.equals("age"))
		{
			queryfield = "Age";
		    filename = "agegroup.csv";
		}
		
		else if (queryfield.equals("device"))
		{
			
			queryfield = "Device_Type";
		    filename = "device.csv";
		}
		
		else if (queryfield.equals("os"))
		{
			
			queryfield = "OS";
		    filename = "os.csv";
		
		}
		
		else if (queryfield.equals("audience_segment"))
		{
			
			queryfield = "Audience_Segment";
		    filename = "audiencesegment.csv";
		}
		
		
		try {
			DBConnector obj = new DBConnector();
			connection = obj.getPooledConnection();


			
			
			
			if (connection != null) {

				if(campaignId.equals("all")){
				QueryString = "SELECT OptimumValues,AnalysedDate,CampaignId,Channel,ConvRate,OptimisationParameter FROM idealtargetingparameters WHERE OptimisationParameter Like '%"+queryfield+"%' AND ConvRate = 1 AND AnalysedDate LIKE "+"'%"+startDate+"%'"+" GROUP BY AnalysedDate,CampaignId,Channel,OptimisationParameter";
				}
				else{
					QueryString = "SELECT OptimumValues,AnalysedDate,CampaignId,Channel,ConvRate,OptimisationParameter FROM idealtargetingparameters WHERE OptimisationParameter Like '%"+queryfield+"%' AND ConvRate = 1 AND AnalysedDate LIKE "+"'%"+startDate+"%'"+" AND CampaignId in ("+campaignId+") GROUP BY AnalysedDate,CampaignId,Channel,OptimisationParameter";	
				}
					
					System.out.println(QueryString+"\n");
				
				preparedStatement = connection.createStatement();
				
				rs = preparedStatement.executeQuery(QueryString);

				obj1=DTOPopulator.populateDTO(rs,details);
				//obj2=DTOProcessor.ProcessReportDTO(obj1, startDate, endDate, ReportCode);
				//obj3=DTOFilter.FilterReportDTO(obj2,startDate, endDate, ReportCode); 	
				
				//jo.put("data",obj1);
				out = new ObjectMapper().writeValueAsString(obj1);
				out.replace("P1","Priority1").replace("P2","Priority2");
				
			
				
				/*
				if(details.equals("true")){
					
					String [] parts = out.split("P2");
				    data.add("[{P1:"+parts[0].replace("P1","").replace(":","")+"}]");
				    data.add("[{P2:"+parts[1].replace("P2","").replace(":","")+"}])");
				    }
					
					else{
				    	
				    	out = out.substring(0,out.indexOf("P2")-1)+"}]";
				    	data.add(out);
				    }
			*/
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
  
        finally{
        	
        	DBUtil.close(rs);
			DBUtil.close(preparedStatement);
			DBUtil.close(connection);
			
		} 
		
		
		return out;
	}



	

	









}
