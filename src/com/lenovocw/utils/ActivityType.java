package com.lenovocw.utils;

public enum ActivityType {
	EARN(1, "赚流量"), GAME(8, "游戏专区"), MOVIE(6, "影视专区"), MUSIC(7, "音乐专区"), ;
	private Integer id;
	private String name;

	private ActivityType(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
