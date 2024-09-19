package cz.prins.capriconnector.client.example;

import cz.prins.capriconnector.*;
import jakarta.xml.ws.BindingProvider;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;

public class Main {

    public static void main(String[] args) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, KeyManagementException, UnrecoverableKeyException {

        initSSLContext();

        DeliveryWebserviceService service = new DeliveryWebserviceService();
        final CapriConnector capriConnector = service.getCapriConnector();

        BindingProvider bindingProvider = (BindingProvider) capriConnector;
        final String productionUrl = "https://secure.prins.cz:8443/CapriConnector/";
        bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, productionUrl);


        try {
            ArticleInformationListDto articleInformationListDto = new ArticleInformationListDto();
            final MerchetIdDto merchetIdDto = new MerchetIdDto();
            merchetIdDto.setBrdName("MANN-FILTER");
            merchetIdDto.setMchArtNr("W 712");
            articleInformationListDto.getItem().add(merchetIdDto);
            final ArticleInformationResponseListDto resp = capriConnector
                    .getArticleInformation(
                            "USERNAME", //TODO fill
                            "PASSWORD", //TODO fill
                            articleInformationListDto
                    );
            System.out.println("Response: " + resp.getItem().getFirst().getMchId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void initSSLContext() throws NoSuchAlgorithmException, KeyStoreException, IOException, CertificateException, UnrecoverableKeyException, KeyManagementException {
        String keystorePath = "./cc-key.p12";
        String keystorePassword = "CERT_PASSWORD"; //TODO fill

        // Inicializace keystore
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        try (FileInputStream keyStoreStream = new FileInputStream(keystorePath)) {
            keyStore.load(keyStoreStream, keystorePassword.toCharArray());
        }

        // Inicializace KeyManagerFactory
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        keyManagerFactory.init(keyStore, keystorePassword.toCharArray());


        // Inicializace SSLContextu
        SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
        sslContext.init(keyManagerFactory.getKeyManagers(), null, new java.security.SecureRandom());

        // Nastavení SSLContextu jako výchozího pro HttpsURLConnection
        HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());

    }
}