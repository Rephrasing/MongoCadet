package io.github.rephrasing.mongocadet;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.bukkit.plugin.java.JavaPlugin;

public class MongoCadetLoader extends JavaPlugin {

    private MongoClient client;
    private static MongoCadetLoader instance;
    @Override
    public void onEnable() {
        saveDefaultConfig();
        String connection = getConfig().getString("mongo-connection-string");

        if (connection == null || connection.isEmpty()) {
            getLogger().severe("Could not find a mongo-connection-string value in plugin configuration. cannot connect to MongoDB");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        client = MongoClients.create(connection);
        instance = this;
        getLogger().info("Successfully connected to MongoDB");
    }

    @Override
    public void onDisable() {
        if (instance == null) return;
        instance = null;
        client.close();
        getLogger().info("Successfully shutdown");
    }

    public static MongoCadetLoader getInstance() {
        return instance;
    }

    public MongoClient getMongoClient()
    {
        return client;
    }
}
