package fr.edminecoreteam.corelobby;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import fr.edminecoreteam.corelobby.changehub.ChangeHubGui;
import fr.edminecoreteam.corelobby.changehub.ChangeHubItem;
import fr.edminecoreteam.corelobby.gui.game.shortgames.DeACoudreGui;
import fr.edminecoreteam.corelobby.visual.dragonbar.DragonBar;
import fr.edminecoreteam.corelobby.manager.edorm.MySQL;
import fr.edminecoreteam.corelobby.manager.edorm.SQLState;
import fr.edminecoreteam.corelobby.gui.*;
import fr.edminecoreteam.corelobby.visual.holograms.PlayerJoinPacketHolograms;
import fr.edminecoreteam.corelobby.listeners.*;
import fr.edminecoreteam.corelobby.gui.game.longgames.DeadSurvivorGui;
import fr.edminecoreteam.corelobby.gui.game.longgames.LongGamesGui;
import fr.edminecoreteam.corelobby.gui.game.shortgames.DeACoudreGui;
import fr.edminecoreteam.corelobby.gui.game.shortgames.PaintBallGui;
import fr.edminecoreteam.corelobby.gui.game.shortgames.ShortGamesGui;
import fr.edminecoreteam.corelobby.manager.gui.GuiManager;
import fr.edminecoreteam.corelobby.user.profile.ProfileGUI;
import fr.edminecoreteam.corelobby.user.profile.ProfileItem;
import fr.edminecoreteam.corelobby.user.profile.friends.FriendDemandGui;
import fr.edminecoreteam.corelobby.user.profile.friends.FriendGui;
import fr.edminecoreteam.corelobby.user.profile.group.GroupGui;
import fr.edminecoreteam.corelobby.user.profile.group.GroupListPlayerGui;
import fr.edminecoreteam.corelobby.user.profile.group.GroupTagCommand;
import fr.edminecoreteam.corelobby.user.profile.link.LinkCommand;
import fr.edminecoreteam.corelobby.user.profile.link.LinkGui;
import fr.edminecoreteam.corelobby.user.profile.settings.SettingGui;
import fr.edminecoreteam.corelobby.visual.scoreboard.JoinScoreboardEvent;
import fr.edminecoreteam.corelobby.visual.scoreboard.LeaveScoreboardEvent;
import fr.edminecoreteam.corelobby.visual.scoreboard.ScoreboardManager;
import fr.edminecoreteam.corelobby.utils.MagicSheep;
import fr.edminecoreteam.corelobby.utils.PingServers;
import fr.edminecoreteam.corelobby.utils.TablistRankJoinListener;
import fr.edminecoreteam.corelobby.utils.TitleBuilder;
import fr.edminecoreteam.corelobby.utils.minecraft.edbossbar.BossBar;
import fr.edminecoreteam.corelobby.utils.minecraft.edbossbar.BossBarEvent;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Core extends JavaPlugin implements PluginMessageListener {

    private static Core instance;
    public TitleBuilder title;
    private BossBar bossBar;
    private ScoreboardManager scoreboardManager;
    private ScheduledExecutorService executorMonoThread;
    private ScheduledExecutorService scheduledExecutorService;
    @Getter
    private GuiManager guiManager;
    private final List<String> foundGame;

    public MySQL database;
    private SQLState sqlState;
    public int srvNumber;

    public Core() {
        title = new TitleBuilder();
        foundGame = new ArrayList<String>();
    }

    /*
     * Méthode d'activation du plugin.
     */
    @Override
    public void onEnable() {
        saveDefaultConfig();

        MySQLConnect();
        loadListeners();
        loadCommands();
        ScoreboardManager();

        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);

        System.out.println("Core Lobby Enable.");
        PingServers pSrv = new PingServers();
        srvNumber = pSrv.getServerPerGroup();
        guiManager = new GuiManager(this);
    }

    /*
     * Méthode de désactivation du plugin.
     */
    @Override
    public void onDisable() {
        this.getServer().getMessenger().unregisterOutgoingPluginChannel(this);
        this.getServer().getMessenger().unregisterIncomingPluginChannel(this);
        getScoreboardManager().onDisable();
        MySQLDisconnect();
        System.out.println("Core Lobby Disable.");
    }

    /*
     * Méthode de connexion au serveur SQL.
     *
     * "jdbc:mysql://"
     */
    public void MySQLConnect()
    {
        instance = this;

        (this.database = new MySQL(instance, "jdbc:mysql://", this.getConfig().getString("mysql.host"), this.getConfig().getString("mysql.database"), this.getConfig().getString("mysql.user"), this.getConfig().getString("mysql.password"))).connexion();

        database.creatingTableSetting();
        database.creatingTableLink();
    }

    /*
     * Méthode de déconnexion au serveur SQL.
     */
    private void MySQLDisconnect()
    {
        database.deconnexion();
    }

    /*
     * Méthode de chargement des listeners.
     */
    private void loadListeners()
    {
        Core.instance = this;
        PluginManager pm = Bukkit.getPluginManager();

        this.bossBar = new BossBar("Edmine Network", 300);
        pm.registerEvents(new BossBarEvent(), this);
        
        pm.registerEvents(new MagicSheep(), this);
        pm.registerEvents(new MainInteractions(), this);
        pm.registerEvents(new ProfileItem(), this);
        pm.registerEvents(new MainItem(), this);
        pm.registerEvents(new ChangeHubItem(), this);
        pm.registerEvents(new ProfileGUI(), this);
        pm.registerEvents(new ChangeHubGui(), this);
        pm.registerEvents(new MainGui(), this);
        pm.registerEvents(new LongGamesGui(), this);
        pm.registerEvents(new GroupGui(), this);
        pm.registerEvents(new GroupListPlayerGui(), this);
        pm.registerEvents(new FriendGui(), this);
        pm.registerEvents(new FriendDemandGui(), this);
        pm.registerEvents(new SettingGui(), this);
        pm.registerEvents(new LinkGui(), this);
        pm.registerEvents(new PlayerChatListener(), this);
        pm.registerEvents(new TablistRankJoinListener(), this);
        pm.registerEvents(new EventListener(), this);
        pm.registerEvents(new PlayerJoinListener(), this);
        pm.registerEvents(new PlayerQuitListener(), this);
        pm.registerEvents(new DoubleJumpListener(), this);
        pm.registerEvents(new FoundGame(), this);

        pm.registerEvents(new PaintBallGui(), this);
        pm.registerEvents(new DeACoudreGui(), this);
        pm.registerEvents(new DeadSurvivorGui(), this);

        pm.registerEvents(new JoinScoreboardEvent(), this);
        pm.registerEvents(new LeaveScoreboardEvent(), this);

        pm.registerEvents(new PlayerJoinPacketHolograms(), this);

        DragonBar dragonBar = new DragonBar();
        dragonBar.launch();
    }

    private void loadCommands()
    {
        this.getCommand("game").setExecutor(new CommandGame());
        this.getCommand("grouptag").setExecutor(new GroupTagCommand());
        this.getCommand("link").setExecutor(new LinkCommand());
    }

    /*
     * Méthode de statut de l'instance MySQL
     * State List: DISCONECTED 0, CONECTED 1.
     */
    public void setSQLState(SQLState sqlState)
    {
        this.sqlState = sqlState;
    }
    public boolean isSQLState(SQLState sqlState)
    {
        return this.sqlState == sqlState;
    }

    public List<String> getFoundGame(){
        return this.foundGame;
    }

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message)
    {
        if (!channel.equals("BungeeCord"))
        {
            return;
        }
        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        String subchannel = in.readUTF();
        if (subchannel.equals("SomeSubChannel"))
        {
            // Use the code sample in the 'Response' sections below to read
            // the data.
        }
    }

    private void ScoreboardManager()
    {
        this.scheduledExecutorService = Executors.newScheduledThreadPool(16);
        this.executorMonoThread = Executors.newScheduledThreadPool(1);
        this.scoreboardManager = new ScoreboardManager();
    }

    /*
     * Méthode de retournement de l'instance.
     */
    public BossBar getBossBar() { return this.bossBar; }
    public ScoreboardManager getScoreboardManager() { return scoreboardManager; }
    public ScheduledExecutorService getExecutorMonoThread() { return executorMonoThread; }
    public ScheduledExecutorService getScheduledExecutorService() { return scheduledExecutorService; }

    public static Core getInstance() {  return Core.instance; }
    public static Plugin getPlugin() { return null; }
}
