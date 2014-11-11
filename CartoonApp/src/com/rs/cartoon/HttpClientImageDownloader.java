package com.rs.cartoon;

	
	import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;

import android.content.Context;

import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
	
/**
 * Implementation of ImageDownloader which uses {@link HttpClient} for image stream retrieving.
 * 
 * @author Sergey Tarasevich (nostra13[at]gmail[dot]com)
 * @since 1.4.1
 */
public class HttpClientImageDownloader extends BaseImageDownloader {

	private HttpClient httpClient;

	public HttpClientImageDownloader(Context context, HttpClient httpClient) {
		super(context);
		this.httpClient = httpClient;
	}

//	@Override
//	protected InputStream getStreamFromNetwork(URI imageUri, Object extra) throws IOException {
//		HttpGet httpRequest = new HttpGet(imageUri.toString());
//		HttpResponse response = httpClient.execute(httpRequest);
//		HttpEntity entity = response.getEntity();
//		BufferedHttpEntity bufHttpEntity = new BufferedHttpEntity(entity);
//		return bufHttpEntity.getContent();
//	}
	
	@Override
	protected InputStream getStreamFromNetwork(String imageUri, Object extra) throws IOException {
		HttpGet httpRequest = new HttpGet(imageUri.toString());
		HttpResponse response = httpClient.execute(httpRequest);
		HttpEntity entity = response.getEntity();
		BufferedHttpEntity bufHttpEntity = new BufferedHttpEntity(entity);
		return bufHttpEntity.getContent();
	}
}
