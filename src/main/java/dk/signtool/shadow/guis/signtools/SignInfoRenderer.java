package dk.signtool.shadow.guis.signtools;

import dk.signtool.shadow.Signtool;



public class SignInfoRenderer {
    private String serverName;
    private String serverMotd;

    private String[] signLines;





    public SignInfoRenderer(String serverName, String[] signLines) {
        if(signLines.length == 4){
            this.signLines = signLines;
        }else{
            this.signLines = new String[] {"Line 1", "Line 2", "Line 3", "Line 4"};
        }
        this.init(serverName);
    }

    public void init(String serverName) {
        this.serverName = serverName;
        this.serverMotd = "Klik for at indlæse.";
    }



    public void drawEntry(int x, int y, int listWidth, int mouseX, int  mouseY) {
        int textOffset = 24*4;


        Signtool.getDrawUtils().drawString(serverName,x + textOffset + 3, y + 1,1.5D,0xFFFFFF, true);
        Signtool.getDrawUtils().drawString(serverMotd,x + textOffset + 3, y + 14,1.1D,0x808080, true);
        Signtool.getDrawUtils().drawSign(x,y,signLines[0], signLines[1],signLines[2],signLines[3]);
    }
}
