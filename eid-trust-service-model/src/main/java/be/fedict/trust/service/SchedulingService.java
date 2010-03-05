/*
 * eID Trust Service Project.
 * Copyright (C) 2009 FedICT.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License version
 * 3.0 as published by the Free Software Foundation.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, see 
 * http://www.gnu.org/licenses/.
 */

package be.fedict.trust.service;

import javax.ejb.Local;
import javax.ejb.Timer;

import be.fedict.trust.service.bean.HarvesterMDB;
import be.fedict.trust.service.entity.ClockDriftConfigEntity;
import be.fedict.trust.service.entity.TrustPointEntity;
import be.fedict.trust.service.exception.InvalidCronExpressionException;

/**
 * Scheduler service.
 * 
 * @author wvdhaute
 * 
 */
@Local
public interface SchedulingService {

	/**
	 * Timer has timeout, scheduler will notify the {@link HarvesterMDB} and
	 * create a new timer for the next update.
	 * 
	 * @param timer
	 */
	void timeOut(Timer timer);

	/**
	 * Start a new timer for the specified {@link TrustPointEntity}.
	 * 
	 * If update is set to <code>false</code>, it will ignore a previously set
	 * timer's fireDate
	 * 
	 * @param trustPoint
	 * @param update
	 * 
	 * @throws InvalidCronExpressionException
	 */
	void startTimer(TrustPointEntity trustPoint, boolean update)
			throws InvalidCronExpressionException;

	/**
	 * Start a new timer for the specified {@link ClockDriftConfigEntity}.
	 * 
	 * If update is set to <code>false</code>, it will ignore a previously set
	 * timer's fireDate
	 * 
	 * @param clockDriftDetectionConfig
	 * @param update
	 * 
	 * @throws InvalidCronExpressionException
	 */
	void startTimer(ClockDriftConfigEntity clockDriftDetectionConfig,
			boolean update) throws InvalidCronExpressionException;

	/**
	 * Cancel running {@link Timer}'s for the specified {@link TimerInfo}.
	 * 
	 * @param timerInfo
	 */
	void cancelTimers(TimerInfo timerInfo);
}
