package com.neusoft.ddmk.damin;

import java.io.Serializable;
import java.util.Date;
/**
 * ����
 * @author Administrator
 *
 */
public class Fsb implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 77466586575595284L;
	
	private String id;
	/*
	 * ����
	 */
	private String mc;
    /*
     * �ǳ�
     */
	private String nc;

	
	private String gh;

	//�˿ں�
	private Integer dkh;

	//���غ�
	private Integer kch;

	/*
	 * ����
	 */
	private String imsi;

	//������
	private String bjh;
	
	//����
	private String sjh;
	
	private String lx;
	
	private String nr;
	
	//����
	private Integer cs;
	
	//��Ϣ
	private String xx;
	
	//ʱ��
	private Date sj;
	
	//��ע
	private String bz;
	
	//״̬
	private String zt;
	
	//�Ƿ�ѡ
	private boolean isSelect = false;
	

	/*=====================================================================*/
	private boolean isQueryforSj;
	private boolean isQueryforSjh;
	private boolean isQueryforBjh;

	public Fsb() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public String getBjh() {
		return bjh;
	}

	public void setBjh(String bjh) {
		this.bjh = bjh;
	}

	public String getSjh() {
		return sjh;
	}

	public void setSjh(String sjh) {
		this.sjh = sjh;
	}

	public Integer getCs() {
		return cs;
	}

	public void setCs(Integer cs) {
		this.cs = cs;
	}

	public String getXx() {
		return xx;
	}

	public void setXx(String xx) {
		this.xx = xx;
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

	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}


	public String getLx() {
		return lx;
	}


	public void setLx(String lx) {
		this.lx = lx;
	}


	public String getNr() {
		return nr;
	}


	public void setNr(String nr) {
		this.nr = nr;
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


	public boolean isSelect() {
		return isSelect;
	}


	public void setSelect(boolean isSelect) {
		this.isSelect = isSelect;
	}


	@Override
	public String toString() {
		return "Fsb [id=" + id + ", mc=" + mc + ", nc=" + nc + ", gh=" + gh + ", dkh=" + dkh + ", kch=" + kch
				+ ", imsi=" + imsi + ", bjh=" + bjh + ", sjh=" + sjh + ", lx=" + lx + ", nr=" + nr + ", cs=" + cs
				+ ", xx=" + xx + ", sj=" + sj + ", bz=" + bz + ", zt=" + zt + "]";
	}

	

}
