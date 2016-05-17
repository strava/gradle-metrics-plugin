/*
 *  Copyright 2015-2016 Netflix, Inc.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package nebula.plugin.metrics;

import org.gradle.api.logging.LogLevel;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

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
    private static final String INDEX_PREFIX = "build-metrics-";
    private static final String LOGSTASH_INDEX_PREFIX = "logstash-";
    public static final DateTimeFormatter ROLLING_FORMATTER = DateTimeFormat.forPattern("yyyyMM");
    private static final LogLevel DEFAULT_LOG_LEVEL = LogLevel.WARN;

    public static final String DEFAULT_INDEX_NAME = "default";

    private String hostname = "localhost";
    private int transportPort = 9300;
    private int httpPort = 9200;
    private String clusterName = "elasticsearch";
    private String indexName = DEFAULT_INDEX_NAME;
    private LogLevel logLevel = DEFAULT_LOG_LEVEL;
    private String esBasicAuthUsername;
    private String esBasicAuthPassword;
    private boolean rollingIndex = false;
    private String metricsIndexMappingFile; // location of mapping file used to create the rolling metrics index (optional)

    private String restUri = "http://localhost/metrics";
    private String restBuildEventName = "build_metrics";
    private String restLogEventName = "build_logs";

    private String splunkUri = "http://localhost/";
    private String splunkAuthHeader = "httpCollectorToken";
    private String splunkInputType = "HTTP_COLLECTOR";

    private DispatcherType dispatcherType = DispatcherType.ES_HTTP;
    private List<String> sanitizedProperties = new ArrayList<>();
    private boolean failOnError = true;
    private boolean verboseErrorOutput = false;

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = checkNotNull(hostname);
    }

    public String getEsBasicAuthUsername() {
        return esBasicAuthUsername;
    }

    public void setEsBasicAuthUsername(String esBasicAuthUsername) {
        this.esBasicAuthUsername = checkNotNull(esBasicAuthUsername);
    }

    public String getEsBasicAuthPassword() {
        return esBasicAuthPassword;
    }

    public void setEsBasicAuthPassword(String esBasicAuthPassword) {
        this.esBasicAuthPassword = checkNotNull(esBasicAuthPassword);
    }

    public int getTransportPort() {
        return transportPort;
    }

    public void setTransportPort(int transportPort) {
        this.transportPort = transportPort;
    }

    public int getHttpPort() {
        return httpPort;
    }

    public void setHttpPort(int httpPort) {
        this.httpPort = httpPort;
    }

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = checkNotNull(clusterName);
    }

    public String getLogstashIndexName() {
        String rollingSuffix = "-" + ROLLING_FORMATTER.print(DateTime.now());
        return LOGSTASH_INDEX_PREFIX + INDEX_PREFIX + indexName + rollingSuffix;
    }

    public String getIndexName() {
        String name = INDEX_PREFIX + indexName;
        return rollingIndex ? name + "-" + ROLLING_FORMATTER.print(DateTime.now()) : name;
    }

    public void setIndexName(String indexName) {
        this.indexName = checkNotNull(indexName);
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = LogLevel.valueOf(logLevel.toUpperCase());
    }

    public DispatcherType getDispatcherType() {
        return dispatcherType;
    }

    public void setDispatcherType(String dispatcherType) {
        this.dispatcherType = DispatcherType.valueOf(dispatcherType.toUpperCase());
    }

    public List<String> getSanitizedProperties() {
        return sanitizedProperties;
    }

    public void setSanitizedProperties(List<String> sanitizedProperties) {
        this.sanitizedProperties = checkNotNull(sanitizedProperties);
    }

    public String getRestLogEventName() {
        return restLogEventName;
    }

    public void setRestLogEventName(String restLogEventName) {
        this.restLogEventName = checkNotNull(restLogEventName);
    }

    public String getSplunkUri() {
        return splunkUri;
    }

    public void setSplunkUri(String splunkUri) {
        this.splunkUri = checkNotNull(splunkUri);
    }

    public String getSplunkAuthHeader() {
        return splunkAuthHeader;
    }

    public void setSplunkAuthHeader(String splunkAuthHeader) {
        this.splunkAuthHeader = checkNotNull(splunkAuthHeader);
    }

    public String getSplunkInputType() {
        return splunkInputType;
    }

    public void setSplunkInputType(String splunkInputType) {
        this.splunkInputType = checkNotNull(splunkInputType);
    }

    public String getRestBuildEventName() {
        return restBuildEventName;
    }

    public void setRestBuildEventName(String restBuildEventName) {
        this.restBuildEventName = checkNotNull(restBuildEventName);
    }

    public String getRestUri() {
        return restUri;
    }

    public void setRestUri(String restUri) {
        this.restUri = checkNotNull(restUri);
    }

    public boolean isFailOnError() {
        return failOnError;
    }

    public void setFailOnError(boolean failOnError) {
        this.failOnError = failOnError;
    }

    public boolean isVerboseErrorOutput() {
        return verboseErrorOutput;
    }

    public void setVerboseErrorOutput(boolean verboseErrorOutput) {
        this.verboseErrorOutput = verboseErrorOutput;
    }

    public boolean isRollingIndex() {
        return rollingIndex;
    }

    public void setRollingIndex(boolean rollingIndex) {
        this.rollingIndex = rollingIndex;
    }

    public String getMetricsIndexMappingFile() {
        return metricsIndexMappingFile;
    }

    public void setMetricsIndexMappingFile(String metricsIndexMappingFile) {
        this.metricsIndexMappingFile = checkNotNull(metricsIndexMappingFile);
    }

    public enum DispatcherType {
        ES_CLIENT,
        ES_HTTP,
        SPLUNK,
        REST,
        NOOP
    }
}
