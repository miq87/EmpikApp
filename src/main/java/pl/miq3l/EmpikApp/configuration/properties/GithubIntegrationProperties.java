package pl.miq3l.EmpikApp.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties("integration.github")
@ConstructorBinding
public class GithubIntegrationProperties {
    private final String rootUrl;
    private final String userPath;
    private final long readTimeout;
    private final long connectTimeout;

    public GithubIntegrationProperties(String rootUrl, String userPath, long readTimeout, long connectTimeout) {
        this.rootUrl = rootUrl;
        this.userPath = userPath;
        this.readTimeout = readTimeout;
        this.connectTimeout = connectTimeout;
    }

    public String getRootUrl() {
        return rootUrl;
    }

    public String getUserPath() {
        return userPath;
    }

    public long getReadTimeout() {
        return readTimeout;
    }

    public long getConnectTimeout() {
        return connectTimeout;
    }

    public String getCompleteUserPath() {
        return rootUrl + userPath;
    }
}
