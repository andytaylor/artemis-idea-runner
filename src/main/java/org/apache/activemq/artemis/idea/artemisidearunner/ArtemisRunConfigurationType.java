package org.apache.activemq.artemis.idea.artemisidearunner;

import com.intellij.execution.configurations.ConfigurationTypeBase;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.util.NotNullLazyValue;

public class ArtemisRunConfigurationType extends ConfigurationTypeBase {

   static final String ID = "ArtemisRunConfiguration";

   ArtemisRunConfigurationType() {
      super(ID, "Artemis", "Artemis run configuration type",
            NotNullLazyValue.createValue(() -> AllIcons.Nodes.Console));
      addFactory(new ArtemisConfigurationFactory(this));
   }

}