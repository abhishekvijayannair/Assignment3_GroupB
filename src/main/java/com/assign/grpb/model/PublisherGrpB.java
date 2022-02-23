/**
 * 
 */
package com.assign.grpb.model;

/* 
 * Date -20/02/2022
 * Author - Abhishek
 * Description - This is the model class for Publishers with Getters and Setters
*/
public class PublisherGrpB {

	private int publisherId;
	private String publisherName;
	private String publisherAddress;

	public int getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(int publisherId) {
		this.publisherId = publisherId;
	}

	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	public String getPublisherAddress() {
		return publisherAddress;
	}

	public void setPublisherAddress(String publisherAddress) {
		this.publisherAddress = publisherAddress;
	}

}
