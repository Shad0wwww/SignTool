package dk.signtool.shadow.utils;

import net.labymod.core.LabyModCore;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MovingObjectPosition;

import java.util.Arrays;
import java.util.Iterator;


public class Looking {

    public static Sign getSignLooking(){
        TileEntity tileEntity = getTileEntityLooking();

        return new Sign(tileEntity);
    }


    private static TileEntity getTileEntityLooking(){
        try {
            MovingObjectPosition movingObjectPosition = LabyModCore.getMinecraft().getPlayer().rayTrace(5, 1.0F);
            if (movingObjectPosition == null){
                return null;
            }
            BlockPos blockPos = movingObjectPosition.getBlockPos();
            if (blockPos == null){
                return null;
            }
            return LabyModCore.getMinecraft().getWorld().getTileEntity(blockPos);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static class Sign {
        private String value  = null;

        public Sign(TileEntity tileEntity){
            if(!(tileEntity instanceof TileEntitySign))
                return;
            TileEntitySign tileEntitySign = (TileEntitySign) tileEntity;

            if(tileEntitySign.signText != null) {

                this.value = String.valueOf(Arrays.stream(tileEntitySign.signText));


            }
        }

        public String getDisplay(){
            return this.value ;

        }
    }




        public String getCopy(){
            System.out.println("1");
            return null;
        }
}
