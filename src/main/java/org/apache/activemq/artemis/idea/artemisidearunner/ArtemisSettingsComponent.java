package org.apache.activemq.artemis.idea.artemisidearunner;

import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBTextField;
import com.intellij.util.ui.FormBuilder;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class ArtemisSettingsComponent {

   private final JPanel myMainPanel;
   private final JBTextField amqInstallationDir = new JBTextField();

   public ArtemisSettingsComponent() {
      myMainPanel = FormBuilder.createFormBuilder()
            .addLabeledComponent(new JBLabel("AMQ Installation Directory:"), amqInstallationDir, 1, false)
            .addComponentFillVertically(new JPanel(), 0)
            .getPanel();
   }

   public JPanel getPanel() {
      return myMainPanel;
   }

   public JComponent getPreferredFocusedComponent() {
      return amqInstallationDir;
   }

   @NotNull
   public String getAmqInstallationDir() {
      return amqInstallationDir.getText();
   }

   public void setAmqInstallationDir(@NotNull String installationDir) {
      amqInstallationDir.setText(installationDir);
   }


}
