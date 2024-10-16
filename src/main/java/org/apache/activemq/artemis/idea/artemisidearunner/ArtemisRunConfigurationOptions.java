package org.apache.activemq.artemis.idea.artemisidearunner;

import com.intellij.execution.configurations.RunConfigurationOptions;
import com.intellij.openapi.components.StoredProperty;

public class ArtemisRunConfigurationOptions extends RunConfigurationOptions {

   private final StoredProperty<String> artemisHostName = string("localhost").provideDelegate(this, "hostName");

   private final StoredProperty<Boolean> cleanData = property(true).provideDelegate(this, "cleanData");

   private final StoredProperty<String> artemisUserName = string("admin").provideDelegate(this, "artemisUserName");

   private final StoredProperty<String> artemisPassword = string("password").provideDelegate(this, "artemisPassword");

   private final StoredProperty<Boolean> artemisAllowAnon = property(true).provideDelegate(this, "artemisAllowAnon");

   private final StoredProperty<String> brokerProperties = string("").provideDelegate(this, "brokerProperties");

   private final StoredProperty<Boolean> clustered = property(false).provideDelegate(this, "clustered");

   private final StoredProperty<Boolean> backup = property(false).provideDelegate(this, "backup");

   private final StoredProperty<String> HAType = string("None").provideDelegate(this, "HAType");

   private final StoredProperty<Integer> portOffset = property(0).provideDelegate(this, "portOffset");

   private final StoredProperty<String> dataDirectory = string("").provideDelegate(this, "dataDirectory");

   private final StoredProperty<String> extraLibDirectory =  string("").provideDelegate(this, "extraLibDirectory");

   private final StoredProperty<String> bootstrapXML = string("").provideDelegate(this, "bootstrapXML");


   public String getArtemisHostName() {
      return artemisHostName.getValue(this);
   }

   public void setArtemisHostName(String host) {
      artemisHostName.setValue(this, host);
   }

   public String getArtemisUserName() {
      return artemisUserName.getValue(this);
   }

   public void setArtemisUserName(String userName) {
      artemisUserName.setValue(this, userName);
   }

   public String getArtemisPassword() {
      return artemisPassword.getValue(this);
   }


   public void setArtemisPassword(String password) {
      artemisPassword.setValue(this, password);
   }
   public Boolean getArtemisAllowAnon() {
      return artemisAllowAnon.getValue(this);
   }

   public void setArtemisAllowAnon(Boolean allowAnon) {
      artemisAllowAnon.setValue(this, allowAnon);
   }

   public Boolean getCleanData() {
      return cleanData.getValue(this);
   }

   public void setCleanData(Boolean clean) {
      cleanData.setValue(this, clean);
   }


   public String getBrokerProperties() {
      return brokerProperties.getValue(this);
   }

   public void setBrokerProperties(String properties) {
      brokerProperties.setValue(this, properties);
   }
   public ArtemisRunConfigurationOptions() {
      super();
   }

   public Boolean getClustered() {
      return clustered.getValue(this);
   }

   public void setClustered(Boolean isClustered) {
      clustered.setValue(this, isClustered);
   }

   public Boolean getBackup() {
      return backup.getValue(this);
   }

   public void setBackup(Boolean isBackup) {
      backup.setValue(this, isBackup);
   }

   public Integer getPortOffset() {
      return portOffset.getValue(this);
   }

   public void setPortOffset(Integer offset) {
      portOffset.setValue(this, offset);
   }

   public String getDataDirectory() {
      return dataDirectory.getValue(this);
   }

   public void setDataDirectory(String dir) {
      dataDirectory.setValue(this, dir);
   }

   public String getHAType() {
      return HAType.getValue(this);
   }

   public void setHAType(String haType) {
      HAType.setValue(this, haType);
   }

   public String getExtraLibDirectory() {
      return extraLibDirectory.getValue(this);
   }

   public void setExtraLibDirectory(String extraLibDirectory) {
      this.extraLibDirectory.setValue(this, extraLibDirectory);
   }

   public String getBootstrapXML() {
      return bootstrapXML.getValue(this);
   }

   public void setBootstrapXML(String bootstrapXML) {
      this.bootstrapXML.setValue(this, bootstrapXML);
   }
}
