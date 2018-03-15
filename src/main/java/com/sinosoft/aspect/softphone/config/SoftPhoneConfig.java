package com.sinosoft.aspect.softphone.config;

import com.sinosoft.aspect.softphone.facade.Plugins;

public class SoftPhoneConfig {


    private Plugins eventPlugin;
    private Plugins plugins;

    /**
     *  Config Event plugin
     * @param plugins
     */
    public void configEventPlugin(Plugins plugins){
        this.eventPlugin = plugins;
    }
    
    /**

     * Config plugin

     */
    public void configPlugin(Plugins plugins){
        this.plugins = plugins;
    }
}
