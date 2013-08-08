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
@Table(name = "urat_documents")
public class Document {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	@JsonProperty("id")
	private Integer id;

	@Column(name = "name", length = 250)
	@JsonProperty("name")
	private String name;

	@ManyToOne(targetEntity = Item.class)
	@JoinColumn(name = "category_id")
	@JsonIgnore
	private Item category;

	@ManyToOne(targetEntity = Item.class)
	@JoinColumn(name = "stage_id")
	@JsonIgnore
	private Item stage;

	@Column(name = "description", length = 2000)
	@JsonIgnore
	private String description;

	@Column(name = "file_type")
	@JsonIgnore
	private Integer fileType;

	@Column(name = "document_type")
	@JsonIgnore
	private Integer documentType;

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

	@JsonProperty("category")
	public String getCategoryName() {
		return category != null ? category.getName() : "";
	}

	@JsonProperty("stage")
	public String getStageName() {
		return stage != null ? stage.getName() : "";
	}

	public Item getCategory() {
		return category;
	}

	public void setCategory(Item category) {
		this.category = category;
	}

	public Item getStage() {
		return stage;
	}

	public void setStage(Item stage) {
		this.stage = stage;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getFileType() {
		return fileType;
	}

	public void setFileType(Integer fileType) {
		this.fileType = fileType;
	}

	public Integer getDocumentType() {
		return documentType;
	}

	public void setDocumentType(Integer documentType) {
		this.documentType = documentType;
	}

	public void setFile(Upload file) {
		this.file = file;
	}

	public Upload getFile() {
		return file;
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

	@JsonProperty("verb")
	public String getVerb() {
		return isState() ? "add" : "delete";
	}

	@JsonProperty("url")
	public String getUrl() {
		if (file != null) {
			String path = RequestContext.get().getContextPath();
			return path + "/" + file.getFilePath();
		}
		return null;
	}

	public String toString() {
		return ReflectionToStringBuilder.toString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
