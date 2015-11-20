package com.htsoft.oa.model.flow;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * ProcessForm Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * 
 */
public class ProcessForm extends com.htsoft.core.model.BaseModel {

    protected Long formId;
	protected String activityName;
	protected com.htsoft.oa.model.flow.ProcessRun processRun;

	protected java.util.Set formDatas = new java.util.HashSet();
	protected java.util.Set formFiles = new java.util.HashSet();

	/**
	 * Default Empty Constructor for class ProcessForm
	 */
	public ProcessForm () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class ProcessForm
	 */
	public ProcessForm (
		 Long in_formId
        ) {
		this.setFormId(in_formId);
    }

	
	public com.htsoft.oa.model.flow.ProcessRun getProcessRun () {
		return processRun;
	}	
	
	public void setProcessRun (com.htsoft.oa.model.flow.ProcessRun in_processRun) {
		this.processRun = in_processRun;
	}

	public java.util.Set getFormDatas () {
		return formDatas;
	}	
	
	public void setFormDatas (java.util.Set in_formDatas) {
		this.formDatas = in_formDatas;
	}

	public java.util.Set getFormFiles () {
		return formFiles;
	}	
	
	public void setFormFiles (java.util.Set in_formFiles) {
		this.formFiles = in_formFiles;
	}
    

	/**
	 * 	 * @return Long
     * @hibernate.id column="formId" type="java.lang.Long" generator-class="native"
	 */
	public Long getFormId() {
		return this.formId;
	}
	
	/**
	 * Set the formId
	 */	
	public void setFormId(Long aValue) {
		this.formId = aValue;
	}	

	/**
	 * 所属运行流程	 * @return Long
	 */
	public Long getRunId() {
		return this.getProcessRun()==null?null:this.getProcessRun().getRunId();
	}
	
	/**
	 * Set the runId
	 */	
	public void setRunId(Long aValue) {
	    if (aValue==null) {
	    	processRun = null;
	    } else if (processRun == null) {
	        processRun = new com.htsoft.oa.model.flow.ProcessRun(aValue);
	        processRun.setVersion(new Integer(0));//set a version to cheat hibernate only
	    } else {
			processRun.setRunId(aValue);
	    }
	}	

	/**
	 * 活动或任务名称	 * @return String
	 * @hibernate.property column="activityName" type="java.lang.String" length="128" not-null="true" unique="false"
	 */
	public String getActivityName() {
		return this.activityName;
	}
	
	/**
	 * Set the activityName
	 * @spring.validator type="required"
	 */	
	public void setActivityName(String aValue) {
		this.activityName = aValue;
	}	

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof ProcessForm)) {
			return false;
		}
		ProcessForm rhs = (ProcessForm) object;
		return new EqualsBuilder()
				.append(this.formId, rhs.formId)
						.append(this.activityName, rhs.activityName)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.formId) 
						.append(this.activityName) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("formId", this.formId) 
						.append("activityName", this.activityName) 
				.toString();
	}



}
