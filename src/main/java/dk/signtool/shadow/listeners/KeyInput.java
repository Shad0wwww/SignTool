package dk.signtool.shadow.listeners;

import dk.signtool.shadow.Signtool;
import dk.signtool.shadow.guis.playerwarpSelector.ServerSelecterGui;
import dk.signtool.shadow.utils.Looking;
import dk.signtool.shadow.utils.data.DataManagers;
import net.labymod.core.LabyModCore;
import net.labymod.main.LabyMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

import java.awt.*;
import java.awt.datatransfer.StringSelection;

import static dk.signtool.shadow.Signtool.connectedToServer;

public class KeyInput {
    private Signtool addon;


    public KeyInput(Signtool addon) {
        this.addon = addon;
    }


    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        System.out.println("Jep hippe er grim");
        try {

            if (!connectedToServer) {
                return;
            }
            if (!addon.isEnabled()) {
                return;
            }

            System.out.println(addon.copyplayerslocation);
            if (addon.copyplayerslocation >= 0) {
                if (Keyboard.isKeyDown(addon.copyplayerslocation)) {
                    Minecraft.getMinecraft().thePlayer.playSound("note.pling", 100, 1);
                    EntityPlayerSP player = LabyModCore.getMinecraft().getPlayer();

                    String playerPosX = String.valueOf((player == null) ? 0 : (int) (player.posX));
                    String playerPosY = String.valueOf((player == null) ? 0 : (int) (player.posY));
                    String playerPosZ = String.valueOf((player == null) ? 0 : (int) (player.posZ));



                    LabyMod.getInstance().displayMessageInChat("§a§lKopier" + " " + playerPosX + "," + " " + playerPosY + "," + " " + playerPosZ);
                    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(playerPosX + "," + " " + playerPosY + "," + " " + playerPosZ), null);
                }
            }
            if (addon.playerwarpbind >= 0){
                if (Keyboard.isKeyDown(addon.playerwarpbind)) {
                    Minecraft.getMinecraft().displayGuiScreen(new ServerSelecterGui(addon, new DataManagers()));
                }
            }
            if (addon.signlinje >= 0) {
                if (Keyboard.isKeyDown(addon.signlinje)) {
                    Looking.Sign sign = Looking.getSignLooking();
                    System.out.println("sign " + sign.getDisplay());
                }
            }

        } catch (Exception er) {
            System.out.println(er);
        }

    }





}
