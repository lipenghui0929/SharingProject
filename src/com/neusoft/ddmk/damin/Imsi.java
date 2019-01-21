package com.neusoft.ddmk.damin;

import java.io.Serializable;

/**
 * ´®Âë
 * @author Administrator
 *
 */
public class Imsi implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2223613120604972558L;
	
	private Integer id;
	/**
	 * ¿¨ºÅ
	 */
    private String imei;
    /**
     * ºÅÂë
     */
    private String haoma;
    /**
     * ¿¨Ð¡ºÅ
     */
    private String ccid;
    /**
     * SNºÅ
     */
    private String sn;
    /**
     * »úÐÍ
     */
    private String jx;
    /**
     * °æ±¾ºÅ
     */
    private String rjbb;
    /**
     * ³§ÉÌ
     */
    private String cs;
    /**
     * CMIIT
     */
    private String cmiit;
    /**
     * A´úÂë
     */
    private String a;
    /**
     * B´úÂë
     */
    private String b;
    /**
     * ×¢²á
     */
    private String zc;
    /**
     * ×´Ì¬
     */
    private String struts;
    /**
     * ±¸×¢
     */
    private String beizhu1;
    /**
     * ±¸×¢2
     */
    private String beizhu2;
    
	public Imsi() {
	}
	
	public Imsi(Integer id, String imei, String haoma, String ccid, String sn, String jx, String rjbb, String cs,
			String cmiit, String a, String b, String zc, String struts, String beizhu1, String beizhu2) {
		super();
		this.id = id;
		this.imei = imei;
		this.haoma = haoma;
		this.ccid = ccid;
		this.sn = sn;
		this.jx = jx;
		this.rjbb = rjbb;
		this.cs = cs;
		this.cmiit = cmiit;
		this.a = a;
		this.b = b;
		this.zc = zc;
		this.struts = struts;
		this.beizhu1 = beizhu1;
		this.beizhu2 = beizhu2;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getHaoma() {
		return haoma;
	}
	public void setHaoma(String haoma) {
		this.haoma = haoma;
	}
	public String getCcid() {
		return ccid;
	}
	public void setCcid(String ccid) {
		this.ccid = ccid;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getJx() {
		return jx;
	}
	public void setJx(String jx) {
		this.jx = jx;
	}
	public String getRjbb() {
		return rjbb;
	}
	public void setRjbb(String rjbb) {
		this.rjbb = rjbb;
	}
	public String getCs() {
		return cs;
	}
	public void setCs(String cs) {
		this.cs = cs;
	}
	public String getCmiit() {
		return cmiit;
	}
	public void setCmiit(String cmiit) {
		this.cmiit = cmiit;
	}
	public String getA() {
		return a;
	}
	public void setA(String a) {
		this.a = a;
	}
	public String getB() {
		return b;
	}
	public void setB(String b) {
		this.b = b;
	}
	public String getZc() {
		return zc;
	}
	public void setZc(String zc) {
		this.zc = zc;
	}
	public String getStruts() {
		return struts;
	}
	public void setStruts(String struts) {
		this.struts = struts;
	}
	public String getBeizhu1() {
		return beizhu1;
	}
	public void setBeizhu1(String beizhu1) {
		this.beizhu1 = beizhu1;
	}
	public String getBeizhu2() {
		return beizhu2;
	}
	public void setBeizhu2(String beizhu2) {
		this.beizhu2 = beizhu2;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Imsi [id=" + id + ", imei=" + imei + ", haoma=" + haoma + ", ccid=" + ccid + ", sn=" + sn + ", jx=" + jx
				+ ", rjbb=" + rjbb + ", cs=" + cs + ", cmiit=" + cmiit + ", a=" + a + ", b=" + b + ", zc=" + zc
				+ ", struts=" + struts + ", beizhu1=" + beizhu1 + ", beizhu2=" + beizhu2 + "]";
	}
    
}
