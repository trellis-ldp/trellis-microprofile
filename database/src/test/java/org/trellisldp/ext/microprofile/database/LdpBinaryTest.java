/*
 * Copyright (c) 2017 - 2020 Aaron Coburn and individual contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.trellisldp.ext.microprofile.database;

import static java.util.concurrent.TimeUnit.MINUTES;
import static javax.ws.rs.client.ClientBuilder.newBuilder;
import static org.junit.jupiter.api.condition.OS.WINDOWS;

import io.quarkus.test.common.http.TestHTTPResource;
import io.quarkus.test.junit.QuarkusTest;

import java.net.URL;

import javax.ws.rs.client.Client;

import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.extension.ExtendWith;
import org.trellisldp.test.LdpBinaryTests;

@DisabledOnOs(WINDOWS)
@ExtendWith(PgsqlExtension.class)
@QuarkusTest
class LdpBinaryTest implements LdpBinaryTests {

    @TestHTTPResource
    URL baseUrl;

    String resource;

    Client client = newBuilder().connectTimeout(2, MINUTES).build();

    @Override
    public void setResourceLocation(final String location) {
        resource = location;
    }

    @Override
    public String getResourceLocation() {
        return resource;
    }

    @Override
    public String getBaseURL() {
        return baseUrl.toString();
    }

    @Override
    public Client getClient() {
        return client;
    }
}

