package com.qmh.sle.bean;

import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;

/**
 * readme实体类
 * @created 2014-07-29
 * @author 火蚁（http://my.oschina.net/LittleDY）
 *
 */
@SuppressWarnings("serial")
public class ReadMe implements Serializable {

	@JsonProperty("content")
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
