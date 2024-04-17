
package com.feuji.timesheetentryservice.service;

import java.util.Date;
import java.util.List;

import com.feuji.timesheetentryservice.bean.WeekAndDayDataBean;
import com.feuji.timesheetentryservice.dto.EmployeeDataDto;
import com.feuji.timesheetentryservice.dto.SaveAndEditRecordsDto;
import com.feuji.timesheetentryservice.dto.WeekAndDayDto;
import com.feuji.timesheetentryservice.entity.TimesheetDayEntity;
import com.feuji.timesheetentryservice.entity.TimesheetWeekEntity;

public interface TimeSheetDataService {

	public List<TimesheetWeekEntity> saveAll(List<WeekAndDayDataBean> yourJavaClassList, Date mondate);

	public List<WeekAndDayDto> fetchAllWeekDayRecordsById(Integer employeeId, Integer accountId, String weekStartDate,
			String weekEndDate);

	List<TimesheetDayEntity> deleteDayRecord(WeekAndDayDto weekAndDayDto);

	public List<TimesheetWeekEntity> saveOrUpdate(SaveAndEditRecordsDto weekAndDayDataBeans, String dateMon);

	public List<EmployeeDataDto> getEmployeeDetailsByIdAndAccountId(Integer accountId, Integer employeeId);

	public List<EmployeeDataDto> getReportingManagerByIdAndAccountId(Integer accountId, Integer employeeId);

	public void processPendingTimesheetsBySubmittedStatus() throws Exception;

	List<TimesheetWeekEntity> submittingTimesheet(String weekStartDate, Integer timesheetStatus, Integer accountId,
			Integer employeeId);

}
