package dk.signtool.shadow.listeners;

import dk.signtool.shadow.Signtool;
import dk.signtool.shadow.api.checkerUser;
import net.labymod.core.LabyModCore;
import net.labymod.main.LabyMod;
import net.labymod.utils.Consumer;
import net.labymod.utils.ServerData;
import net.minecraft.client.Minecraft;


public class JoinListener implements Consumer<ServerData> {
    private Signtool addon;
    public JoinListener(Signtool addon) {
        this.addon = addon;
    }


    @Override
    public void accept(final ServerData serverData) {
        Signtool.connectedToServer = true;

        //if (!Signtool.whitelisted) {
            //     try {
                //        Signtool.whitelisted = checkerUser.isWhitelisted(String.valueOf(Minecraft.getMinecraft().thePlayer.getName()));
                //    } catch (Exception e) {
                //        e.printStackTrace();
            //}
            //    //LabyModCore.getMinecraft().getPlayer().getName() virker ikke
            //}
        //ystem.out.println("String.valueOf(LabyModCore.getMinecraft().getPlayer()) " + LabyModCore.getMinecraft().getPlayer());
        //System.out.println("Signtool.whitelisted " + Signtool.whitelisted);
        //if (!Signtool.whitelisted) {
            //    LabyMod.getInstance().displayMessageInChat("§a§lSIGNTOOL §8§l-> §c§lFEJL.");
            //    LabyMod.getInstance().displayMessageInChat("§a§lSIGNTOOL §8§l-> §fDu er ikke §cwhitelistet.");
            //    addon.setEnabled(false, this);

            //}
        LabyMod.getInstance().displayMessageInChat("§a§lSIGNTOOL §8§l-> §fTilknytter " + serverData.getIp());
        addon.setEnabled(true, this);




    }
}
