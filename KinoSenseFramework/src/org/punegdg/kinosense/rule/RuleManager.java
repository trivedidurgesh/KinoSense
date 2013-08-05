/**
 * Copyright 2012 Pune-GDG (http://meetup.com/pune-gdg)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package org.punegdg.kinosense.rule;

import java.util.ArrayList;

import org.punegdg.kinosense.actions.ActionIdConstants;
import org.punegdg.kinosense.triggers.TriggerIdConstants;

/**
 * @author saurabhg
 * 
 */
public class RuleManager
{

	/**
	 * 
	 */
	private RuleManager()
	{
	}

	private static RuleManager ruleManager;


	public static RuleManager getInstance()
	{
		if ( null == ruleManager )
		{
			ruleManager = new RuleManager();
		}
		return ruleManager;
	}


	/**
	 * Temporary method for populating rule list
	 */
	public ArrayList<Rule> createRules()
	{
		Rule rule1 = new Rule(0, TriggerIdConstants.DEVICE_FLIPPED_DOWN, ActionIdConstants.PHONE_SILENT, null);
		Rule rule2 = new Rule(0, TriggerIdConstants.DEVICE_FLIPPED_UP, ActionIdConstants.PHONE_RINGING, null);
		Rule rule3 = new Rule(0, TriggerIdConstants.POWER_CONNECTED_TRIGGER, ActionIdConstants.BRIGHTNESS_HIGH, null);

		ArrayList<Rule> ruleList = new ArrayList<Rule>(1);

		ruleList.add(rule1);
		ruleList.add(rule2);
		// ruleList.add(rule3);

		return ruleList;
	}
}
