package com.singplayground.util;

import org.apache.http.HttpHost;
import org.apache.http.StatusLine;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.singplayground.model.MessageDTO;

public class NotificationHandler {
	private Logger logger = Logger.getLogger(NotificationHandler.class);

	private String proxyIp;
	private Integer proxyPort;
	private String proxyUsername;
	private String proxyPassword;

	public NotificationHandler(String proxyIp, Integer proxyPort, String proxyUsername, String proxyPassword) {
		this.proxyIp = proxyIp;
		this.proxyPort = proxyPort;
		this.proxyUsername = proxyUsername;
		this.proxyPassword = proxyPassword;
	}

	public CloseableHttpResponse sendGoogleNotificationPost(String url, String apiKey, MessageDTO messageDTO) {

		try {

			ObjectMapper mapper = new ObjectMapper();
			String submitJson = mapper.writeValueAsString(messageDTO);

			int timeout = 30;

			CloseableHttpResponse response = null;
			if (messageDTO.getRegistrationIds() == null || messageDTO.getRegistrationIds().size() == 0) {

				return null;
			} else {

				if (proxyIp == null || proxyIp.length() == 0) {

					CredentialsProvider credsProvider = new BasicCredentialsProvider();
					CloseableHttpClient httpclient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();

					StringEntity params = new StringEntity(submitJson, HTTP.UTF_8);
					RequestConfig timeoutConfig = RequestConfig.custom().setSocketTimeout(timeout * 1000).setConnectTimeout(timeout * 1000).build();

					// set the header and the content 
					HttpPost httpPost = new HttpPost(url);
					//httpPost.addHeader("User-Agent", "Mozilla/5.0");
					httpPost.addHeader("Content-Type", "application/json");
					httpPost.addHeader("Authorization", "key=" + apiKey);
					httpPost.setConfig(timeoutConfig);
					httpPost.setEntity(params);
					//System.out.println("submitJson : " + submitJson);
					//System.out.println("string : " + params.toString());
					logger.info("submitJson : " + submitJson);
					logger.info("string : " + params.toString());
					response = httpclient.execute(httpPost);

				} else {
					CredentialsProvider credsProvider = new BasicCredentialsProvider();
					credsProvider.setCredentials(new AuthScope(proxyIp, proxyPort), new UsernamePasswordCredentials(proxyUsername, proxyPassword));
					CloseableHttpClient httpclient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();

					HttpHost proxy = new HttpHost(proxyIp, proxyPort);
					RequestConfig config = RequestConfig.custom().setProxy(proxy).setSocketTimeout(timeout * 1000).setConnectTimeout(timeout * 1000).build();

					StringEntity params = new StringEntity(submitJson, HTTP.UTF_8);

					// set the header and the content 
					HttpPost httpPost = new HttpPost(url);
					httpPost.setConfig(config);
					httpPost.addHeader("Content-Type", "application/json");
					httpPost.addHeader("Authorization", "key=" + apiKey);

					httpPost.setEntity(params);

					response = httpclient.execute(httpPost);
				}
				StatusLine sl = response.getStatusLine();
				if (sl.getStatusCode() == 200) {
					System.out.println("this is the result of the string : " + EntityUtils.toString(response.getEntity()));
					logger.info("this is the result of the string : " + EntityUtils.toString(response.getEntity()));
				} else {
					System.out.println("cannot connect the google account");
					logger.info("cannot connect the google account");
					return null;
				}
				response.close();
			}
			return response;
		} catch (Exception e) {
			System.out.println("error : " + e);
			logger.error("error : " + e);
			return null;
		}
	}
}
