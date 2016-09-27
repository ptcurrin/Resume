package dataprovider;

import java.util.Date;

/**
 * Created by Patrick on 9/23/2016.
 */

public class League {

    private int _id;
    private String leagueId;
    private String name;
    private String userId;
    private String sportId;
    private int minimumAge;
    private int maximumAge;
    private Date startDate;
    private Date endDate;

    public League(int id, String lid, String nm, String uid,
                  String sid, int minAge, int maxAge, Date sDate, Date eDate){
        set_id(id);
        setLeagueId(lid);
        setName(nm);
        setUserId(uid);
        setSportId(sid);
        setMinimumAge(minAge);
        setMaximumAge(maxAge);
        setStartDate(sDate);
        setEndDate(eDate);
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(String leagueId) {
        this.leagueId = leagueId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSportId() {
        return sportId;
    }

    public void setSportId(String sportId) {
        this.sportId = sportId;
    }

    public int getMinimumAge() {
        return minimumAge;
    }

    public void setMinimumAge(int minimumAge) {
        this.minimumAge = minimumAge;
    }

    public int getMaximumAge() {
        return maximumAge;
    }

    public void setMaximumAge(int maximumAge) {
        this.maximumAge = maximumAge;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
