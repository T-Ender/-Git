package com.review;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class TestWeather {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestWeather tWeather = new TestWeather();
		ArrayList<HashMap<String,Object>> list = tWeather.getWeather("D:\\coursewareResource\\兰州大方培训\\企业培训\\试题\\天气预报报文.txt");
		System.out.println(list);

	}
	/*
	 *  1.FSCI50 BCSY 242245 BBB 
		2.2008042500时沈阳区域中心订正预报产品 
		3.SPCC    2008042500 www.docin.com 7 2 
		4.兰州市 晴 10.0   -5.0  风力3级  西北风
		5.天水市 晴 12.0   -5.0  风力4级  东南风 
		6.陇南市 晴 15.0   3.0  风力2级  西风
		7.定西市 晴 9.0   -5.0  风力1级  北风  
		8.庆阳市 晴 8.0   -5.0  风力5级  西北风 
		9.武威市 晴 12.0   -4.0  风力6级  北风 
		10.金昌市 晴 11.0   -5.0  风力1级  西风  
		
		要求
		1、读取文件内容，读出当前报文是哪一天的预报
		2、要求返回ArrayList集合，。
		思路一:HashMap<String,Object> ("name",兰州市),("weather",晴)……；
		思路二:Weather private String name;private String weather;private Double tem_max……；
		包含甘肃省内市的预报内容，包括元素：市名、天气现象、高温、低温、风力、风向
	 */
	
	public ArrayList<HashMap<String, Object>> getWeather(String filePath) {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		// 1、读取文件
		BufferedReader reader = null;
		try {
			FileInputStream in = new FileInputStream(filePath);
			InputStreamReader isr = new InputStreamReader(in, "UTF-8");
			reader = new BufferedReader(isr);
			String row = null;// 行的数据
			int rowIndex = 1;// 行号
			Date time = null;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
			while ((row = reader.readLine()) != null) {
				// 2、解析文件
				if (rowIndex == 2) {
					time = sdf.parse(row.substring(0,10));
				}
				if (rowIndex > 3) {
					// 3、向list集合里面添加解析完的数据。
					//  兰州市 晴 10.0   -5.0  风力3级  西北风
					String[] rowData = row.split(" +");
					//思路一:HashMap<String,Object> ("name",兰州市),("weather",晴)……；
					HashMap<String,Object> map = new HashMap<String,Object> ();
					map.put("time", time);
					map.put("name", rowData[0]);
					map.put("weather", rowData[1]);
					map.put("tem_max", Double.parseDouble(rowData[2]));
					map.put("tem_min", Double.parseDouble(rowData[3]));
					map.put("wind_s", rowData[4]);
					map.put("wind_d", rowData[5]);
					//4、向list集合添加map数据 
					list.add(map);
				}
				rowIndex++;
			}
		} catch (Exception e) {

		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (Exception e) {

			}
		}
		
		
		// 4、返回list 集合
		return list;
	}

}
