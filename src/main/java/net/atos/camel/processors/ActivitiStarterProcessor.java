/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.atos.camel.processors;

import java.util.HashMap;
import java.util.Map;

import net.atos.camel.entities.Order;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ActivitiStarterProcessor implements Processor {

	private static final transient Logger LOG = LoggerFactory
			.getLogger(ActivitiStarterProcessor.class);

	
    public void process(Exchange exchange) throws Exception {
        LOG.info("Preparing Activiti start parameters...");

        Order order = (Order) exchange.getIn().getBody();
        
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("ORDER", order);
 
        exchange.getIn().setBody(map);

    }
}