package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
	private int id;
	private int views;
	private String regDate;
	private String updateDate;
	private int memberId;
	private String title;
	private String body;
	private String writerName;

}
