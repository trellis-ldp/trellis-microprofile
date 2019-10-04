/*
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
package org.trellisldp.ext.microprofile.triplestore;

import static java.util.Collections.emptySet;
import static java.util.concurrent.TimeUnit.MINUTES;
import static javax.ws.rs.client.ClientBuilder.newBuilder;

import io.quarkus.test.common.http.TestHTTPResource;
import io.quarkus.test.junit.QuarkusTest;

import java.net.URL;
import java.util.Set;

import javax.ws.rs.client.Client;

import org.trellisldp.test.LdpRdfTests;

@QuarkusTest
class LdpRdfTest implements LdpRdfTests {

    @TestHTTPResource
    URL baseUrl;

    String resource;

    Client client = newBuilder().connectTimeout(2, MINUTES).build();

    @Override
    public Set<String> supportedJsonLdProfiles() {
        return emptySet();
    }

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

