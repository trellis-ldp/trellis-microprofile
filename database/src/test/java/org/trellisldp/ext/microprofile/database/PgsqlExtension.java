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

import com.opentable.db.postgres.embedded.EmbeddedPostgres;

import org.apache.commons.text.RandomStringGenerator;
import org.junit.jupiter.api.extension.Extension;

final class PgsqlExtension implements Extension {

    private static EmbeddedPostgres pg = getDatabase();

    static EmbeddedPostgres getDatabase() {
        if (!Boolean.valueOf(System.getProperty("testing.external.pgsql", "false"))) {
            final int port = Integer.valueOf(System.getProperty("testing.pgsql.port", "0"));
            try {
                pg = EmbeddedPostgres.builder().setPort(port)
                    .setDataDirectory("build/testing/" + "pgdata-" + new RandomStringGenerator
                            .Builder().withinRange('a', 'z').build().generate(10)).start();
            } catch (final Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        return null;
    }

    private PgsqlExtension() {
        /* no-op */
    }
}
