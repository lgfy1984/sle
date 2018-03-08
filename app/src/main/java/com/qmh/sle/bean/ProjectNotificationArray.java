package com.qmh.sle.bean;

import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;

/**
 * 一条项目通知
 * @created 2014-07-07
 * @author 火蚁（http://my.oschina.net/LittleDY）
 *
 */
@SuppressWarnings("serial")
public class ProjectNotificationArray implements Serializable {
	
	@JsonProperty("project")
	private ProjectNotification project;

	public ProjectNotification getProject() {
		return project;
	}

	public void setProject(ProjectNotification project) {
		this.project = project;
	}
}
