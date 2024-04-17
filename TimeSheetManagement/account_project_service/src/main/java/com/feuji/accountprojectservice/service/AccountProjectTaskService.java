package com.feuji.accountprojectservice.service;

import com.feuji.accountprojectservice.bean.AccountTaskBean;
import com.feuji.accountprojectservice.exception.IdNotFoundException;

public interface AccountProjectTaskService {
	AccountTaskBean getById(Integer taskId) throws IdNotFoundException;
}
