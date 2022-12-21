package dk.signtool.shadow.modules;

import dk.signtool.shadow.Signtool;
import net.labymod.ingamegui.moduletypes.SimpleModule;
import net.labymod.settings.elements.ControlElement;
import net.minecraft.client.Minecraft;

public class Iron extends SimpleModule {
    private Signtool addon;

    public Iron(Signtool addon) {
        this.addon = addon;
    }

    @Override
    public String getDisplayName() {
        return "iron Ingots";
    }

    @Override
    public String getDisplayValue() {
        return "ok";
    }

    @Override
    public String getDefaultValue() {
        return null;
    }

    @Override
    public ControlElement.IconData getIconData() {
        return null;
    }

    @Override
    public void loadSettings() {

    }

    @Override
    public String getSettingName() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public int getSortingId() {
        return 0;
    }
}
