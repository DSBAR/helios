package org.helios.spring.container.templates.hibernate;

// Generated Oct 14, 2008 9:08:52 AM by Hibernate Tools 3.2.0.CR1

/**
 * MergerBean generated by hbm2java
 */
public class MergerBean implements java.io.Serializable {

	private static final long serialVersionUID = 8883140418250616013L;
	private int mergerBeanId;
	private Bean bean;
	private Merger merger;

	public MergerBean() {
	}

	public MergerBean(int mergerBeanId, Bean bean, Merger merger) {
		this.mergerBeanId = mergerBeanId;
		this.bean = bean;
		this.merger = merger;
	}

	public int getMergerBeanId() {
		return this.mergerBeanId;
	}

	public void setMergerBeanId(int mergerBeanId) {
		this.mergerBeanId = mergerBeanId;
	}

	public Bean getBean() {
		return this.bean;
	}

	public void setBean(Bean bean) {
		this.bean = bean;
	}

	public Merger getMerger() {
		return this.merger;
	}

	public void setMerger(Merger merger) {
		this.merger = merger;
	}

	/**
	 * Constructs a <code>String</code> with all attributes
	 * in name = value format.
	 *
	 * @return a <code>String</code> representation 
	 * of this object.
	 */
	public String toString()
	{
	    final String CR = "\n\t";
	
	    StringBuilder retValue = new StringBuilder();
	    
	    retValue.append("MergerBean ( ")
	        .append(CR).append("mergerBeanId:").append(this.mergerBeanId)
	        .append(CR).append("beanId:").append(this.bean.getBeanId())
	        .append(CR).append("mergerId:").append(this.merger.getMergerId())
	        .append("\n )");
	    
	    return retValue.toString();
	}
	

}