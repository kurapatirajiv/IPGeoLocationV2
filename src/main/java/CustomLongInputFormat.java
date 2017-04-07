import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

import java.io.IOException;

/**
 * Created by Rajiv on 4/5/17.
 */
public class CustomLongInputFormat extends FileInputFormat<Text, CustomLogRecord> {

    @Override
    public RecordReader<Text, CustomLogRecord> createRecordReader(InputSplit inputSplit, TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        return new CustomLogRecordReader();
    }
}
