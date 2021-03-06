package com.nbbang.chat.model.vo;

public class Message {
	private String sendNickName;
	private String msg;
	private String curMemsList;
	private String boardId;
	private String chatProfile;
	private String chatTime;

	public Message() {
		// TODO Auto-generated constructor stub
	}

	public Message(String sendNickName, String msg, String curMemsList, String boardId, String chatProfile,
			String chatTime) {
		super();
		this.sendNickName = sendNickName;
		this.msg = msg;
		this.curMemsList = curMemsList;
		this.boardId = boardId;
		this.chatProfile = chatProfile;
		this.chatTime = chatTime;
	}

	public String getSendNickName() {
		return sendNickName;
	}

	public void setSendNickName(String sendNickName) {
		this.sendNickName = sendNickName;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCurMemsList() {
		return curMemsList;
	}

	public void setCurMemsList(String curMemsList) {
		this.curMemsList = curMemsList;
	}

	public String getBoardId() {
		return boardId;
	}

	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}

	public String getChatProfile() {
		return chatProfile;
	}

	public void setChatProfile(String chatProfile) {
		this.chatProfile = chatProfile;
	}

	public String getChatTime() {
		return chatTime;
	}

	public void setChatTime(String chatTime) {
		this.chatTime = chatTime;
	}

	@Override
	public String toString() {
		return "Message [sendNickName=" + sendNickName + ", msg=" + msg + ", curMemsList=" + curMemsList + ", boardId="
				+ boardId + ", chatProfile=" + chatProfile + ", chatTime=" + chatTime + "]";
	}

}
