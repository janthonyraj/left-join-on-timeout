package kafkastreams.leftjoin;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.concurrent.ScheduledFuture;

import static org.apache.commons.lang3.Validate.isTrue;

public class Scheduled<K, V> {
    public final K key;
    public final V value;
    public final long timestamp;

    @JsonIgnore
    private transient ScheduledFuture scheduledFuture;

    @JsonCreator
    public Scheduled(@JsonProperty("key") K key,
                     @JsonProperty("value") V value,
                     @JsonProperty("timestamp") long timestamp) {
        this.key = key;
        this.value = value;
        this.timestamp = timestamp;
    }

    public void setScheduledFuture(ScheduledFuture scheduledFuture) {
        isTrue(this.scheduledFuture == null, "Already initialized");
        this.scheduledFuture = scheduledFuture;
    }

    public ScheduledFuture getScheduledFuture() {
        return scheduledFuture;
    }

    @Override
    public String toString(){
        return String.format("key=%s, value=%s, timestamp=%s", key, value, timestamp);
    }
}