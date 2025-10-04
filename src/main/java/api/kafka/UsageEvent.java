package api.kafka;

public class UsageEvent {
    private String subscriptionItemId;
    private long units;
    private long timestamp;

    public UsageEvent() {}

    public UsageEvent(String subscriptionItemId, long units, long timestamp) {
        this.subscriptionItemId = subscriptionItemId;
        this.units = units;
        this.timestamp = timestamp;
    }

    public String getSubscriptionItemId() {
        return subscriptionItemId;
    }

    public void setSubscriptionItemId(String subscriptionItemId) {
        this.subscriptionItemId = subscriptionItemId;
    }

    public long getUnits() {
        return units;
    }

    public void setUnits(long units) {
        this.units = units;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "UsageEvent{" +
                "subscriptionItemId='" + subscriptionItemId + '\'' +
                ", units=" + units +
                ", timestamp=" + timestamp +
                '}';
    }
}
