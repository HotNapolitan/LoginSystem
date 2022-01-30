package com.example.demo.auth;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepository {

	public User identifyUser(String email);
}
