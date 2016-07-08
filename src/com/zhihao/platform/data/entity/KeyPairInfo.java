package com.zhihao.platform.data.entity;

public class KeyPairInfo {
	public String exponent ;
	public String modules ;
	
	public KeyPairInfo(String exponent,String modules) {
		this.exponent = exponent;
		this.modules = modules;
	}
	
	public String getExponent() {
		return exponent;
	}
	public void setExponent(String exponent) {
		this.exponent = exponent;
	}
	public String getModules() {
		return modules;
	}
	public void setModules(String modules) {
		this.modules = modules;
	}
}
