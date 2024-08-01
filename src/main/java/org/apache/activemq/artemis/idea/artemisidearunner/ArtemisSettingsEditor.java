package org.apache.activemq.artemis.idea.artemisidearunner;

import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.options.SettingsEditor;

import com.intellij.openapi.ui.ComboBox;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.ui.components.JBCheckBox;
import com.intellij.ui.components.JBTextArea;
import com.intellij.ui.components.JBTextField;
import com.intellij.ui.components.fields.IntegerField;
import com.intellij.util.ui.FormBuilder;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class ArtemisSettingsEditor  extends SettingsEditor<ArtemisRunConfiguration> {

   private final JPanel myPanel;

   private final JBCheckBox cleanDataField;

   private final JBTextField artemisHostNameField;

   private final JBTextField artemisUserNameField;

   private final JBTextField artemisPasswordField;

   private final JBCheckBox artemisAllowAnonField;

   private final JBTextArea brokerPropertiesField;

   private final JBCheckBox clusteredField;

   private final JBCheckBox backupField;

   private final ComboBox<String> haTypeField;

   private final IntegerField portOffsetField;

   private final TextFieldWithBrowseButton dataDirectoryField;

   private final TextFieldWithBrowseButton extraDirectoryField;


   public ArtemisSettingsEditor() {
      cleanDataField = new JBCheckBox();
      artemisHostNameField = new JBTextField();
      artemisUserNameField = new JBTextField();
      artemisPasswordField = new JBTextField();
      artemisAllowAnonField = new JBCheckBox();
      brokerPropertiesField = new JBTextArea();
      clusteredField = new JBCheckBox();
      backupField = new JBCheckBox();
      haTypeField = new ComboBox<>(new String[]{"None", "Shared Store", "Replicated"});
      portOffsetField = new IntegerField();
      dataDirectoryField = new TextFieldWithBrowseButton();
      dataDirectoryField.addBrowseFolderListener("Select Data Directory", null, null,
            FileChooserDescriptorFactory.createSingleFileDescriptor());
      extraDirectoryField = new TextFieldWithBrowseButton();
      extraDirectoryField.addBrowseFolderListener("Select Extra Lib Directory", null, null,
            FileChooserDescriptorFactory.createSingleFileDescriptor());
      portOffsetField.setInputVerifier(new InputVerifier() {
         @Override
         public boolean verify(JComponent input) {
            try {
               Integer.parseInt(((JBTextField) input).getText());
               return true;
            } catch (NumberFormatException e) {
               return false;
            }
         }
      });
      myPanel = FormBuilder.createFormBuilder()
            .addLabeledComponent("Clean Data on every Run", cleanDataField)
            .addLabeledComponent("Artemis Host Name", artemisHostNameField)
            .addLabeledComponent("Artemis User Name", artemisUserNameField)
            .addLabeledComponent("Artemis Password", artemisPasswordField)
            .addLabeledComponent("Allow Anonymous", artemisAllowAnonField)
            .addLabeledComponent("Broker Properties", brokerPropertiesField)
            .addLabeledComponent("Clustered", clusteredField)
            .addLabeledComponent("Backup", backupField)
            .addLabeledComponent("HA Type", haTypeField)
            .addLabeledComponent("Port Offset", portOffsetField)
            .addLabeledComponent("Data Directory", dataDirectoryField)
            .addLabeledComponent("Extra Lib Directory", extraDirectoryField)
            .getPanel();
   }

   @Override
   protected void resetEditorFrom(ArtemisRunConfiguration runConfiguration) {
      cleanDataField.setSelected(runConfiguration.getCleanData());
      artemisHostNameField.setText(runConfiguration.getArtemisHostName());
      artemisUserNameField.setText(runConfiguration.getArtemisUserName());
      artemisPasswordField.setText(runConfiguration.getArtemisPassword());
      artemisAllowAnonField.setSelected(runConfiguration.getArtemisAllowAnon());
      brokerPropertiesField.setText(runConfiguration.getBrokerProperties());
      clusteredField.setSelected(runConfiguration.getClustered());
      backupField.setSelected(runConfiguration.getBackup());
      haTypeField.setItem(runConfiguration.getHAType());
      portOffsetField.setText("" + runConfiguration.getPortOffset());
      dataDirectoryField.setText(runConfiguration.getDataDirectory());
      extraDirectoryField.setText(runConfiguration.getExtraLibDirectory());
   }

   @Override
   protected void applyEditorTo(@NotNull ArtemisRunConfiguration artemisRunConfiguration) {
      artemisRunConfiguration.setCleanData(cleanDataField.isSelected());
      artemisRunConfiguration.setArtemisHostName(artemisHostNameField.getText());
      artemisRunConfiguration.setArtemisUserName(artemisUserNameField.getText());
      artemisRunConfiguration.setArtemisPassword(artemisPasswordField.getText());
      artemisRunConfiguration.setArtemisAllowAnon(artemisAllowAnonField.isSelected());
      artemisRunConfiguration.setBrokerProperties(brokerPropertiesField.getText());
      artemisRunConfiguration.setClustered(clusteredField.isSelected());
      artemisRunConfiguration.setBackup(backupField.isSelected());
      artemisRunConfiguration.setHAType(haTypeField.getItem());
      if (portOffsetField.getText() != null && portOffsetField.getText().length() > 0)
         artemisRunConfiguration.setPortOffset(Integer.valueOf(portOffsetField.getText()));

      if (artemisRunConfiguration.getOptions().getModificationCount() > 0) {
         artemisRunConfiguration.setCreateBroker(true);
      }
      artemisRunConfiguration.setDataDirectory(dataDirectoryField.getText());
      artemisRunConfiguration.setExtralibDirectory(extraDirectoryField.getText());
   }

   @NotNull
   @Override
   protected JComponent createEditor() {
      return myPanel;
   }
}