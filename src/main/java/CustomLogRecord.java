import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by Rajiv on 4/4/17.
 */
public class CustomLogRecord implements WritableComparable<CustomLogRecord> {

    private String originatingIp;
    private String userId;
    private String timestamp;
    private String requestType;
    private String responseCode;

    public CustomLogRecord() {
    }

    public CustomLogRecord(String originatingIp, String userId, String timestamp, String requestType, String responseCode) {
        this.originatingIp = originatingIp;
        this.userId = userId;
        this.timestamp = timestamp;
        this.requestType = requestType;
        this.responseCode = responseCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomLogRecord that = (CustomLogRecord) o;

        if (originatingIp != null ? !originatingIp.equals(that.originatingIp) : that.originatingIp != null)
            return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (timestamp != null ? !timestamp.equals(that.timestamp) : that.timestamp != null) return false;
        if (requestType != null ? !requestType.equals(that.requestType) : that.requestType != null) return false;
        return !(responseCode != null ? !responseCode.equals(that.responseCode) : that.responseCode != null);

    }

    @Override
    public int hashCode() {
        int result = originatingIp != null ? originatingIp.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        result = 31 * result + (requestType != null ? requestType.hashCode() : 0);
        result = 31 * result + (responseCode != null ? responseCode.hashCode() : 0);
        return result;
    }

    public String getOriginatingIp() {

        return originatingIp;
    }

    public void setOriginatingIp(String originatingIp) {
        this.originatingIp = originatingIp;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public int compareTo(CustomLogRecord o) {
        return 0;
    }

    public void write(DataOutput dataOutput) throws IOException {

    }

    public void readFields(DataInput dataInput) throws IOException {

    }
}
