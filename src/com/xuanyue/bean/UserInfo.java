package com.xuanyue.bean;

import org.json.JSONException;
import org.json.JSONObject;

public class UserInfo {
	public String uid;
	public String nickname;
	public String sex;
	public String birthday;
	public String head_portrait;
	public String signature;
	
	public void init(String id){
		uid=id;
		nickname="用户"+uid;
		sex="男";
		birthday="2017-01-01";
		head_portrait="1";
		signature="没有哦";
	}
	public void init2(String id){
		uid=id;
		nickname="用户"+uid;
		sex="女";
		birthday="2015-01-01";
		head_portrait="3";
		signature="有哦";
	}
	
	public void getInfoByJSON(JSONObject obj) throws JSONException{
		uid=obj.get("uid").toString();
		nickname=obj.get("nickname").toString();
		sex=obj.get("sex").toString();
		birthday=obj.get("birthday").toString();
		head_portrait=obj.get("head_portrait").toString();
		signature=obj.get("signature").toString();
	}

}
