package mongo;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * User: pei.xu
 * Date: 15-3-9
 * Time: 下午6:25
 */
public class MongoTest {

    public static void main(String[] args) throws UnknownHostException {

        ServerAddress serverAddress = new ServerAddress("*******", 11);
        List<ServerAddress> seeds = new ArrayList<ServerAddress>();
        seeds.add(serverAddress);
        MongoCredential mongoCredential = MongoCredential.createMongoCRCredential("****", "****", "pwd".toCharArray());

        List<MongoCredential> credentialsList = new ArrayList<MongoCredential>();
        credentialsList.add(mongoCredential);

        MongoClient mongoClient = new MongoClient(seeds, credentialsList);
        DB db = mongoClient.getDB("test");
        System.out.println(db.getName());
    }
}
