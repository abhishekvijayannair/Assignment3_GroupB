/**
 * 
 */
package com.assign.grpb.model;

import java.time.LocalDate;

/**
 * @author abhis
 *
 */
public class MemberGrpB {

	private int memberID;
	private String memberName;
	private String memberAddress;
	private String memberType;
	private LocalDate memberJoinDate;
	private LocalDate memberExpireDate;

	public int getMemberID() {
		return memberID;
	}

	public void setMemberID(int memberID) {
		this.memberID = memberID;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberAddress() {
		return memberAddress;
	}

	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}

	public String getMemberType() {
		return memberType;
	}

	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}

	public LocalDate getMemberJoinDate() {
		return memberJoinDate;
	}

	public void setMemberJoinDate(LocalDate memberJoinDate) {
		this.memberJoinDate = memberJoinDate;
	}

	public LocalDate getMemberExpireDate() {
		return memberExpireDate;
	}

	public void setMemberExpireDate(LocalDate memberExpireDate) {
		this.memberExpireDate = memberExpireDate;
	}

}
