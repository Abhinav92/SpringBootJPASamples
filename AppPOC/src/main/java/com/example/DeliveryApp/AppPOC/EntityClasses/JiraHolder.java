package com.example.DeliveryApp.AppPOC.EntityClasses;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "JIRA_HOLDER_TABLE")
public class JiraHolder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "STORY")
	private String story;

	@Column(name = "USAGE")
	private String usage;

	@Column(name = "BUSINESS")
	private String business;

	@Column(name = "EFFORT")
	private String effort;

	@ManyToOne
	@JoinColumn(name = "REPORTID", referencedColumnName = "REPORTID")
	private ReleaseParameters releaseParam;

	public JiraHolder() {
	}

	public JiraHolder(String story, String usage, String business, String effort) {
		this.story = story;
		this.usage = usage;
		this.business = business;
		this.effort = effort;
	}

	public Long getID() {
		return id;
	}

	public void setID(Long id) {
		this.id = id;
	}

	public String getStory() {
		return story;
	}

	public void setStory(String story) {
		this.story = story;
	}

	public String getUsage() {
		return usage;
	}

	public void setUsage(String usage) {
		this.usage = usage;
	}

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public String getEffort() {
		return effort;
	}

	public void setEffort(String effort) {
		this.effort = effort;
	}

	public ReleaseParameters getReleaseParam() {
		return releaseParam;
	}

	public void setReleaseParam(ReleaseParameters releaseParam) {
		this.releaseParam = releaseParam;
	}

}
