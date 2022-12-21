package dk.signtool.shadow;


import dk.signtool.shadow.listeners.*;
import dk.signtool.shadow.modules.Iron;
import dk.signtool.shadow.utils.TaDrawUtils;
import dk.signtool.shadow.utils.data.DataManagers;
import net.labymod.api.LabyModAddon;
import net.labymod.api.events.RenderIngameOverlayEvent;
import net.labymod.settings.elements.*;
import net.labymod.utils.Consumer;
import net.labymod.utils.Material;
import net.labymod.utils.ModColor;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.GuiScreenEvent;
import org.lwjgl.input.Keyboard;


import java.util.List;

public class Signtool extends LabyModAddon {
    public DataManagers dataManagers;
    private static Signtool addon;
    private boolean isWhitelisted = true;
    public boolean isSignToolsGuiOpen = false;
    public boolean opensign;
    public boolean FullBright;
    public Integer copyplayerslocation;
    public Integer playerwarpbind;
    public Integer signlinje;

    private boolean isEnabled;
    public static boolean connectedToServer;
    private static boolean signToolsIsEnabled = false;
    public static boolean whitelisted;

    public static boolean autoRespawn = false;

    public static boolean isSignToolsIsEnabled() {
        return signToolsIsEnabled;
    }

    public static void setSignToolsIsEnabled(boolean signToolsIsEnabled) {
        Signtool.signToolsIsEnabled = signToolsIsEnabled;
    }
    public void setEnabled(boolean enabled, JoinListener listener) {
        if(listener == this.joinListener){
            isEnabled = enabled;
        }
    }

    public void setWhitelisted(boolean whitelisted,JoinListener listener) {
        if(listener == this.joinListener) {
            isWhitelisted = whitelisted;
        }
    }

    private TaDrawUtils drawUtils;

    public static TaDrawUtils getDrawUtils() {
        return addon.drawUtils;
    }
    public boolean isEnabled() {
        return isEnabled;
    }


    private JoinListener joinListener;
    @Override
    public void onEnable() {
        this.drawUtils = new TaDrawUtils();
        addon = this;
        dataManagers = new DataManagers();
        isEnabled = false;
        this.joinListener = new JoinListener(this);

        this.getApi().registerForgeListener(new GuiListener(this));
        this.getApi().registerForgeListener(new KeyInput(this));

        this.getApi().getEventManager().registerOnJoin(this.joinListener);
        this.getApi().getEventManager().registerOnQuit(new QuitListener(this));
        //this.getApi().getEventManager().register((RenderIngameOverlayEvent) new DeathEvent(this));
        //this.getApi().registerModule(new Iron(addon));

    }


    @Override
    public void loadConfig() {
        this.opensign = !getConfig().has("opensign") || getConfig().get("opensign").getAsBoolean();
        this.copyplayerslocation = getConfig().has( "copylocation" ) ? getConfig().get( "copylocation" ).getAsInt() : Keyboard.KEY_Y;
        this.FullBright = !getConfig().has("FullBright") || getConfig().get("FullBright").getAsBoolean();
        this.playerwarpbind = getConfig().has( "Playerwarp menu" ) ? getConfig().get( "Playerwarp menu" ).getAsInt() : Keyboard.KEY_L;
        this.signlinje = getConfig().has( "signlinje" ) ? getConfig().get( "signlinje" ).getAsInt() : Keyboard.KEY_O;
        this.autoRespawn = !getConfig().has("Auto respawn") || getConfig().get("Auto respawn").getAsBoolean();
    }

    @Override
    protected void fillSettings(List<SettingsElement> subSettings ) {

        subSettings.add(new HeaderElement(ModColor.cl("f") + "Lavet af Mads_Gamer_DK"));
        subSettings.add(new BooleanElement("slår det fra og til.", this, new ControlElement.IconData(Material.STONE_BUTTON), "opensign", this.opensign));
        KeyElement playerwarp = new KeyElement("Playerwarp menu", new ControlElement.IconData(Material.STONE_BUTTON), playerwarpbind, new Consumer<Integer>() {
            @Override
            public void accept(Integer accepted) {
                if (accepted < 0) {
                    System.out.println("Set new key to NONE");
                    playerwarpbind = -1;
                    configSave();
                    return;
                }
                System.out.println("Set new key to " + Keyboard.getKeyName(accepted));
                playerwarpbind = accepted;
                configSave();
            }
        });
        subSettings.add(playerwarp);

        subSettings.add(new HeaderElement(ModColor.cl("f") + "Lavet af Shad0wsense"));

        KeyElement copylocation = new KeyElement("copylocation", new ControlElement.IconData(Material.STONE_BUTTON), copyplayerslocation, new Consumer<Integer>() {
            @Override
            public void accept(Integer accepted) {
                if (accepted < 0) {
                    System.out.println("Set new key to NONE");
                    copyplayerslocation = -1;
                    configSave();
                    return;
                }
                System.out.println("Set new key to " + Keyboard.getKeyName(accepted));
                copyplayerslocation = accepted;
                configSave();
            }
        });
        subSettings.add(copylocation);
        BooleanElement enable = new BooleanElement("FullBright", this, new ControlElement.IconData(Material.PAINTING), "FullBright", this.FullBright);

        enable.addCallback(callback -> {
            if (callback) {
                changeGama(10.0F);
            } else {
                changeGama(1.0F);
            }

        });
        subSettings.add(enable);

        KeyElement getsignlinje = new KeyElement("signlinje Virker ikke", new ControlElement.IconData(Material.STONE_BUTTON), signlinje, new Consumer<Integer>() {
            @Override
            public void accept(Integer accepted) {
                if (accepted < 0) {
                    System.out.println("Set new key to NONE");
                    signlinje = -1;
                    configSave();
                    return;
                }
                System.out.println("Set new key to " + Keyboard.getKeyName(accepted));
                signlinje = accepted;
                configSave();
            }
        });
        subSettings.add(getsignlinje);
        BooleanElement respawn = new BooleanElement("Auto respawn", this, new ControlElement.IconData(Material.PAINTING), "Auto repsawn", this.autoRespawn);
        subSettings.add(respawn);


    }

    public void changeGama(double gamma) {
        Minecraft.getMinecraft().gameSettings.gammaSetting = (float) gamma;
    }


    private void configSave(){
        getConfig().addProperty("copylocation", this.copyplayerslocation);
        getConfig().addProperty("Playerawarp menu", this.playerwarpbind);
        getConfig().addProperty("signlinje", this.signlinje);
    }


    public DataManagers getDataManagers() {
        return dataManagers;
    }
}
