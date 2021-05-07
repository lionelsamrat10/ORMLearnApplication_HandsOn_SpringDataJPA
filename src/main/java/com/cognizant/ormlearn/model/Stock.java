package com.cognizant.ormlearn.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // Specifies that this is mapped to a database table
@Table(name = "stock") // Mapped to that country table of ormlearn database
public class Stock {
	@Id // This is the primary key
	@Column(name = "st_id")
	private int id;
	@Column(name = "st_code")
	private String code;
	@Column(name = "st_date")
	private Date date;
	@Column(name = "st_open", columnDefinition = "Decimal(10,2)")
	private float open;
	@Column(name = "st_close", columnDefinition = "Decimal(10,2)")
	private float close;
	@Column(name = "st_volume", columnDefinition = "Decimal")
	private float volume;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public float getOpen() {
		return open;
	}

	public void setOpen(float open) {
		this.open = open;
	}

	public float getClose() {
		return close;
	}

	public void setClose(float close) {
		this.close = close;
	}

	public float getVolume() {
		return volume;
	}

	public void setVolume(float volume) {
		this.volume = volume;
	}

	@Override
	public String toString() {
		return "Stock [id=" + id + ", code=" + code + ", date=" + date + ", open=" + open + ", close=" + close
				+ ", volume=" + volume + "]";
	}
}
