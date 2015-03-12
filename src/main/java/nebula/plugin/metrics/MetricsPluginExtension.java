/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package nebula.plugin.metrics;

import org.gradle.api.invocation.Gradle;
import org.gradle.api.plugins.ExtensionContainer;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Nebula build metrics plugin extension.
 *
 * @author Danny Thomas
 */
public class MetricsPluginExtension {
    /**
     * The name used when adding this extension to the extension container.
     */
    public static final String METRICS_EXTENSION_NAME = "metrics";

    /**
     * Retrieve the metrics extension for the root project.
     *
     * @param gradle the {@link org.gradle.api.invocation.Gradle} instance to retrieve the root project from
     * @return the {@link MetricsPluginExtension} for the root project
     */
    public static MetricsPluginExtension getRootMetricsExtension(Gradle gradle) {
        ExtensionContainer extensions = gradle.getRootProject().getExtensions();
        return (MetricsPluginExtension) extensions.findByName(METRICS_EXTENSION_NAME);
    }

    private String hostname = "localhost";
    private int port = 9300;
    private String clusterName = "elasticsearch";

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = checkNotNull(hostname);
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = checkNotNull(clusterName);
    }
}