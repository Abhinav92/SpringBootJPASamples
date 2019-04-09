package com.example.DeliveryApp.AppPOC.EntityClasses;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "RELEASE_PARAMETERS", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "LOB", "YEAR", "QUARTER", "RELEASES" }) })
public class ReleaseParameters {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "REPORTID")
	private Long ID;

	@Column(name = "LOB")
	@NotNull
	private String LOB;

	@Column(name = "YEAR")
	@NotNull
	private String YEAR;

	@Column(name = "QUARTER")
	@NotNull
	private String QUARTER;

	@Column(name = "RELEASES")
	@NotNull
	private String RELEASES;

	@Column(name = "TITLE")
	private String Title;

	@Column(name = "DESCRIPTION")
	private String Description;

	@Column(name = "NAME")
	private String Name;

	@Column(name = "BUSINESSCONTACT")
	private String BusinessContact;

	@OneToMany(mappedBy = "releaseParam", cascade = CascadeType.PERSIST)
	private List<JiraHolder> jiraholder;

	@Column(name = "ADDITIONALINFO")
	private String AdditionalInfo;

	@Column(name = "PROGRESSBAR")
	private String ProgressBar;

	@Column(name = "ATTACHMENT")
	private String Attachement;

	public ReleaseParameters() {

	}

	public ReleaseParameters(Long iD, String lOB, String yEAR, String qUARTER, String rELEASES, String title,
			String description, String name, String businessContact, List<JiraHolder> jiraholder, String additionalInfo,
			String progressBar, String attachement) {
		this.ID = iD;
		this.LOB = lOB;
		this.YEAR = yEAR;
		this.QUARTER = qUARTER;
		this.RELEASES = rELEASES;
		this.Title = title;
		this.Description = description;
		this.Name = name;
		this.BusinessContact = businessContact;
		this.jiraholder = jiraholder;
		this.AdditionalInfo = additionalInfo;
		this.ProgressBar = progressBar;
		this.Attachement = attachement;
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public String getLOB() {
		return LOB;
	}

	public void setLOB(String lOB) {
		LOB = lOB;
	}

	public String getYEAR() {
		return YEAR;
	}

	public void setYEAR(String yEAR) {
		YEAR = yEAR;
	}

	public String getQUARTER() {
		return QUARTER;
	}

	public void setQUARTER(String qUARTER) {
		QUARTER = qUARTER;
	}

	public String getRELEASES() {
		return RELEASES;
	}

	public void setRELEASES(String rELEASES) {
		RELEASES = rELEASES;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getBusinessContact() {
		return BusinessContact;
	}

	public void setBusinessContact(String businessContact) {
		BusinessContact = businessContact;
	}

	public String getAdditionalInfo() {
		return AdditionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		AdditionalInfo = additionalInfo;
	}

	public List<JiraHolder> getJiraholder() {
		return jiraholder;
	}

	public void setJiraholder(List<JiraHolder> jiraholder) {
		this.jiraholder = jiraholder;
	}

	public String getProgressBar() {
		return ProgressBar;
	}

	public void setProgressBar(String progressBar) {
		ProgressBar = progressBar;
	}

	public String getAttachement() {
		return Attachement;
	}

	public void setAttachement(String attachement) {
		Attachement = attachement;
	}
}
