package cc.fatenetwork.kcrates.database;

import cc.fatenetwork.kcrates.Crate;
import cc.fatenetwork.kcrates.profile.Profile;
import com.mongodb.*;
import com.mongodb.util.JSON;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.UUID;
import java.util.logging.Logger;

public class MongoManager {
    private final Logger log = Bukkit.getLogger();
    private MongoClient mongoClient;
    private DB db;
    private final Crate plugin;

    public MongoManager(Crate plugin) {
        this.plugin = plugin;
        log.info("[kCrate] starting database...");
        FileConfiguration config = plugin.getConfig();
        try {
            String host = config.getString("mongo.host");
            String auth = config.getString("mongo.auth");
            String user = config.getString("mongo.user");
            int port = config.getInt("mongo.port");
            String password = config.getString("mongo.password");
            MongoClientURI uri;
            uri = new MongoClientURI("mongodb://" + user + ":" + password + "@" + host + ":" + port + "/?authSource=" + auth);
            mongoClient = new MongoClient(uri);
            log.info("[kCrate] connected to database.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (mongoClient != null) {
            String database = config.getString("mongo.database");
            assert database != null;
            db = mongoClient.getDB(database);
        }
    }

    public void close() {
        mongoClient.close();
    }

    public void insertPlayer(UUID uuid) {
        Profile profile = plugin.getProfileManager().getProfile(uuid);
        Player player = Bukkit.getPlayer(uuid);
        DBCollection coll = db.getCollection("profiles");

        BasicDBObject doc = new BasicDBObject("uuid", uuid)
                .append("name", player.getName())
                .append("killKeys", String.valueOf(0));
        coll.insert(doc);
        profile.setKillKeys(0);
    }

    public BasicDBObject getUUID(UUID uuid) {
        DBCollection coll = db.getCollection("profiles");
        BasicDBObject query = new BasicDBObject("uuid", uuid);
        DBCursor cursor = coll.find(query);
        if (cursor.hasNext()) {
            return (BasicDBObject) cursor.next();
        } else {
            log.info("[KitPvP] uuid not found.");
        }

        return null;
    }

    public void update(UUID uuid) {
        Profile profile = plugin.getProfileManager().getProfile(uuid);
        DBCollection coll = db.getCollection("profiles");
        BasicDBObject object = getUUID(uuid);

        BasicDBObject edit = getUUID(uuid);

        edit.remove("killKeys");
        edit.put("killKeys", profile.getKillKeys());

        coll.update(object, edit);
    }

    public void getPlayer(UUID uuid) {
        DBCollection coll = db.getCollection("profiles");
        BasicDBObject query = new BasicDBObject("uuid", uuid);
        DBCursor cursor = coll.find(query);
        if (cursor.hasNext()) {
            JSON json = new JSON();
            String s = JSON.serialize(cursor.next());
            try {
                parse(s, uuid);
                log.info("[KitPvP] found " + uuid);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            log.info("[KitPvP] Player not found.");
        }
        cursor.close();
    }

    public void parse(String js, UUID uuid) throws ParseException {
        if (getUUID(uuid) == null) {
            return;
        }
        Profile profile;
        if (plugin.getProfileManager().getProfile(uuid) == null) {
            profile = new Profile(uuid);
            plugin.getProfileManager().getProfiles().put(uuid, profile);
        } else {
            profile = plugin.getProfileManager().getProfile(uuid);
        }
        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse(js);
        String killKeys = (String) object.get("killKeys");

        profile.setKillKeys(Integer.parseInt(killKeys));
    }
}
