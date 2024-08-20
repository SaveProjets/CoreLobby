package fr.edminecoreteam.corelobby.user.profile.friends;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FriendInfo
{
    private Map<String, FriendInfo> friendInfo;
    private String p;
    private FriendData friendData;
    //accountInfo = new HashMap<Player, AccountInfo>();

    public FriendInfo(String p) {
        this.p = p;
        this.friendInfo = new HashMap<String, FriendInfo>();
        this.friendData = new FriendData(p);
        this.friendInfo.put(p, this);
    }

    public FriendInfo getFriendInfos(String player) { return friendInfo.get(player); }

    public String getPlayerName() { return p; }

    public List<String> getFriendList() { return friendData.getFriendList(); }

    public int getFriendPageNumber() { return friendData.getFriendPageNumber(); }

    public List<String> getFriendForPage(int page, int playerSort) { return friendData.getFriendForPage(page, playerSort); }
    public int getFriendSort(){return friendData.getFriendSort();}
    public void setFriendSort(String p, int newSort){FriendData.setFriendSort(p, newSort);}

    public List<String> getFriendRequest() { return friendData.getFriendRequest(); }

    public int getFriendRequestPageNumber() { return friendData.getFriendRequestPageNumber(); }

    public List<String> getFriendRequestForPage(int page) { return friendData.getFriendRequestForPage(page); }

    public Integer getFriendCount() { return friendData.getFriendCount(); }

    public Integer getFriendDemandCount() { return friendData.getFriendDemandCount(); }
    public List<String> getFavList(){return friendData.getFavList();}
}
