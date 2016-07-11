package com.base;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/**节点任务完成时触发*/
public class BaseCompleteHandler extends BascContext  implements TaskListener{
	@Override
	public void notify(DelegateTask delegateTask) {
	
	}
}
