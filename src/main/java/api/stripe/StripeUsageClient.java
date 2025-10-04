package api.stripe;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class StripeUsageClient {
    @Value("${stripe.api-key}")
    private String stripeApiKey;

    private final HttpClient httpClient;

    public StripeUsageClient() {
        this.httpClient = HttpClient.newHttpClient();
    }

    public void reportUsage(String subscriptionItemId, long quantity, long timestamp) throws Exception {
        String endpoint = String.format(
                "https://api.stripe.com/v1/subscription_items/%s/usage_records", subscriptionItemId
        );

        String body = String.format(
                "subscription_item=%s&quantity=%d&timestamp=%d&action=set",
                subscriptionItemId, quantity, timestamp
        );

        sendPostRequest(endpoint, body);
    }

    private void sendPostRequest(String url, String body) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Authorization", "Bearer " + stripeApiKey)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() / 100 != 2) { // Simple check for 2xx codes
            throw new RuntimeException(
                    "Failed to report usage. Status: " + response.statusCode() + ", Body: " + response.body()
            );
        }

        System.out.println("Reported usage to Stripe: " + response.body());
    }
}
