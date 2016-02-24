/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.github.dockerjava.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * @author xbkaishui
 * @version $Id: SwarmNode, v 0.1 2016 02 24 上午10:07 xbkaishui Exp $$
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SwarmNode {

	@JsonProperty("Name")
	private String name;

	@JsonProperty("ID")
	private String id;

	@JsonProperty("Addr")
	private String addr;

	@JsonProperty("IP")
	private String ip;

	@JsonProperty("Cpus")
	private String cpus;

	@JsonProperty("Memory")
	private String memory;

	@JsonProperty("Labels")
	private Map<String, String> labels;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getCpus() {
		return cpus;
	}

	public void setCpus(String cpus) {
		this.cpus = cpus;
	}

	public String getMemory() {
		return memory;
	}

	public void setMemory(String memory) {
		this.memory = memory;
	}

	public Map<String, String> getLabels() {
		return labels;
	}

	public void setLabels(Map<String, String> labels) {
		this.labels = labels;
	}
}
