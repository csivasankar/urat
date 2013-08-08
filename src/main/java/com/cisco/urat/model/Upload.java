package com.cisco.urat.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable
@Entity
@Table(name = "urat_uploads")
public class Upload {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	@Column(name = "name", length = 200)
	private String name;

	@Column(name = "ext", length = 4)
	private String ext;

	@Column(name = "path", length = 250)
	private String path;

	@Column(name = "file_size", length = 50)
	private String size;
	
	@ManyToOne(targetEntity = BlobData.class, cascade = CascadeType.REMOVE)
	@JoinColumn(name = "blob_id")
	private BlobData blobData;

	public Integer getId() {
		return this.id;
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

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getFilePath() {
		return path + id + "." + ext;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}
	
	public BlobData getBlobData() {
		return blobData;
	}

	public void setBlobData(BlobData blobData) {
		this.blobData = blobData;
	}

	public String toString() {
		return ReflectionToStringBuilder.toString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
