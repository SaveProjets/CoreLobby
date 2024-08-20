package fr.edminecoreteam.corelobby;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import fr.edminecoreteam.corelobby.dragonbar.BarListener;
import fr.edminecoreteam.corelobby.utils.minecraft.edbossbar.BossBar;
import fr.edminecoreteam.corelobby.utils.minecraft.edbossbar.BossBarEvent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import fr.edminecoreteam.corelobby.changehub.ChangeHubGui;
import fr.edminecoreteam.corelobby.changehub.ChangeHubItem;
import fr.edminecoreteam.corelobby.edorm.MySQL;
import fr.edminecoreteam.corelobby.edorm.SQLState;
import fr.edminecoreteam.corelobby.holograms.PlayerJoinPacketHolograms;
import fr.edminecoreteam.corelobby.listeners.DoubleJumpListener;
import fr.edminecoreteam.corelobby.listeners.EventListener;
import fr.edminecoreteam.corelobby.listeners.PlayerChatListener;
import fr.edminecoreteam.corelobby.listeners.PlayerJoinListener;
import fr.edminecoreteam.corelobby.listeners.PlayerQuitListener;
import fr.edminecoreteam.corelobby.mainmenu.CommandGame;
import fr.edminecoreteam.corelobby.mainmenu.FoundGame;
import fr.edminecoreteam.corelobby.mainmenu.MainGui;
import fr.edminecoreteam.corelobby.mainmenu.MainInteractions;
import fr.edminecoreteam.corelobby.mainmenu.MainItem;
import fr.edminecoreteam.corelobby.mainmenu.longgame.DeadSurvivorGui;
import fr.edminecoreteam.corelobby.mainmenu.longgame.LongGamesGui;
import fr.edminecoreteam.corelobby.mainmenu.shortgames.DeACoudreGui;
import fr.edminecoreteam.corelobby.mainmenu.shortgames.PaintBallGui;
import fr.edminecoreteam.corelobby.mainmenu.shortgames.ShortGamesGui;
import fr.edminecoreteam.corelobby.profile.ProfileGUI;
import fr.edminecoreteam.corelobby.profile.ProfileItem;
import fr.edminecoreteam.corelobby.profile.friends.FriendDemandGui;
import fr.edminecoreteam.corelobby.profile.friends.FriendGui;
import fr.edminecoreteam.corelobby.profile.group.GroupGui;
import fr.edminecoreteam.corelobby.profile.group.GroupListPlayerGui;
import fr.edminecoreteam.corelobby.profile.group.GroupTagCommand;
import fr.edminecoreteam.corelobby.profile.link.LinkCommand;
import fr.edminecoreteam.corelobby.profile.link.LinkGui;
import fr.edminecoreteam.corelobby.profile.settings.SettingGui;
import fr.edminecoreteam.corelobby.scoreboard.JoinScoreboardEvent;
import fr.edminecoreteam.corelobby.scoreboard.LeaveScoreboardEvent;
import fr.edminecoreteam.corelobby.scoreboard.ScoreboardManager;
import fr.edminecoreteam.corelobby.utils.MagicSheep;
import fr.edminecoreteam.corelobby.utils.PingServers;
import fr.edminecoreteam.corelobby.utils.TablistRankJoinListener;
import fr.edminecoreteam.corelobby.utils.TitleBuilder;

public class Core extends JavaPlugin implements PluginMessageListener {

    private static Core instance;
    public TitleBuilder title;
    private BossBar bossBar;
    private ScoreboardManager scoreboardManager;
    private ScheduledExecutorService executorMonoThread;
    private ScheduledExecutorService scheduledExecutorService;
    private List<String> foundGame;

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
        pm.registerEvents((Listener)new BossBarEvent(), (Plugin)this);
        
        pm.registerEvents((Listener)new MagicSheep(), (Plugin)this);
        pm.registerEvents((Listener)new MainInteractions(), (Plugin)this);
        pm.registerEvents((Listener)new ProfileItem(), (Plugin)this);
        pm.registerEvents((Listener)new MainItem(), (Plugin)this);
        pm.registerEvents((Listener)new ChangeHubItem(), (Plugin)this);
        pm.registerEvents((Listener)new ProfileGUI(), (Plugin)this);
        pm.registerEvents((Listener)new ChangeHubGui(), (Plugin)this);
        pm.registerEvents((Listener)new MainGui(), (Plugin)this);
        pm.registerEvents((Listener)new ShortGamesGui(), (Plugin)this);
        pm.registerEvents((Listener)new LongGamesGui(), (Plugin)this);
        pm.registerEvents((Listener)new GroupGui(), (Plugin)this);
        pm.registerEvents((Listener)new GroupListPlayerGui(), (Plugin)this);
        pm.registerEvents((Listener)new FriendGui(), (Plugin)this);
        pm.registerEvents((Listener)new FriendDemandGui(), (Plugin)this);
        pm.registerEvents((Listener)new SettingGui(), (Plugin)this);
        pm.registerEvents((Listener)new LinkGui(), (Plugin)this);
        pm.registerEvents((Listener)new PlayerChatListener(), (Plugin)this);
        pm.registerEvents((Listener)new TablistRankJoinListener(), (Plugin)this);
        pm.registerEvents((Listener)new EventListener(), (Plugin)this);
        pm.registerEvents((Listener)new PlayerJoinListener(), (Plugin)this);
        pm.registerEvents((Listener)new PlayerQuitListener(), (Plugin)this);
        pm.registerEvents((Listener)new DoubleJumpListener(), (Plugin)this);
        pm.registerEvents((Listener)new FoundGame(), (Plugin)this);

        pm.registerEvents((Listener)new PaintBallGui(), (Plugin)this);
        pm.registerEvents((Listener)new DeACoudreGui(), (Plugin)this);
        pm.registerEvents((Listener)new DeadSurvivorGui(), (Plugin)this);

        pm.registerEvents((Listener)new JoinScoreboardEvent(), (Plugin)this);
        pm.registerEvents((Listener)new LeaveScoreboardEvent(), (Plugin)this);

        pm.registerEvents((Listener)new PlayerJoinPacketHolograms(), (Plugin)this);

        BarListener barListener = new BarListener();
        barListener.launch();
    }

    private void loadCommands()
    {
        this.getCommand("game").setExecutor((CommandExecutor)new CommandGame());
        this.getCommand("grouptag").setExecutor((CommandExecutor)new GroupTagCommand());
        this.getCommand("link").setExecutor((CommandExecutor)new LinkCommand());
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
