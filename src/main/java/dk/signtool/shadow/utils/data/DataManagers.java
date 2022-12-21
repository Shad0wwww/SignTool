package dk.signtool.shadow.utils.data;

import com.google.gson.JsonObject;
import net.labymod.addon.AddonLoader;

import java.io.File;

public class DataManagers {

    private final JsonObject mcmmoData;
    private final DataManager<Data> mcmmoDataManager;

    private final JsonObject playerwarpData;
    private final DataManager<Data> playerwarpDataManager;

    private final JsonObject signData;
    private final DataManager<Data> signDataManager;

    public DataManagers(){
        this.mcmmoDataManager = new DataManager<Data>(new File(AddonLoader.getConfigDirectory() + "\\Signtool", "mcmmoData.json"), Data.class);
        this.mcmmoData = (this.mcmmoDataManager.getSettings()).getData();

        this.playerwarpDataManager = new DataManager<Data>(new File(AddonLoader.getConfigDirectory() + "\\Signtool", "playerwarp.json"), Data.class);
        this.playerwarpData = (this.playerwarpDataManager.getSettings()).getData();

        this.signDataManager = new DataManager<Data>(new File(AddonLoader.getConfigDirectory() + "\\Signtool", "signData.json"), Data.class);
        this.signData = (this.signDataManager.getSettings()).getData();

    }

    public void saveMCMMOData() {
        if (this.mcmmoDataManager != null) {
            this.mcmmoDataManager.save();
        }

    }

    public JsonObject getMCMMOData() {
        return this.mcmmoData;
    }

    public void savePlayerwarpData() {
        if (this.playerwarpDataManager != null) {
            this.playerwarpDataManager.save();
        }

    }

    public JsonObject getPlayerwarpData() {
        return this.playerwarpData;
    }

    public void saveSignData() {
        if (this.signDataManager != null) {
            this.signDataManager.save();
        }

    }

    public JsonObject getSignData() {
        return this.signData;
    }

}
