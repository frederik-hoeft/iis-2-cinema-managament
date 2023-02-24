package pluginSolution.plugin;

/*Generated by MPS */

import jetbrains.mps.plugins.actions.BaseKeymapChanges;
import com.intellij.openapi.actionSystem.Shortcut;
import com.intellij.openapi.actionSystem.KeyboardShortcut;
import javax.swing.KeyStroke;

public class keyMapRunGeneration_KeymapChanges extends BaseKeymapChanges {
  public keyMapRunGeneration_KeymapChanges() {
    // simple 
    addSimpleShortcut("pluginSolution.plugin.GenerateService_Action", getShortcut("alt C"));
    // simple parameterized 
    // complex 
  }
  public String getScheme() {
    return "$default";
  }
  public static Shortcut getShortcut(String stroke) {
    return new KeyboardShortcut(KeyStroke.getKeyStroke(stroke), null);
  }
}