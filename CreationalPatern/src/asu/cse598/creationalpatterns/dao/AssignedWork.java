package asu.cse598.creationalpatterns.dao;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class AssignedWork {
	
	//Map of Category and related AssignedWork
	Map<String, GradedWork> assignedWorkList = new LinkedHashMap<String, GradedWork>();
	
	public Map<String, GradedWork> getAssignedWorkList() {
		return assignedWorkList;
	}
	public void setAssignedWorkList(Map<String, GradedWork> assignedWorkList) {
		this.assignedWorkList = assignedWorkList;
	}
	
	
	
	public void addAssignedWork(String key, GradedWork value)
	{
		this.assignedWorkList.put(key, value);
	}
	
	public GradedWork getAssignedWork(String category)
	{
		return assignedWorkList.get(category);
	}
	
	@Override
	public String toString() {
		return "AssignedWork  + assignedWorkList=" + assignedWorkList + "]";
	}
	
	
}
