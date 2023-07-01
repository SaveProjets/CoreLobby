package fr.edminecoreteam.corelobby.dragonbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import fr.edminecoreteam.corelobby.Core;

public class BarListener
{
    private static Core core = Core.getInstance();

    private Player p;
    private Random random = new Random();

    public BarListener(Player p) {
        this.p = p;
    }

    public void launch()
    {
        BarUtil.sendBar(p, "", 0);
        List<String> phrases = new ArrayList<String>();

        for (String s : core.getConfig().getConfigurationSection("dragonbar").getKeys(false))
        {
            phrases.add(s);
        }

        String configPhrase = selectRandomString(phrases);
        String finalPhrase = core.getConfig().getString("dragonbar." + configPhrase);
        BarUtil.updateText(p, encode(finalPhrase));
        new BukkitRunnable() {
            int t = 0;
            boolean dragonUpdate = false;
            public void run() {

                if (Bukkit.getPlayer(p.getName()) == null) { cancel(); }

                if (dragonUpdate == false)
                {
                    if (t < 100)
                    {
                        ++t;
                        BarUtil.updateHealth(p, t);
                    }
                    else if (t == 100)
                    {
                        BarUtil.updateHealth(p, t);
                        List<String> phrases = new ArrayList<String>();

                        for (String s : core.getConfig().getConfigurationSection("dragonbar").getKeys(false))
                        {
                            phrases.add(s);
                        }

                        String configPhrase = selectRandomString(phrases);
                        String finalPhrase = core.getConfig().getString("dragonbar." + configPhrase);
                        BarUtil.updateText(p, encode(finalPhrase));
                        dragonUpdate = true;
                    }
                }
                else
                {
                    if (t > 0)
                    {
                        --t;
                        BarUtil.updateHealth(p, t);
                    }
                    else if (t == 0)
                    {
                        BarUtil.updateHealth(p, t);
                        List<String> phrases = new ArrayList<String>();

                        for (String s : core.getConfig().getConfigurationSection("dragonbar").getKeys(false))
                        {
                            phrases.add(s);
                        }

                        String configPhrase = selectRandomString(phrases);
                        String finalPhrase = core.getConfig().getString("dragonbar." + configPhrase);
                        BarUtil.updateText(p, encode(finalPhrase));
                        dragonUpdate = false;
                    }
                }

            }
        }.runTaskTimer((Plugin)Core.getInstance(), 0L, 2L);
    }

    private String selectRandomString(List<String> strings) {
        if (strings == null || strings.isEmpty())
        {
            throw new IllegalArgumentException("La liste de chaînes est vide ou nulle.");
        }

        int randomIndex = random.nextInt(strings.size());
        return strings.get(randomIndex);
    }

    private String encode(String s) {
        String encoded = s
                .replace("&", "§")
                .replace("{e1}", "é")
                .replace("{e2}", "è")
                .replace("{e3}", "ê")
                .replace("{e4}", "É")
                .replace("{e5}", "È")
                .replace("{e6}", "Ê")
                .replace("{i1}", "î")
                .replace("{i2}", "Î")
                .replace("{a1}", "à")
                .replace("{a2}", "â")
                .replace("{a3}", "À")
                .replace("{a4}", "Â")
                .replace("{o1}", "ô")
                .replace("{o2}", "Ô")
                .replace("{love}", "❤")
                .replace("{valide}", "✔")
                .replace("{unvalid}", "✘")
                .replace("{flocon}", "✵")
                .replace("{eclats}", "❁")
                .replace("{money}", "✪")
                .replace("{star}", "✯")
                .replace("{right}", "➡")
                .replace("{left}", "⬅")
                .replace("{up}", "⬆")
                .replace("{down}", "⬇")
                .replace("{right_select}", "➟")
                .replace("{alert}", "⚠")
                .replace("{separator}", "»")
                .replace("{inverseseparator}", "«")
                .replace("{player_name}", p.getName())
                .replace("{euro}", "€");

        return encoded;
    }

}
