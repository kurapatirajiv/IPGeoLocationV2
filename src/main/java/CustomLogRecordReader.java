import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.LineRecordReader;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Rajiv on 4/4/17.
 */
public class CustomLogRecordReader extends RecordReader<Text, CustomLogRecord> {


    LineRecordReader lineReader;

    private Text key;
    private CustomLogRecord value;
    private String ipAddress;
    private String userID;
    private String timestamp;
    private String requestType;
    private String responseCode;


    //Performing the same function as line reader
    @Override
    public void initialize(InputSplit inputSplit, TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        lineReader = new LineRecordReader();
        lineReader.initialize(inputSplit, taskAttemptContext);

    }

    @Override
    public boolean nextKeyValue() throws IOException, InterruptedException {

        if (!lineReader.nextKeyValue()) {
            return false;
        }

        String row = lineReader.getCurrentValue().toString();

        if (row != null) {

            //Assigning the first part of the record as key
            key = new Text();
            key.set(row.split(" ")[0]);

            Pattern apacheLogPattern = Pattern.compile("^([0-9.]+)\\s([w.-]+)\\s([w.-]+)\\s\\[([^\\]]+)\\]\\s((?:[^\"]|\")+)\\s\"((?:[^\"]|\")+)");
            Matcher regexMatch = apacheLogPattern.matcher(row);

            if (regexMatch.matches()) {

                ipAddress = regexMatch.group(0).split(" ")[0];
                userID = regexMatch.group(2);
                timestamp = regexMatch.group(3);
                requestType = regexMatch.group(5).split(" ")[0].substring(1, 4);
                responseCode = regexMatch.group(5).split(" ")[3];
                value = new CustomLogRecord(ipAddress, userID, timestamp, requestType, responseCode);
            }

            // The record is not properly formatted

            else {
                System.out.println("No Match for the record: " + row);
                this.nextKeyValue();
            }

        } else {
            key = new Text();
            value = new CustomLogRecord();
        }
        return true;
    }

    @Override
    public Text getCurrentKey() throws IOException, InterruptedException {
        return this.key;
    }

    @Override
    public CustomLogRecord getCurrentValue() throws IOException, InterruptedException {
        return this.value;
    }

    @Override
    public float getProgress() throws IOException, InterruptedException {
        return lineReader.getProgress();
    }

    @Override
    public void close() throws IOException {
        lineReader.close();
    }


}
