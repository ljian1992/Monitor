package com.monitor.domain;

import java.io.Serializable;
import java.sql.Timestamp;

public class MonitorInfo implements Serializable{

	private int md_id;
	private float temperature;
	private float humidity;
	private double carbon_dioxide;
	private double oxygen;
	private double nitrogen;
	private double ethylene;
	private double ammonia;
	private double sulfur_dioxide;
	private Timestamp  time;
	private int car_id;

	
	public int getMd_id() {
		return md_id;
	}
	public void setMd_id(int md_id) {
		this.md_id = md_id;
	}
	public float getTemperature() {
		return temperature;
	}
	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}
	public float getHumidity() {
		return humidity;
	}
	public void setHumidity(float humidity) {
		this.humidity = humidity;
	}
	public double getCarbon_dioxide() {
		return carbon_dioxide;
	}
	public void setCarbon_dioxide(double carbon_dioxide) {
		this.carbon_dioxide = carbon_dioxide;
	}
	public double getOxygen() {
		return oxygen;
	}
	public void setOxygen(double oxygen) {
		this.oxygen = oxygen;
	}
	public double getNitrogen() {
		return nitrogen;
	}
	public void setNitrogen(double nitrogen) {
		this.nitrogen = nitrogen;
	}
	public double getEthylene() {
		return ethylene;
	}
	public void setEthylene(double ethylene) {
		this.ethylene = ethylene;
	}
	public double getAmmonia() {
		return ammonia;
	}
	public void setAmmonia(double ammonia) {
		this.ammonia = ammonia;
	}
	public double getSulfur_dioxide() {
		return sulfur_dioxide;
	}
	public void setSulfur_dioxide(double sulfur_dioxide) {
		this.sulfur_dioxide = sulfur_dioxide;
	}
	
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public int getCar_id() {
		return car_id;
	}
	public void setCar_id(int car_id) {
		this.car_id = car_id;
	}
}
