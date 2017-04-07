/* Only works with GeoIP Country Edition */
/* For Geoip City Edition, use CityLookup.java */

import com.maxmind.geoip.*;
import java.io.IOException;


/*
 Code Sample for retrieving country name from maxmin api
 */


class CountryLookup {
    public static void main(String[] args) {
        try {

            String dbfile = "Input/GeoIP.dat";
            LookupService cl = new LookupService(dbfile,LookupService.GEOIP_STANDARD);

            System.out.println(cl.getCountry("12.87.118.0").getCode());
            System.out.println(cl.getCountry("65.116.3.82").getName());
            System.out.println(cl.getCountry("89.160.20.112").getName());
            System.out.println(cl.getCountry("222.230.137.0").getName());
            System.out.println(cl.getCountry("89.92.213.223").getName());

            cl.close();
        }
        catch (IOException e) {
            System.out.println("IO Exception");
        }
    }
}