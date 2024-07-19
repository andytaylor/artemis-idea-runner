package org.apache.activemq.artemis.idea.artemisidearunner;

import com.intellij.openapi.options.Configurable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Objects;

public class ArtemisSettingsConfigurable implements Configurable {

   private ArtemisSettingsComponent artemisSettingsComponent;

   // A default constructor with no arguments is required because
   // this implementation is registered as an applicationConfigurable

   @Nls(capitalization = Nls.Capitalization.Title)
   @Override
   public String getDisplayName() {
      return "Artemis";
   }

   @Override
   public JComponent getPreferredFocusedComponent() {
      return artemisSettingsComponent.getPreferredFocusedComponent();
   }

   @Nullable
   @Override
   public JComponent createComponent() {
      artemisSettingsComponent = new ArtemisSettingsComponent();
      return artemisSettingsComponent.getPanel();
   }

   @Override
   public boolean isModified() {
      ArtemisSettings.State state =
            Objects.requireNonNull(ArtemisSettings.getInstance().getState());
      return !artemisSettingsComponent.getAmqInstallationDir().equals(state.amqInstallationDir);
   }

   @Override
   public void apply() {
      ArtemisSettings.State state =
            Objects.requireNonNull(ArtemisSettings.getInstance().getState());
      state.amqInstallationDir = artemisSettingsComponent.getAmqInstallationDir();
   }

   @Override
   public void reset() {
      ArtemisSettings.State state =
            Objects.requireNonNull(ArtemisSettings.getInstance().getState());
      artemisSettingsComponent.setAmqInstallationDir(state.amqInstallationDir);
   }

   @Override
   public void disposeUIResources() {
      artemisSettingsComponent = null;
   }

}