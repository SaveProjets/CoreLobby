package fr.edminecoreteam.corelobby.holograms;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import fr.edminecoreteam.corelobby.Core;

public class HoloConfigManager
{
    public Core core = (Core) Core.getPlugin();

    public FileConfiguration holocfg;
    public File holofile;


    public void setup()
    {
        holofile = new File(core.getDataFolder(), "holograms.yml");

        if (!holofile.exists())
        {
            try {
                holofile.createNewFile();
            } catch (IOException e) {
                // TODO: handle exception
            }
        }

        holocfg = YamlConfiguration.loadConfiguration(holofile);
        Bukkit.getServer().getConsoleSender().sendMessage("Configuration loaded...");
    }

    public FileConfiguration getHolograms()
    {
        return holocfg;
    }

    public void saveHolograms()
    {
        try {
            holocfg.save(holofile);
            Bukkit.getServer().getConsoleSender().sendMessage("Configuration saved...");
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void reloadHolograms()
    {
        holocfg = YamlConfiguration.loadConfiguration(holofile);
        Bukkit.getServer().getConsoleSender().sendMessage("Configuration reload...");
    }
}
