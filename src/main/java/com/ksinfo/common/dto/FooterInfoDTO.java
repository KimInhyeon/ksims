package com.ksinfo.common.dto;

public class FooterInfoDTO {

	public String arrPageName;
	public String arrPageURL;
	public String arrPageReWriteFLG;
	
	public String getArrPageName() {
		return arrPageName;
	}
	public void setArrPageName(String arrPageName) {
		this.arrPageName = arrPageName;
	}
	public String getArrPageURL() {
		return arrPageURL;
	}
	public void setArrPageURL(String arrPageURL) {
		this.arrPageURL = arrPageURL;
	}
	public String getArrPageReWriteFLG() {
		return arrPageReWriteFLG;
	}
	public void setArrPageReWriteFLG(String arrPageReWriteFLG) {
		this.arrPageReWriteFLG = arrPageReWriteFLG;
	}
	@Override
	public String toString() {
		return "FooterInfoDTO [arrPageName=" + arrPageName + ", arrPageURL=" + arrPageURL + ", arrPageReWriteFLG="
				+ arrPageReWriteFLG + "]";
	}
	

}
