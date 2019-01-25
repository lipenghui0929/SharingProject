package com.neusoft.ddmk.damin;

import java.io.Serializable;
/**
 * 分页查询的封装
 * @author Administrator
 *
 */
public class Page implements Serializable{
	
	private static final long serialVersionUID = 2360564165530498432L;
	
	//每页条数
	private Integer pageSize;
	//当前页数
	private Integer pageNow;
	//总条数
	private Integer tatolCount;
	//总页数(需要计算)
	private Integer pageCount;
	
	
	public Page() {
		this.pageSize = 15;
		this.pageNow = 0;
	}
	
	public Page(Integer pageNow) {
		this(15,pageNow);
	}

	public Page(Integer pageSize, Integer pageNow) {
		this.pageSize = pageSize;
		this.pageNow = pageNow;
	}
	
	
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPageNow() {
		return pageNow;
	}
	public void setPageNow(Integer pageNow) {
		this.pageNow = pageNow;
	}
	public Integer getTatolCount() {
		return tatolCount;
	}
	public void setTatolCount(Integer tatolCount) {
		this.tatolCount = tatolCount;
		//计算总页数
		this.pageCount = (tatolCount%pageSize == 0)?tatolCount/pageSize : tatolCount/pageSize+1;
	}
	public Integer getPageCount() {
		return pageCount;
	}
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
	

	@Override
	public String toString() {
		return "Page [pageSize=" + pageSize + ", pageNow=" + pageNow
				+ ", tatolCount=" + tatolCount +  "]";
	}
	
	
}
