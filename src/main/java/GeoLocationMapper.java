import com.maxmind.geoip.LookupService;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.filecache.DistributedCache;

import java.io.IOException;
import java.net.URI;

/**
 * Created by Rajiv on 4/6/17.
 */
public class GeoLocationMapper extends Mapper<Text, CustomLogRecord, Text, IntWritable> {

    private final IntWritable ONE = new IntWritable(1);
    private LookupService cl;
    private Text countryName;


    public void setup(Context context) throws IOException, InterruptedException {


        // Accesing from the Project Path
//        String dbfile = "Input/GeoIP.dat";
//        cl = new LookupService(dbfile, LookupService.GEOIP_STANDARD);

        //Accessing through Distributed cache
        URI[] cacheFiles = context.getCacheFiles();
        cl = new LookupService(cacheFiles[0].getPath(), LookupService.GEOIP_STANDARD);

    }

    public void map(Text key, CustomLogRecord value, Context context)
            throws IOException, InterruptedException {

        String ipAddr = value.getOriginatingIp();

        countryName = new Text(cl.getCountry(ipAddr).getName());
        context.write(countryName, ONE);
    }

}
