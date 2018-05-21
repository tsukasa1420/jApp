package enquete.Java;

import java.io.Serializable;

public class BeanDum implements Serializable{
	protected String pri;
	protected String enqName;
	protected String url;

	public BeanDum(String pri, String enqName, String url) {
		this.pri = pri;
		this.enqName = enqName;
		this.url = url;
	}

	public String getPri()					{return pri;}
	public String getEnqName()				{return enqName;}
	public String getUrl()					{return url;}

	public void setPri(String pri)				{this.pri = pri;}
	public void setEnqName(String enqName)	{this.enqName = enqName;}
	public void setUrl(String url)				{this.url = url;}



	protected String qState;
	protected String qType;
}