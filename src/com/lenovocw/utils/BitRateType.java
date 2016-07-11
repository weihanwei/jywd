package com.lenovocw.utils;

/**歌曲各比特率下的文件名后5位
 * @author linxiaohui
 *
 */
public enum BitRateType {
	
	bit_128 {
		@Override
		public String getBitString() {
			return "00800";
		}
	},
	bit_48 {
		@Override
		public String getBitString() {
			return "01400";
		}
	},
	bit_16 {
		@Override
		public String getBitString() {
			return "10400";
		}
	};
	
	public abstract  String getBitString();
}
