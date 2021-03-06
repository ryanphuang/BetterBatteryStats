/*
 * Copyright (C) 2014 asksven
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.asksven.android.common.privateapiproxies;

import java.io.Serializable;
import java.util.List;

import com.asksven.android.common.dto.MiscDto;
import com.asksven.android.common.nameutils.UidInfo;
import com.google.gson.annotations.SerializedName;

import android.util.Log;

/**
 * @author sven
 *
 */
public class Notification extends StatElement implements Comparable<Notification>, Serializable
{
	
	/** 
	 * the tag for logging
	 */
	private static transient final String TAG = "Notification";
	
	/**
	 * the name of the object
	 */
	@SerializedName("name")
	private String m_name;
	
	/**
	 * the time on in ms
	 */
	@SerializedName("time_on_ms")
	private long m_timeOn;
	
	/**
	 * the time running in ms
	 */
	@SerializedName("time_runing_ms")
	private long m_timeRunning;

	/**
	 * Constructor
	 * @param name
	 * @param timeOn
	 * @param ratio
	 */
	public Notification(String name)
	{

		m_name			= name;
		m_timeOn		= 0;
		m_timeRunning	= 0;
	}
	
	public Notification(MiscDto source)
	{
		
		this.setUid(source.m_uid);
		this.m_name 		= source.m_name;
		this.m_timeOn 		= source.m_timeOn;
		this.m_timeRunning	= source.m_timeRunning;
		this.setTotal(source.m_total);
	}

	public MiscDto toDto()
	{
		MiscDto ret = new MiscDto();
		ret.m_uid			= this.getuid();
		ret.m_timeOn 		= this.m_timeOn;
		ret.m_timeRunning 	= this.m_timeRunning;
		ret.m_total		 	= this.getTotal();
		ret.m_name			= this.m_name;
	
		return ret;
	}

	public Notification clone()
	{
		Notification clone = new Notification(m_name);
		clone.setTotal(0);
		return clone;
	}


	/**
	 * @return the name
	 */
	public String getName()
	{
		return m_name;
	}

	/**
	 * @return the time on
	 */
	public long getTimeOn()
	{
		return m_timeOn;
	}

	/**
	 * @return the time running
	 */
	public long getTimeRunning()
	{
		return m_timeRunning;
	}

	/**
     * Compare a given Wakelock with this object.
     * If the duration of this object is 
     * greater than the received object,
     * then this object is greater than the other.
     */
	public int compareTo(Notification o)
	{
		// we want to sort in descending order
		return (int)( o.getTimeOn() - this.getTimeOn());
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Notification [m_name=" + m_name + "]";
	}

	/**
	 * returns a string representation of the data
	 */
	public String getData(long totalTime)
	{
		
		if (totalTime < getTimeOn())
		{
			totalTime = getTimeOn();
		}
		return this.formatDuration(getTimeOn())
		+ " " + formatRatio(getTimeOn(), totalTime);
	}

	/**
	 * returns a string representation of the data
	 */
	public String getVals()
	{
		
		return m_name;
	}

	/** 
	 * returns the values of the data
	 */	
	public double[] getValues()
	{
		double[] retVal = new double[2];
		retVal[0] = getTimeOn();
		return retVal;
	}
	
	public double getMaxValue()
	{
		return getTimeOn();            
    }
	
}

