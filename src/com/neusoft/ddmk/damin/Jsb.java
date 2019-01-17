package com.neusoft.ddmk.damin;

import java.util.Date;

public class Jsb {

	private Integer id;

	private String mc;

	private String nc;

	private String gh;

	private Integer dkh;

	private Integer kch;

	private String imsi;

	private String sjh;

	private String bjh;

	private String nr;
	
	private Date sj;
	
	private String bz;
	
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
	
	

}
