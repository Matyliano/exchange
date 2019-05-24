package matyliano.exchange.service;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class ClientHelper {

 public static RestTemplate getRestTemplate() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {

     TrustStrategy trustStrategy = new TrustStrategy() {
         @Override
         public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
             return false;
         }
     };
     // Trust own CA and all self-signed certs
     SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, trustStrategy).build();
     // Allow TLSv1 protocol only
     SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, new NoopHostnameVerifier());
     CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();


     HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
     httpComponentsClientHttpRequestFactory.setHttpClient(httpclient);

     RestTemplate restTemplate = new RestTemplate(httpComponentsClientHttpRequestFactory);

     return restTemplate;
 }









}
