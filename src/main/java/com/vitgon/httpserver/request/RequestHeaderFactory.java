package com.vitgon.httpserver.request;

import java.util.Set;

import com.vitgon.httpserver.data.Header;
import com.vitgon.httpserver.data.Headers;
import com.vitgon.httpserver.data.RequestParameters;
import com.vitgon.httpserver.enums.HttpMethod;

public class RequestHeaderFactory {
	private byte[] headerData;
	private int bytesReceived;
	
	private HttpMethod method;
	private String uri;
	private String httpVersion;
	private Headers headers;
	private RequestParameters parameters;
	
	public RequestHeaderFactory() {
		headers = new Headers();
		parameters = new RequestParameters();
	}
	
	public void addHeaderData(byte[] headerBytes) {
		if (headerData == null) {
			headerData = new byte[headerBytes.length];
			System.arraycopy(headerBytes, 0, headerData, 0, headerBytes.length);
			bytesReceived = headerBytes.length;
		}
	}
	
	public byte[] getHeaderData() {
		return headerData;
	}

	public void addHeader(Header header) {
		headers.addHeader(header);
	}
	
	public String getHeader(String headerName) {
		return headers.getHeader(headerName);
	}

	public HttpMethod getMethod() {
		return method;
	}

	public void setMethod(HttpMethod method) {
		this.method = method;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getHttpVersion() {
		return httpVersion;
	}

	public void setHttpVersion(String httpVersion) {
		this.httpVersion = httpVersion;
	}

	public RequestParameters getParameters() {
		return parameters;
	}

	public void setParameters(RequestParameters parameters) {
		this.parameters = parameters;
	}
	
	public String getBoundary() {
		String contentTypeHeader = getHeader("Content-Type"); 
		String[] contentTypeHeaderParts = contentTypeHeader.split(";");
		if (contentTypeHeaderParts.length >= 1) {
			String boundaryKeyValue = contentTypeHeaderParts[1].trim();
			
			if (boundaryKeyValue.startsWith("boundary=")) {
				return boundaryKeyValue.split("=")[1];
			}
		}
		return null;
	}
	
	public Headers getHeaders() {
		return headers;
	}
}
