package dk.signtool.shadow.listeners;

import dk.signtool.shadow.Signtool;
import dk.signtool.shadow.guis.signtools.CustomGuiEditSign;
import dk.signtool.shadow.utils.ReflectionUtils;

import net.labymod.core.LabyModCore;
import net.labymod.labyconnect.packets.Packet;
import net.labymod.labyconnect.packets.PacketAddonMessage;
import net.labymod.main.LabyMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraft.client.gui.inventory.GuiEditSign;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static dk.signtool.shadow.Signtool.whitelisted;

public class GuiListener {
    Signtool addon;

    public GuiListener(Signtool addon) {
        this.addon = addon;
    }

    @SubscribeEvent
    public void onGuiOpen(GuiOpenEvent event) {

        if(whitelisted) {
            if (event.gui instanceof GuiEditSign) {
                if (!addon.opensign)
                    return;
                TileEntitySign sign = (TileEntitySign) ReflectionUtils.getPrivateFieldValueByType(event.gui, GuiEditSign.class, TileEntitySign.class);
                event.setCanceled(true);
                Minecraft.getMinecraft().displayGuiScreen(new CustomGuiEditSign(sign, addon));
            }
            if (event.gui instanceof GuiGameOver) {
                if (!Signtool.autoRespawn)
                    return;
                Minecraft.getMinecraft().thePlayer.respawnPlayer();
                //Minecraft.getMinecraft().thePlayer.removePotionEffect();
            }
        }
    }
}
