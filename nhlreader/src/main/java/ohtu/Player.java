
package ohtu;

public class Player {
    private String name;
    private String team;
    private String goals;
    private String assists;
    private Integer points;
    private String nationality;

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getAssists() {
        return assists;
    }

    public void setAssists(String assists) {
        this.assists = assists;
    }
    private String penalties;

    public String getPenalties() {
        return penalties;
    }

    public void setPenalties(String penalties) {
        this.penalties = penalties;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getGoals() {
        return goals;
    }

    public void setGoals(String goals) {
        this.goals = goals;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public void setPoints() {
        this.points = Integer.valueOf(goals) + Integer.valueOf(assists);
    }
    
    public Integer getPoints() {
        return points;
    }

    @Override
    public String toString() {
        String nimi = String.format("%-20s", name);
        String pisteet = String.format("%2s", String.valueOf(getPoints()));
        String numero = String.format("%2s", assists);
        String maalit = String.format("%2s", goals);
        return nimi + "\t" + team + "\t Pisteet: " + maalit + " + " + numero + " = " + pisteet;
    }
      
}
