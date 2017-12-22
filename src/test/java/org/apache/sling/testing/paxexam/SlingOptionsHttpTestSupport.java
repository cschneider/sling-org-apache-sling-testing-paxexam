/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.sling.testing.paxexam;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Dictionary;

import javax.inject.Inject;

import org.osgi.service.cm.ConfigurationAdmin;

public abstract class SlingOptionsHttpTestSupport extends SlingOptionsTestSupport {

    @Inject
    protected ConfigurationAdmin configurationAdmin;

    protected synchronized int findFreePort() {
        try {
            final ServerSocket serverSocket = new ServerSocket(0);
            final int port = serverSocket.getLocalPort();
            serverSocket.close();
            return port;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected int httpPort() throws IOException {
        final Dictionary<String, Object> properties = configurationAdmin.getConfiguration("org.apache.felix.http").getProperties();
        return Integer.parseInt(properties.get("org.osgi.service.http.port").toString());
    }

}