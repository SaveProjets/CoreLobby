package fr.edminecoreteam.corelobby.gui;

import fr.edminecoreteam.corelobby.changehub.ChangeHubData;

public class OnlinesForGroups
{
    @SuppressWarnings("unused")
    public static int getOnlinesFromGroup(String group)
    {
        if (group.equalsIgnoreCase("paintball2vs4"))
        {
            ChangeHubData data = new ChangeHubData("PaintBall_TwovsFour_");
            int i = 0;
            for (String s : data.getServer())
            {
                ChangeHubData newData = new ChangeHubData(s);
                i += newData.getServerOnlines();
            }
            return i;
        }
        if (group.equalsIgnoreCase("deacoudre"))
        {
            ChangeHubData data = new ChangeHubData("DeACoudre");
            int i = 0;
            for (String s : data.getServer())
            {
                ChangeHubData newData = new ChangeHubData(s);
                i += newData.getServerOnlines();
            }
            return i;
        }
        if (group.equalsIgnoreCase("deadsurvivor"))
        {
            ChangeHubData data = new ChangeHubData("DeadSurvivor");
            int i = 0;
            for (String s : data.getServer())
            {
                ChangeHubData newData = new ChangeHubData(s);
                i += newData.getServerOnlines();
            }
            return i;
        }
        return 0;
    }
}
