package com.cuberoot.util;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.websystique.springmvc.model.Reports;


/**
 * Utility for converting ResultSets into DTO
 */
public class DTOPopulator {
    /**
     * Populate a result set into DTO
     
     */
    
	public static String idealCities = "";
	public static HashSet<String> P1cities;
	public static HashSet<String> P2cities;
	public static String idealGender = "";
	public static HashSet<String> P1genderdata;
	public static HashSet<String> P2genderdata;
	public static String idealAgegroup = "";
	public static HashSet<String>P1agegroupdata;
	public static HashSet<String>P2agegroupdata;
	public static String idealDeviceType = "";
	public static HashSet<String>P1devicedata;
	public static HashSet<String>P2devicedata;
	public static String idealOs = "";
	public static HashSet<String>P1osdata;
	public static HashSet<String>P2osdata;
	public static String idealSegments = "";
	public static HashSet<String>P1segmentdata;
	public static HashSet<String>P2segmentdata;
	
	public static List<Reports> populateDTO(ResultSet resultSet, String details)
            throws Exception {
       
    	   List<Reports> report = new ArrayList<Reports>();
           Reports reportDTO = null;
           List<String> topcities = new ArrayList<String>();
    	   String name;
           while (resultSet.next()) {
        	int total_rows = resultSet.getMetaData().getColumnCount();
            Reports obj = new Reports();
            for (int i = 0; i < total_rows; i++) {
               name = resultSet.getMetaData().getColumnLabel(i + 1).toLowerCase();
              
               
            
               if( name.equals("analyseddate"))
            	 obj.setDate(resultSet.getObject(i + 1).toString());
            
               if( name.equals("campaignid"))
            	  obj.setCampaign_id(resultSet.getObject(i + 1).toString());
            
              if( name.equals("channel"))   
            	   obj.setChannel(resultSet.getObject(i + 1).toString());
                             
            
              if( name.equals("optimisationparameter")){
            	  if(resultSet.getObject(i + 1).toString().equals("CITY")){
            		 // idealCities = Concatenate(resultSet.getString("optimumvalues"),"City");
                      String city = resultSet.getString("optimumvalues");
                      String [] parts = city.split("P2");
  				      List<String> data = new ArrayList<String>();
                      if(details.equals("true")){
                    	  if(parts[0]!=null )
      				    	  data.add("P1:"+parts[0].replace("P1","").replace(":",""));
        				      if(parts.length>1 && parts[1]!=null )
        				      data.add("P2:"+parts[1].replace("P2","").replace(":",""));
                      }
                      else{
                    	  
                    	 
                          String citydata = "P1:"+parts[0].replace("P1","").replace(":","");
                    	 
          			      String [] cityList = citydata.split(",");
          			      List<String> citylist = Arrays.asList(cityList);
          			      if( citylist.size() > 9){
          			      topcities = citylist.subList(0,10);	
          			      }
          			      else{
          			    	
          			    	  topcities = citylist.subList(0,citylist.size());  
          			    	  
          			      }
          			    	  
          			      String list = new ObjectMapper().writeValueAsString(topcities);
          				  list = list.replace("[", "").replace("]","").replace("\\","").replace("\"", "");
          				  data.add(list);
          				
                          
                          
                    	 
                      }
  				      obj.setCity(data);
            		  
            		  
            	  }
            	
            	  if(resultSet.getObject(i + 1).toString().equals("Audience_Segment")){
                	 String audience_segment = resultSet.getString("optimumvalues");
                	 String [] parts = audience_segment.split("P2");
 				     List<String> data = new ArrayList<String>();
 				     if(details.equals("true")){
 				    	 if(parts[0]!=null )
 	  				    	  data.add("P1:"+parts[0].replace("P1","").replace(":",""));
 	    				      if(parts[1]!=null && parts.length>1)
 	    				      data.add("P2:"+parts[1].replace("P2","").replace(":",""));
 	                      }
 	                      else{
 	                    	  
 	                    	  data.add("P1:"+parts[0].replace("P1","").replace(":","")); 
 	                      }
           		     obj.setAudience_segment(data);
            	  
            	  }
            	 
            	  
            	  if(resultSet.getObject(i + 1).toString().equals("OS")){
            		  String os = resultSet.getString("optimumvalues");
                 	  String [] parts = os.split("P2");
  				      List<String> data = new ArrayList<String>();
  				      if(details.equals("true")){
    				      if(parts[0]!=null )
  				    	  data.add("P1:"+parts[0].replace("P1","").replace(":",""));
    				      if(parts.length>1 && parts[1]!=null )
    				      data.add("P2:"+parts[1].replace("P2","").replace(":",""));
                        }
                        else{
                      	  
                      	  data.add("P1:"+parts[0].replace("P1","").replace(":","")); 
                        }
            		  obj.setOs(data);
            	  }
                	  
            	  if(resultSet.getObject(i + 1).toString().equals("Age")){
            		  String age = resultSet.getString("optimumvalues");
                 	  String [] parts = age.split("P2");
  				      List<String> data = new ArrayList<String>();
  				      if(details.equals("true")){
  				    	 if(parts[0]!=null )
  	  				    	  data.add("P1:"+parts[0].replace("P1","").replace(":",""));
  	    				      if(parts.length>1 && parts[1]!=null )
  	    				      data.add("P2:"+parts[1].replace("P2","").replace(":",""));
                        }
                        else{
                      	  
                      	  data.add("P1:"+parts[0].replace("P1","").replace(":","")); 
                        }
            		  obj.setAge(data);
            	  }
            	  if(resultSet.getObject(i + 1).toString().equals("Gender")){
            		  String gender = resultSet.getString("optimumvalues");
                 	  String [] parts = gender.split("P2");
  				      List<String> data = new ArrayList<String>();
  				      if(details.equals("true")){
  				    	 if(parts[0]!=null )
  	  				    	  data.add("P1:"+parts[0].replace("P1","").replace(":",""));
  	    				      if(parts.length>1 && parts[1]!=null )
  	    				      data.add("P2:"+parts[1].replace("P2","").replace(":",""));
                        }
                        else{
                      	  
                      	  data.add("P1:"+parts[0].replace("P1","").replace(":","")); 
                        }
            		  obj.setGender(data);
             
            	  }
              
            	  if(resultSet.getObject(i + 1).toString().equals("Device_Type")){
            		  String devicetype = resultSet.getString("optimumvalues");
                 	  String [] parts = devicetype.split("P2");
  				      List<String> data = new ArrayList<String>();
  				      if(details.equals("true")){
  				    	 if(parts[0]!=null )
  	  				    	  data.add("P1:"+parts[0].replace("P1","").replace(":",""));
  	    				      if(parts.length>1 && parts[1]!=null )
  	    				      data.add("P2:"+parts[1].replace("P2","").replace(":",""));
                        }
                        else{
                      	  
                      	  data.add("P1:"+parts[0].replace("P1","").replace(":","")); 
                        }
            		  obj.setDevicetype(data);
             
            	  }
              
              
              
              }  
           
                         
              if(name.equals("ctr"))
                  obj.setCtr("1"); 
            
              
              if(name.equals("cpc"))
                  obj.setCpc("1");
            
            
              if(name.equals("cvr"))
                  obj.setConvrate("1");
            
            
            
            }
            report.add(obj);
        
        }
        return report;
    }
    

public static String Concatenate(String input,String targetingdomain){
	
	String [] cityparts;
	String [] p1cityparts;
	String [] p2cityparts;
	String [] osparts;
	String [] p1osparts;
	String [] p2osparts;
	String [] agegroupparts;
	String [] p1agegroupparts;
	String [] p2agegroupparts;
	String [] genderparts;
	String [] p1genderparts;
	String [] p2genderparts;
	String [] segmentparts;
	String [] p1segmentparts;
	String [] p2segmentparts;
	String [] deviceparts;
	String [] p1deviceparts;
	String [] p2deviceparts;
	String priorityString = null;
	
	if(targetingdomain.equals("City")){
	//if(idealCities.equals("")){
	//	cityparts = idealCities.split(",");
	//}
	//else	
	cityparts = input.split("P2");
	p1cityparts = cityparts[0].split(",");
	p2cityparts = cityparts[1].split(",");
	
	for(int i=0; i < p1cityparts.length ;i++){
		
		if(p1cityparts[i].contains("P1") == false)
		P1cities.add(p1cityparts[i]);
	}
	
    for(int j=0; j < p2cityparts.length ;j++){
		
		P2cities.add(p2cityparts[j]);
	}
	
	 priorityString = "P1:"+ConverttoString(P1cities)+"P2:"+ConverttoString(P2cities);
	 
    
	}
	
	if(targetingdomain.equals("OS")){
	//	if(idealOs.equals("")){
		//	osparts = idealOs.split(",");
	//	}
	//	else
		osparts = input.split("P2");
		p1osparts = osparts[0].split(",");
		p2osparts = osparts[1].split(",");
		
		for(int i=0; i < p1osparts.length ;i++){
			
			if(p1osparts[i].contains("P1") == false)
			P1osdata.add(p1osparts[i]);
		}
		
	    for(int j=0; j < p2osparts.length ;j++){
			
			P2osdata.add(p2osparts[j]);
		}
	
	    
	    priorityString = "P1:"+ConverttoString(P1osdata)+"P2:"+ConverttoString(P2osdata);
	    
	}		
		
	if(targetingdomain.equals("Age")){
		
	//	if(idealAgegroup.equals("")){
			
			//agegrouppart = 
	//	}
	//	else
	    agegroupparts = input.split("P2");
		p1agegroupparts = agegroupparts[0].split(",");
		p2agegroupparts = agegroupparts[1].split(",");
		
		for(int i=0; i < p1agegroupparts.length ;i++){
			
			if(p1agegroupparts[i].contains("P1") == false)
			P1agegroupdata.add(p1agegroupparts[i]);
		}
		
	    for(int j=0; j < p2agegroupparts.length ;j++){
			
			P2agegroupdata.add(p2agegroupparts[j]);
		}
		
	    priorityString = "P1:"+ConverttoString(P1agegroupdata)+"P2:"+ConverttoString(P2agegroupdata);
	
	}	
		
	if(targetingdomain.equals("Gender")){
		
		genderparts = input.split("P2");
		p1genderparts = genderparts[0].split(",");
		p2genderparts = genderparts[1].split(",");
		
		for(int i=0; i < p1genderparts.length ;i++){
			
			if(p1genderparts[i].contains("P1") == false)
			P1genderdata.add(p1genderparts[i]);
		}
		
	    for(int j=0; j < p2genderparts.length ;j++){
			
			P2genderdata.add(p2genderparts[j]);
		}
		
	    priorityString = "P1:"+ConverttoString(P1genderdata)+"P2:"+ConverttoString(P2genderdata);

	}	
		
	if(targetingdomain.equals("segment")){
		
		segmentparts = input.split("P2");
		p1segmentparts = segmentparts[0].split(",");
		p2segmentparts = segmentparts[1].split(",");
		
		for(int i=0; i < p1segmentparts.length ;i++){
			
			if(p1segmentparts[i].contains("P1") == false)
			P1segmentdata.add(p1segmentparts[i]);
		}
		
	    for(int j=0; j < p2segmentparts.length ;j++){
			
			P2segmentdata.add(p2segmentparts[j]);
		}
		
	    priorityString = "P1:"+ConverttoString(P1segmentdata)+"P2:"+ConverttoString(P2segmentdata);
	}		
	if(targetingdomain.equals("devicetype")){
		
		deviceparts = input.split("P2");
		p1deviceparts = deviceparts[0].split(",");
		p2deviceparts = deviceparts[1].split(",");
		
		for(int i=0; i < p1deviceparts.length ;i++){
			
			if(p1deviceparts[i].contains("P1") == false)
			P1devicedata.add(p1deviceparts[i]);
		}
		
	    for(int j=0; j < p2deviceparts.length ;j++){
			
			P2devicedata.add(p2deviceparts[j]);
		}

	    priorityString = "P1:"+ConverttoString(P1devicedata)+"P2:"+ConverttoString(P2devicedata);
	    
	    
}

return priorityString;


}

public static String ConverttoString(HashSet<String> set){

	String priorityString="";
	
	for(String s:set) {
		  priorityString += ","+s;
	
		}

   priorityString = priorityString.substring(1,priorityString.length());

   return priorityString;

}	
	
}