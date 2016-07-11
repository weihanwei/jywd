package com.base;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.Map;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.delegate.DelegateExecution;

/**初基础信息提取到实例流程中*/
public class BaseStartAction  extends BascContext implements org.activiti.engine.delegate.ExecutionListener {
	@Override
	public void notify(DelegateExecution execution) throws Exception {
//		execution.getEngineServices().getIdentityService();
//		execution.getEngineServices().getTaskService()
		Map  s=execution.getVariables();
		RepositoryService repositoryService=   execution.getEngineServices().getRepositoryService();
		
		  String di=repositoryService.createProcessDefinitionQuery().processDefinitionId(execution.getProcessDefinitionId()).singleResult().getDeploymentId();
		   try {
			   ByteArrayInputStream bis = (ByteArrayInputStream)repositoryService.getResourceAsStream(di, "templatepath");
			   BufferedReader reader = new BufferedReader(new InputStreamReader(bis));   
			   s.put("templatepath", reader.readLine());
			   execution.setVariables(s);
			   reader.close();
			    bis = (ByteArrayInputStream)repositoryService.getResourceAsStream(di, "templatename");
			   reader = new BufferedReader(new InputStreamReader(bis));   
			   s.put("templatename", reader.readLine());
			   execution.setVariables(s);
			   reader.close();
			   reader=null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
