package de.mia.list;


public class ListModel {
    
	private  String image="";
    private  String entfernung="";
    private  String name="";
    private  String level="";
    private  String beschreibung="";
    private  String lat;
    private  String lon;
    
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getEntfernung() {
		return entfernung;
	}
	public void setEntfernung(String entfernung) {
		this.entfernung = entfernung+" m";
	}
	public String getDescription() {
		return beschreibung;
	}
	public void setDescription(String description) {
		this.beschreibung = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String _lat) {
		this.lat = _lat;
	}
	public String getLon() {
		return lon;
	}
	public void setLon(String _lon) {
		this.lon = _lon;
	}
	
	/***** compare "level"-Objekt whit an another "entfernung"-Object and return this value. It is for sort Function in ListActivity****/		
	public int compareToEntfernung(ListModel another) {
		// TODO Auto-generated method stub
		return this.entfernung.compareTo(another.entfernung);
	}

/***** compare "level"-Object whit an another "level"-Objekt and return this value. It is for sort Function in ListActivity****/
	public int compareToLevel(ListModel another) {
		// TODO Auto-generated method stub
		return this.level.compareTo(another.level);
	}

}