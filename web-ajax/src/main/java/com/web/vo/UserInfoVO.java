package com.web.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Setter
@ToString
@Getter
public class UserInfoVO {
	private int uiNum;
	private String uiName;
	private String uiId;
	private String uiPwd;
	private String uiImgPath;
	private String uiDesc;
	private String uiBirth;
	private String credat;
	private String cretim;
	private String lmodat;
	private String lmotim;
	private String active;
}

class Execute {
	public static void main(String[] args) {
		UserInfoVO user = new UserInfoVO();
		user.setUiNum(1);
		user.setUiName("바이퍼");
		user.setUiId("viper");
		user.setUiPwd("viper");
		user.setUiImgPath(null);
		user.setUiDesc("미국 출신 화학자 바이퍼는 다양한 화학 무기로 전장을 장악하고 적의 시야를 차단합니다. 화학 공격으로부터 살아남는다고 해도 바이퍼의 심리전에 놀아날 뿐입니다.");
		user.setUiBirth("20210212");
		user.setCredat("20230811");
		user.setCretim("115900");
		user.setLmodat("20230811");
		user.setLmotim("120000");
		
		System.out.println(user.getUiId());
	}
}