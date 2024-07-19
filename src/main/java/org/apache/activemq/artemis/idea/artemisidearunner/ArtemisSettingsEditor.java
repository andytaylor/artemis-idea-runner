package org.apache.activemq.artemis.idea.artemisidearunner;

import com.intellij.openapi.options.SettingsEditor;
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

   private final JBTextField artemisUserNameField;

   private final JBTextField artemisPasswordField;

   private final JBCheckBox artemisAllowAnonField;

   private final JBTextArea brokerPropertiesField;

   private final JBCheckBox clusteredField;

   private final JBCheckBox backupField;

   private final IntegerField portOffsetField;


   public ArtemisSettingsEditor() {
      cleanDataField = new JBCheckBox();
      artemisUserNameField = new JBTextField();
      artemisPasswordField = new JBTextField();
      artemisAllowAnonField = new JBCheckBox();
      brokerPropertiesField = new JBTextArea();
      clusteredField = new JBCheckBox();
      backupField = new JBCheckBox();
      portOffsetField = new IntegerField();
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
            .addLabeledComponent("Artemis UserName", artemisUserNameField)
            .addLabeledComponent("Artemis Password", artemisPasswordField)
            .addLabeledComponent("Allow Anonymous", artemisAllowAnonField)
            .addLabeledComponent("Broker Properties", brokerPropertiesField)
            .addLabeledComponent("Clustered", clusteredField)
            .addLabeledComponent("Backup", backupField)
            .addLabeledComponent("Port Offset", portOffsetField)
            .getPanel();
   }

   @Override
   protected void resetEditorFrom(ArtemisRunConfiguration runConfiguration) {
      cleanDataField.setSelected(runConfiguration.getCleanData());
      artemisUserNameField.setText(runConfiguration.getArtemisUserName());
      artemisPasswordField.setText(runConfiguration.getArtemisPassword());
      artemisAllowAnonField.setSelected(runConfiguration.getArtemisAllowAnon());
      brokerPropertiesField.setText(runConfiguration.getBrokerProperties());
      clusteredField.setSelected(runConfiguration.getClustered());
      backupField.setSelected(runConfiguration.getBackup());
      portOffsetField.setText("" + runConfiguration.getPortOffset());
   }

   @Override
   protected void applyEditorTo(@NotNull ArtemisRunConfiguration artemisRunConfiguration) {
      artemisRunConfiguration.setCleanData(cleanDataField.isSelected());
      artemisRunConfiguration.setArtemisUserName(artemisUserNameField.getText());
      artemisRunConfiguration.setArtemisPassword(artemisPasswordField.getText());
      artemisRunConfiguration.setArtemisAllowAnon(artemisAllowAnonField.isSelected());
      artemisRunConfiguration.setBrokerProperties(brokerPropertiesField.getText());
      artemisRunConfiguration.setClustered(clusteredField.isSelected());
      artemisRunConfiguration.setBackup(backupField.isSelected());
      artemisRunConfiguration.setPortOffset(Integer.valueOf(portOffsetField.getText()));
   }

   @NotNull
   @Override
   protected JComponent createEditor() {
      return myPanel;
   }

}