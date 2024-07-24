package org.apache.activemq.artemis.idea.artemisidearunner;

import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBTextField;
import com.intellij.util.ui.FormBuilder;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class ArtemisSettingsComponent {

   private final JPanel myMainPanel;
   private final TextFieldWithBrowseButton artemisInstallationDir = new TextFieldWithBrowseButton();

   public ArtemisSettingsComponent() {
      artemisInstallationDir.addBrowseFolderListener("Select Artemis Installation Directory", null, null,
            FileChooserDescriptorFactory.createSingleFileDescriptor());
      myMainPanel = FormBuilder.createFormBuilder()
            .addLabeledComponent(new JBLabel("AMQ Installation Directory:"), artemisInstallationDir, 1, false)
            .addComponentFillVertically(new JPanel(), 0)
            .getPanel();
   }

   public JPanel getPanel() {
      return myMainPanel;
   }

   public JComponent getPreferredFocusedComponent() {
      return artemisInstallationDir;
   }

   @NotNull
   public String getAmqInstallationDir() {
      return artemisInstallationDir.getText();
   }

   public void setAmqInstallationDir(@NotNull String installationDir) {
      artemisInstallationDir.setText(installationDir);
   }


}
