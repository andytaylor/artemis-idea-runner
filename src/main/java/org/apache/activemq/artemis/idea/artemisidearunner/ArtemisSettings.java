package org.apache.activemq.artemis.idea.artemisidearunner;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

@State(
      name = "org.apache.activemq.artemis.idea.artemisidearunner.ArtemisSettings",
      storages = @Storage("ArtemisSettingsPlugin.xml")
)
public class ArtemisSettings implements PersistentStateComponent<ArtemisSettings.State> {

   static class State {
      @NonNls
      public String amqInstallationDir = "foo";
   }

   private State myState = new State();

   static ArtemisSettings getInstance() {
      return ApplicationManager.getApplication()
            .getService(ArtemisSettings.class);
   }

   @Override
   public State getState() {
      return myState;
   }

   @Override
   public void loadState(@NotNull State state) {
      myState = state;
   }

}