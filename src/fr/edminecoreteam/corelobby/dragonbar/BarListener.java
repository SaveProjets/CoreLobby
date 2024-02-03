package fr.edminecoreteam.corelobby.dragonbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import fr.edminecoreteam.corelobby.Core;

public class BarListener
{
    private static final Core core = Core.getInstance();
    private final Random random = new Random();

    public void launch()
    {

        List<String> phrases = new ArrayList<String>(core.getConfig().getConfigurationSection("dragonbar").getKeys(false));

        String configPhrase = selectRandomString(phrases);
        String finalPhrase = core.getConfig().getString("dragonbar." + configPhrase);
        core.getBossBar().setTitle(encode(finalPhrase));
        new BukkitRunnable() {
            int t = 0;
            boolean dragonUpdate = false;
            public void run() {

                if (!dragonUpdate)
                {
                    if (t < 100)
                    {
                        ++t;
                        core.getBossBar().setHealth(t, 100);
                    }
                    else if (t == 100)
                    {
                        core.getBossBar().setHealth(t, 100);

                        String configPhrase = selectRandomString(phrases);
                        String finalPhrase = core.getConfig().getString("dragonbar." + configPhrase);
                        core.getBossBar().setTitle(encode(finalPhrase));
                        dragonUpdate = true;
                    }
                }
                else
                {
                    if (t > 0)
                    {
                        --t;
                        core.getBossBar().setHealth(t, 100);
                    }
                    else if (t == 0)
                    {
                        core.getBossBar().setHealth(1, 100);

                        String configPhrase = selectRandomString(phrases);
                        String finalPhrase = core.getConfig().getString("dragonbar." + configPhrase);
                        core.getBossBar().setTitle(encode(finalPhrase));
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
        return s
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
                .replace("{euro}", "€");
    }

}
