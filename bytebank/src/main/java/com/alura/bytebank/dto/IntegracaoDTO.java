package com.alura.bytebank.dto;

import java.sql.Date;

public class IntegracaoDTO {

	private Long codigo;
	private String object;
	private Date createdAt;
	private Date updatedAt;
	private String contactType;
	private String nome;
	private String cpf;
	private int mainContactId;
	private String website;
	private String occupation;
	
	public String getObject() {
		return object;
	}
	public void setObject(String object) {
		this.object = object;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public String getContactType() {
		return contactType;
	}
	public void setContactType(String contactType) {
		this.contactType = contactType;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public int getMainContactId() {
		return mainContactId;
	}
	public void setMainContactId(int mainContactId) {
		this.mainContactId = mainContactId;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public Long getCodigo() {
		return codigo;
	}

	
}
