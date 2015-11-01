package asu.cse598.creationalpatterns.dao;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class GradeItem {

	Map <String, String> gradeItemList = new LinkedHashMap<String, String>();

	public Map<String, String> getGradeItemList() {
		return gradeItemList;
	}

	public void setGradeItemList(Map<String, String> gradeItemList) {
		this.gradeItemList = gradeItemList;
	}

	public void addGradeItem(String key, String value)
	{
		this.gradeItemList.put(key, value);
	}
	
	@Override
	public String toString() {
		return "GradeItem [gradeItemList=" + gradeItemList + "]";
	}
	
	
}
