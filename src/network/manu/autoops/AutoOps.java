package network.manu.autoops;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import network.manu.autoops.commands.AutoOpsCommand;
import network.manu.autoops.listeners.LoginListener;
import network.manu.autoops.pojos.Op;
import org.bson.UuidRepresentation;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class AutoOps extends JavaPlugin {
    public static Logger logger;
    public static FileConfiguration config;
    public static MongoClient mongoClient;
    public static MongoCollection<Op> autoOps;
    public static CodecRegistry codecRegistry;

    @Override
    public void onEnable() {
        logger = getLogger();

        this.saveDefaultConfig();
        config = this.getConfig();

        getServer().getPluginManager().registerEvents(new LoginListener(), this);

        this.getCommand("autoops").setExecutor(new AutoOpsCommand());

        codecRegistry =
                CodecRegistries.fromRegistries(
                        MongoClientSettings.getDefaultCodecRegistry(),
                        CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build())
                );
        ConnectionString connectionString = new ConnectionString((String)config.get("mongo-connection-uri"));
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .codecRegistry(codecRegistry)
                .uuidRepresentation(UuidRepresentation.STANDARD)
                .applyConnectionString(connectionString)
                .build();
        mongoClient = MongoClients.create(mongoClientSettings);
        autoOps = mongoClient.getDatabase((String)config.get("database")).getCollection("autoOps", Op.class);
    }

    @Override
    public void onDisable() {

    }
}
