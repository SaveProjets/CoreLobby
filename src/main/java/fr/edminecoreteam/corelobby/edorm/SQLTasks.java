package fr.edminecoreteam.corelobby.edorm;

import org.bukkit.scheduler.BukkitRunnable;

import fr.edminecoreteam.corelobby.Core;

public class SQLTasks extends BukkitRunnable
{
    public int timer;
    private Core api;
    public MySQL database;

    public SQLTasks(Core api, MySQL database) {
        this.database = database;
        this.api = api;
        this.timer = 10;
    }

    @Override
    public void run() {
        if (timer == 0)
        {
            if (database.isOnline())
            {
                api.setSQLState(SQLState.CONECTED);
                this.timer = 10;
            }
            if (!database.isOnline())
            {
                api.setSQLState(SQLState.DISCONECTED);
                api.MySQLConnect();
                cancel();
            }
        }
        --timer;
    }
}
