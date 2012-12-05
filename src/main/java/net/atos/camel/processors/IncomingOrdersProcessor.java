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


import java.util.List;

import net.atos.camel.entities.Order;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.cxf.message.MessageContentsList;
import org.apache.cxf.service.model.BindingOperationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class IncomingOrdersProcessor implements Processor {

    private static final transient Logger LOG = LoggerFactory.getLogger(IncomingOrdersProcessor.class);

    @SuppressWarnings("unchecked")
    public void process(Exchange exchange) throws Exception {
        LOG.info("Processing incoming orders (from Web Services)");

        BindingOperationInfo boi = (BindingOperationInfo) exchange.getProperty(BindingOperationInfo.class.toString());
        if (boi != null) {
            LOG.info("boi.isUnwrapped" + boi.isUnwrapped());
        }
        // Get the parameters list which element is the holder.
        MessageContentsList msgList = (MessageContentsList) exchange.getIn().getBody();

        
        List<Order> orders = (List<Order>) msgList.get(0);

        for (Order order : orders) {
            System.out.println("[ORDER id:" + order.getId() +
            		" partName: " + order.getPartName() + 
            		" amount: " + order.getAmount() + 
            		" customerName: " + order.getCustomerName() +"]");
        }

        exchange.getIn().setBody(orders);	// NOT SURE!!!
        exchange.getOut().setBody(orders);
        
        // Set the response message, first element is the return value of the operation,
        // the others are the holders of method parameters
        
//    	Map<String, Object> map = new HashMap<String, Object>();
//      map.put("ORDER", order);
 
        //exchange.getOut().setBody(map);

    }

}