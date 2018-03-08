package com.websystique.springmvc.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;


@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Reports {

	private String date;
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCampaign_id() {
		return campaign_id;
	}

	public void setCampaign_id(String campaign_id) {
		this.campaign_id = campaign_id;
	}

	
	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	
	private String campaign_id;
	
	private String channel;
	
	private List<String> audience_segment = new ArrayList<String>();
	
	public List<String> getAudience_segment() {
		return audience_segment;
	}

	public void setAudience_segment(List<String> audience_segment) {
		this.audience_segment = audience_segment;
	}

	public List<String> getCity() {
		return city;
	}

	public void setCity(List<String> city) {
		this.city = city;
	}

	public List<String> getOs() {
		return os;
	}

	public void setOs(List<String> os) {
		this.os = os;
	}

	public List<String> getAge() {
		return age;
	}

	public void setAge(List<String> age) {
		this.age = age;
	}

	public List<String> getDevicetype() {
		return devicetype;
	}

	public void setDevicetype(List<String> devicetype) {
		this.devicetype = devicetype;
	}

	public List<String> getGender() {
		return gender;
	}

	public void setGender(List<String> gender) {
		this.gender = gender;
	}


	private List<String> city = new ArrayList<String>();
	
	private List<String> os = new ArrayList<String>();
	
	private List<String> age =  new ArrayList<String>();
	
	private List<String> devicetype = new ArrayList<String>();
	
	private List<String> gender;
	
	
	
	private String cpc;
	
	public String getCpc() {
		return cpc;
	}

	public void setCpc(String cpc) {
		this.cpc = cpc;
	}

	public String getCtr() {
		return ctr;
	}

	public void setCtr(String ctr) {
		this.ctr = ctr;
	}

	public String getConvrate() {
		return convrate;
	}

	public void setConvrate(String convrate) {
		this.convrate = convrate;
	}


	private String ctr;
	
	private String convrate;
	

    }
