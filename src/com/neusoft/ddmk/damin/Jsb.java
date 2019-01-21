package com.neusoft.ddmk.damin;

import java.io.Serializable;
import java.util.Date;
/**
 *  接收
 * @author Administrator
 *
 */
public class Jsb implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

	/*
	 * 名称
	 */
	private String mc;
    /*
     * 昵称
     */
	private String nc;

	
	private String gh;

	//端口号
	private Integer dkh;

	//卡池号
	private Integer kch;

	private String imsi;

	//接收号
	private String sjh;

	//本机号
	private String bjh;
	
	/*
	 *  短信内容
	 */
	private String nr;
	
	/*
	 * 时间
	 */
	private Date sj;
	
	/*
	 * 备注
	 */
	private String bz;
	
	
	/*=====================================================================*/
	private boolean isQueryforSj;
	private boolean isQueryforSjh;
	private boolean isQueryforBjh;
	
	public String toString(){
		return "id:"+id+"; mc:"+mc+"; nc:"+nc+"; gh:"+gh+"; dkh:"+dkh+"; kch:"+kch+"; imsi:"+imsi+"; sjh:"+sjh+"; bjh:"+bjh+"; nr:"+nr+";sj:"+sj+";bz:"+bz;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMc() {
		return mc;
	}

	public void setMc(String mc) {
		this.mc = mc;
	}

	public String getNc() {
		return nc;
	}

	public void setNc(String nc) {
		this.nc = nc;
	}

	public String getGh() {
		return gh;
	}

	public void setGh(String gh) {
		this.gh = gh;
	}

	public Integer getDkh() {
		return dkh;
	}

	public void setDkh(Integer dkh) {
		this.dkh = dkh;
	}

	public Integer getKch() {
		return kch;
	}

	public void setKch(Integer kch) {
		this.kch = kch;
	}

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getSjh() {
		return sjh;
	}

	public void setSjh(String sjh) {
		this.sjh = sjh;
	}

	public String getBjh() {
		return bjh;
	}

	public void setBjh(String bjh) {
		this.bjh = bjh;
	}

	public String getNr() {
		return nr;
	}

	public void setNr(String nr) {
		this.nr = nr;
	}

	public Date getSj() {
		return sj;
	}

	public void setSj(Date sj) {
		this.sj = sj;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public boolean isQueryforSj() {
		return isQueryforSj;
	}

	public void setQueryforSj(boolean isQueryforSj) {
		this.isQueryforSj = isQueryforSj;
	}

	public boolean isQueryforSjh() {
		return isQueryforSjh;
	}

	public void setQueryforSjh(boolean isQueryforSjh) {
		this.isQueryforSjh = isQueryforSjh;
	}

	public boolean isQueryforBjh() {
		return isQueryforBjh;
	}

	public void setQueryforBjh(boolean isQueryforBjh) {
		this.isQueryforBjh = isQueryforBjh;
	}

	

}
