package data;


public class DataManager {
    private static DataManager dataManager = null;
    BodyPartsData [] bodyPartsData;
    public DataManager() {
        bodyPartsData = new BodyPartsData[6];
        for(int i=0; i<bodyPartsData.length; i++)
            bodyPartsData[i] = new BodyPartsData();
        setName(bodyPartsData);
    }
    public static DataManager getInstance() {
        if(dataManager == null)
            dataManager = new DataManager();
        return dataManager;
    }
    public void initialize() {
        for(var bodyParts : bodyPartsData) {
            if(bodyParts.getList() == null)
                bodyParts.setList();
        }
    }
    public BodyPartsData chooseBodyPart(String exeName) {
        for(var bodyPart : bodyPartsData) {
            if(bodyPart.name.equals(exeName))
                return bodyPart;
        }
        return null;
    }
    public BodyPartsData [] getArrBodyPart() {
        return bodyPartsData;
    }
    private void setName(BodyPartsData [] bodyPartsData) {
        bodyPartsData[0].name = "BARKI";
        bodyPartsData[1].name = "KLATKA PIERSIOWA";
        bodyPartsData[2].name = "BIC&TRIC";
        bodyPartsData[3].name = "PLECY";
        bodyPartsData[4].name = "BRZUCH";
        bodyPartsData[5].name = "NOGI";
    }
}
