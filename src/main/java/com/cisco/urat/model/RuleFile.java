package com.cisco.urat.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.format.annotation.DateTimeFormat;

import com.cisco.urat.utilities.RequestContext;

@Configurable
@Entity
@Table(name = "urat_rule_files")
public class RuleFile {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	@JsonProperty("id")
	private Integer id;

	@Column(name = "name", length = 250)
	@JsonProperty("name")
	private String name;

	@ManyToOne(targetEntity = Upload.class)
	@JoinColumn(name = "file_id")
	@JsonIgnore
	private Upload file;

	@Column(name = "created", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "MM")
	@JsonIgnore
	private Calendar created = java.util.Calendar.getInstance();

	@Column(name = "modified")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "MM")
	@JsonIgnore
	private Calendar modified;

	@Column(name = "state")
	@NotNull
	@JsonIgnore
	private boolean state = true;

	@Column(name = "published")
	@NotNull
	@JsonIgnore
	private boolean published = false;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Upload getFile() {
		return file;
	}

	public void setFile(Upload file) {
		this.file = file;
	}

	public Calendar getCreated() {
		return created;
	}

	public void setCreated(Calendar created) {
		this.created = created;
	}

	public Calendar getModified() {
		return modified;
	}

	public void setModified(Calendar modified) {
		this.modified = modified;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

	@JsonProperty("url")
	public String getUrl() {
		String path = RequestContext.get().getContextPath();
		return path + "/" + file.getFilePath();
	}

	public String toString() {
		return ReflectionToStringBuilder.toString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
