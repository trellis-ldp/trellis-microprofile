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
package org.trellisldp.ext.microprofile.database;

import static org.eclipse.microprofile.config.ConfigProvider.getConfig;

import com.opentable.db.postgres.embedded.EmbeddedPostgres;

import org.apache.commons.text.RandomStringGenerator;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

class PgsqlExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {

    private static EmbeddedPostgres pg;

    @Override
    public void beforeTestExecution(final ExtensionContext context) throws Exception {

        System.setProperty("quarkus.datasource.username", "postgres");
        if (getConfig().getOptionalValue("quarkus.external.pgsql", Boolean.class).orElse(false)) {
            System.clearProperty("quarkus.datasource.password");
            System.setProperty("quarkus.datasource.url", "jdbc:postgresql://localhost/trellis");
        } else {
            pg = EmbeddedPostgres.builder()
                .setDataDirectory("build/testing/" + "pgdata-" + new RandomStringGenerator
                        .Builder().withinRange('a', 'z').build().generate(10)).start();
            System.setProperty("quarkus.datasource.password", "postgres");
            System.setProperty("quarkus.datasource.url", "jdbc:postgresql://localhost:" + pg.getPort() + "/postgres");
        }
    }

    @Override
    public void afterTestExecution(final ExtensionContext context) throws Exception {
        System.clearProperty("quarkus.datasource.url");
        System.clearProperty("quarkus.datasource.username");
        System.clearProperty("quarkus.datasource.password");
        if (pg != null) {
            pg.close();
        }
    }
}
