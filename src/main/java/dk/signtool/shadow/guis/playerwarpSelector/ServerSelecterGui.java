
package dk.signtool.shadow.guis.playerwarpSelector;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dk.signtool.shadow.Signtool;
import dk.signtool.shadow.utils.data.DataManagers;

import java.io.IOException;

import net.labymod.gui.elements.Scrollbar;
import net.labymod.gui.elements.Scrollbar.EnumMouseAction;
import net.labymod.main.LabyMod;

import net.labymod.utils.DrawUtils;
import net.labymod.utils.ModColor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class ServerSelecterGui extends GuiScreen {
    private Scrollbar scrollbar;
    private Signtool addon;
    private DataManagers dataManagers;
    private String hoverServer;

    public ServerSelecterGui(Signtool addon, DataManagers dataManagers) {
        this.addon = addon;
        this.dataManagers = dataManagers;
    }

    public void updateScreen() {
    }

    @Override
    public void initGui() {
        super.initGui();
        this.scrollbar = new Scrollbar(36);
        this.scrollbar.setPosition(this.width / 2 + 150 + 4, 41, this.width / 2 + 150 + 4 + 6, this.height - 40);
        this.scrollbar.setSpeed(20);
        this.scrollbar.setSpaceBelow(5);



        this.buttonList.add(new GuiButton(30007, this.width / 2 - 50 - 5 - 100, this.height - 30, 100, 20, "Remove Playerwarp"));
        this.buttonList.add(new GuiButton(30006, this.width / 2 - 50, this.height - 30, 100, 20, "Direct Playerwarp"));
        this.buttonList.add(new GuiButton(30005, this.width / 2 + 50 + 5, this.height - 30, 100, 20, "Add Playerwarp"));

        Integer buttonWidth = this.width / 5;
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        System.out.println("Clicked: Button with id " + button.id);
        super.actionPerformed(button);
        switch (button.id) {
            case 30005:
                Minecraft.getMinecraft().displayGuiScreen(new AddServerGui(this, true, dataManagers));
                break;
            case 30006:
                Minecraft.getMinecraft().displayGuiScreen(new AddServerGui(this, false, dataManagers));
                break;
            case 30007:
                Minecraft.getMinecraft().displayGuiScreen(new RemoveServerGui(this, dataManagers));

        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {

        DrawUtils draw = LabyMod.getInstance().getDrawUtils();

        draw.drawAutoDimmedBackground(this.scrollbar.getScrollY());
        //fejl = dataManagers
        JsonObject data = dataManagers.getPlayerwarpData();

        if (data.has("playerwarp")){
            JsonArray servers = dataManagers.getPlayerwarpData().getAsJsonArray("playerwarp");

            this.scrollbar.update(servers.size());

            int midX = this.width / 2;
            int entryWidth = 300;
            int entryHeight = 36;
            double posY = 45.0 + this.scrollbar.getScrollY();

            boolean hoveringServer = false;

            for(JsonElement json : servers) {
                JsonObject jsonObj = json.getAsJsonObject();
                String serverName = jsonObj.get("playerwarpNavn").toString();
                serverName = serverName.replace("\"", "");
                ServerInfoRenderer serverInfoRenderer = new ServerInfoRenderer(serverName);
                if((double)mouseY > posY && (double)mouseY < posY + (double)entryHeight && mouseX > midX - entryWidth / 2 && mouseX < midX + entryWidth / 2 + 5){
                    this.hoverServer = serverName;
                    hoveringServer = true;
                    draw.drawRect(midX - entryWidth / 2 - 2, posY - 2.0, midX + entryWidth / 2 + 2, posY + (double)entryHeight - 2.0, ModColor.toRGB(128,128, 128, 255));
                    draw.drawRect(midX - entryWidth / 2 - 1, posY - 1.0, midX + entryWidth / 2 + 1, posY + (double)entryHeight - 3.0, ModColor.toRGB(0, 0, 0, 255));
                }
                serverInfoRenderer.drawEntry(midX - entryWidth / 2, (int)posY, entryWidth + 5, mouseX, mouseY);
                posY += (double)entryHeight;
            }
            if(!hoveringServer){this.hoverServer = null;}
        }

        draw.drawOverlayBackground(0, 41);
        draw.drawGradientShadowTop(41.0, 0.0, this.width);
        draw.drawOverlayBackground(this.height - 40, this.height);
        draw.drawGradientShadowBottom(this.height - 40, 0.0, this.width);

        LabyMod.getInstance().getDrawUtils().drawCenteredString(ModColor.cl("8")+ModColor.cl("l")+"["+ModColor.cl("a")+ModColor.cl("l")+" Playerwarp Selecter "+ModColor.cl("8")+ModColor.cl("l")+"]", (double)(this.width / 2), 20.0D, 2.0D);
        this.scrollbar.draw();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        this.scrollbar.mouseAction(mouseX, mouseY, EnumMouseAction.CLICKED);
        if(hoverServer != null){
            Minecraft.getMinecraft().thePlayer.sendChatMessage("/playerwarp " + hoverServer);
        }
    }

    @Override
    public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
        super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
        this.scrollbar.mouseAction(mouseX, mouseY, EnumMouseAction.DRAGGING);
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {
        this.scrollbar.mouseAction(mouseX, mouseY, EnumMouseAction.RELEASED);
        super.mouseReleased(mouseX, mouseY, state);
    }

    @Override
    public void handleMouseInput() throws IOException {
        super.handleMouseInput();
        this.scrollbar.mouseInput();
    }
}