package com.lenovocw.utils;

/**铃声类型枚举类
 * @author linxiaohui
 *
 */
public enum AudioType {
	/**彩铃**/
	CRBTAudio{
		public int getTypeValue(){
			return 1;
		}
	},
	/**振铃**/
	RingtoneAudio{
		public int getTypeValue(){
			return 2;
		}
	},
	/**全曲**/
	FullTrackAudio{
		public int getTypeValue(){
			return 4;
		}
	};

	public abstract int getTypeValue();
}
