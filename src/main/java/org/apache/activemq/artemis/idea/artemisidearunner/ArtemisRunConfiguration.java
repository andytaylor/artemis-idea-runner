package org.apache.activemq.artemis.idea.artemisidearunner;

import com.intellij.execution.Executor;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.execution.configurations.RunConfigurationBase;
import com.intellij.execution.configurations.RunProfileState;
import com.intellij.execution.configurations.RuntimeConfigurationException;
import com.intellij.execution.runners.ExecutionEnvironment;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

public class ArtemisRunConfiguration  extends RunConfigurationBase<ArtemisRunConfigurationOptions> {

   private boolean createBroker = true;

   protected ArtemisRunConfiguration(Project project,
                                     ConfigurationFactory factory,
                                     String name) {
      super(project, factory, name);
   }

   @NotNull
   @Override
   protected ArtemisRunConfigurationOptions getOptions() {
      return (ArtemisRunConfigurationOptions) super.getOptions();
   }

   @Override
   public void checkConfiguration() throws RuntimeConfigurationException {
      super.checkConfiguration();
   }

   public String getArtemisHostName() {
      return getOptions().getArtemisHostName();
   }

   public void setArtemisHostName(String host) {
      getOptions().setArtemisHostName(host);
   }

   public String getArtemisUserName() {
      return getOptions().getArtemisUserName();
   }

   public void setArtemisUserName(String username) {
      getOptions().setArtemisUserName(username);
   }

   public String getArtemisPassword() {
      return getOptions().getArtemisPassword();
   }

   public void setArtemisPassword(String password) {
      getOptions().setArtemisPassword(password);
   }


   public Boolean getArtemisAllowAnon() {
      return getOptions().getArtemisAllowAnon();
   }

   public void setArtemisAllowAnon(Boolean allowAnon) {
      getOptions().setArtemisAllowAnon(allowAnon);
   }

   public Boolean getCleanData() {
      return getOptions().getCleanData();
   }

   public void setCleanData(Boolean clean) {
      getOptions().setCleanData(clean);
   }

   public Boolean getClustered() {
      return getOptions().getClustered();
   }

   public void setClustered(Boolean clustered) {
      getOptions().setClustered(clustered);
   }

   public Boolean getBackup() {
      return getOptions().getBackup();
   }

   public void setBackup(Boolean backup) {
      getOptions().setBackup(backup);
   }

   public String getBrokerProperties() {
      return getOptions().getBrokerProperties();
   }

   public void setBrokerProperties(String properties) {
      getOptions().setBrokerProperties(properties);
   }

   public Integer getPortOffset() {
      return getOptions().getPortOffset();
   }

   public void setPortOffset(Integer offset) {
      getOptions().setPortOffset(offset);
   }

   @NotNull
   @Override
   public SettingsEditor<? extends RunConfiguration> getConfigurationEditor() {
      return new ArtemisSettingsEditor();
   }

   @Nullable
   @Override
   public RunProfileState getState(@NotNull Executor executor,
                                   @NotNull ExecutionEnvironment environment) {
      return new ArtemisCommandLineState(this, environment);
   }

   public void setCreateBroker(boolean createBroker) {
      this.createBroker = createBroker;
   }

   public boolean getCreateBroker() {
      return createBroker;
   }

   public String getDataDirectory() {
      return getOptions().getDataDirectory();
   }

   public void setDataDirectory(String dir) {
      getOptions().setDataDirectory(dir);
   }

   public String getHAType() {
      return getOptions().getHAType();
   }

   public void setHAType(String haType) {
      getOptions().setHAType(haType);
   }
}