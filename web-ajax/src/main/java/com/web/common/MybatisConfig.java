package com.web.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.web.vo.UserInfoVO;

public class MybatisConfig {

	private static SqlSessionFactory SSF;
	private final static String CONFIG_PATH = "config/mybatis-config.xml";
	static {
		try {
			InputStream is = Resources.getResourceAsStream(CONFIG_PATH);
			SqlSessionFactoryBuilder ssfb = new SqlSessionFactoryBuilder();
			SSF = ssfb.build(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static SqlSessionFactory getSqlSessionFactory() {
		return SSF;
	}

	public static void main(String[] args) {
        try (SqlSession session = getSqlSessionFactory().openSession()) {
        	
            UserInfoVO newUser = new UserInfoVO();
            newUser.setUiName("test");
            newUser.setUiId("1234");
            newUser.setUiPwd("1234");
            
            session.insert("com.web.mapper.UserInfoMapper.insertUserInfo", newUser);
            session.commit();
            
            UserInfoVO updateUser = new UserInfoVO();
            updateUser.setUiNum(8); 
            updateUser.setUiName("test");
            updateUser.setUiPwd("1234");
            
            session.update("com.web.mapper.UserInfoMapper.updateUserInfo", updateUser);
            session.commit();
            
            int uiNumToDelete = 11; 
            session.delete("com.web.mapper.UserInfoMapper.deleteUserInfo", uiNumToDelete);
            session.commit();
            
            List<UserInfoVO> list = session.selectList("com.web.mapper.UserInfoMapper.selectUserInfoList");
            for (UserInfoVO userInfo : list) {
                System.out.println(userInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}