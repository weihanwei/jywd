package com.lenovocw.utils;

public enum StatusCode {
	NO_FIND_DATA {
		@Override
		public String getMessage() { return "没有您所要查找的资源"; }

		@Override
		public int getCode() { return 4001; }
	},
	NO_LONG{
		@Override
		public String getMessage() { return "未登录"; }

		@Override
		public int getCode() { return 401; }
	},
	NO_LONG_IMSIDN{
		@Override
		public String getMessage() { return "不是有效的手机号码"; }

		@Override
		public int getCode() { return 1002; }
	},
	NO_AUTH {
		@Override
		public String getMessage() { return "您不具备相应权限"; }

		@Override
		public int getCode() { return 2001; }
	},
	ACTION_OK {
		@Override
		public String getMessage() { return "操作成功"; }

		@Override
		public int getCode() { return 2000; }
	},
	ACTION_NO {
		@Override
		public String getMessage() { return "操作失败"; }

		@Override
		public int getCode() { return 5000; }
	}
	;
	public abstract String getMessage();
	public abstract int getCode();
}
