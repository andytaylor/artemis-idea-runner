package org.apache.activemq.artemis.idea.artemisidearunner;

import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.components.BaseState;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.NlsSafe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ArtemisConfigurationFactory  extends ConfigurationFactory {

   protected ArtemisConfigurationFactory(ConfigurationType type) {
      super(type);
   }

   @Override
   public @NotNull String getId() {
      return ArtemisRunConfigurationType.ID;
   }

   @NotNull
   @Override
   public RunConfiguration createTemplateConfiguration(
         @NotNull Project project) {
      return new ArtemisRunConfiguration(project, this, "Demo");
   }

   @Nullable
   @Override
   public Class<? extends BaseState> getOptionsClass() {
      return ArtemisRunConfigurationOptions.class;
   }

}
