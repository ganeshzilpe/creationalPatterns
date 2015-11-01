package asu.cse598.creationalpatterns.dao;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class GradedWork {
	
	Map <String, String> gradedWorkList = new LinkedHashMap<String, String>();

	public Map<String, String> getGradedWorkList() {
		return gradedWorkList;
	}

	public void setGradedWorkList(Map<String, String> gradedWorkList) {
		this.gradedWorkList = gradedWorkList;
	}
	
	public void addGradedWork(String key, String value)
	{
		this.gradedWorkList.put(key, value);
	}

	@Override
	public String toString() {
		return "GradedWork [gradedWorkList=" + gradedWorkList + "]";
	}
	
	

}
