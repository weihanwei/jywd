package com.lenovocw.music.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FlowTC {

	private String mainTC;

	private List<String> giveTC = new ArrayList<String>();

	public String getMainTC() {
		return mainTC;
	}

	public void setMainTC(String mainTC) {
		this.mainTC = mainTC;
	}

	public List<String> getGiveTC() {
		return giveTC;
	}

	public void setGiveTC(List<String> giveTC) {
		this.giveTC = giveTC;
	}

}
