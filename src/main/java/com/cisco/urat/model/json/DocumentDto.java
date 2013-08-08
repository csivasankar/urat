package com.cisco.urat.model.json;

import java.util.ArrayList;
import java.util.List;

import com.cisco.urat.model.Document;
import com.cisco.urat.model.RuleFile;

import org.codehaus.jackson.annotate.JsonProperty;

public class DocumentDto extends BaseDto {
	@JsonProperty("Rule_Datas")
	private List<RuleFile> ruleDataFiles = new ArrayList<RuleFile>();
	@JsonProperty("Documents")
	private List<Document> documents = new ArrayList<Document>();
	@JsonProperty("Videos")
	private List<Document> videos = new ArrayList<Document>();

	public List<RuleFile> getRuleDataFiles() {
		return ruleDataFiles;
	}

	public void setRuleDataFiles(List<RuleFile> ruleDataFiles) {
		this.ruleDataFiles = ruleDataFiles;
	}

	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	public List<Document> getVideos() {
		return videos;
	}

	public void setVideos(List<Document> videos) {
		this.videos = videos;
	}
}
