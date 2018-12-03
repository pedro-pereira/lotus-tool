/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uece.lotus.msc.designer.windowLTS;

import br.uece.lotus.Component;
import br.uece.lotus.msc.api.model.msc.bmsc.BmscComponent;
import br.uece.lotus.msc.api.model.msc.hmsc.HmscComponent;
import br.uece.lotus.msc.api.window.DefaultWindowManagerPluginMSC;
import br.uece.lotus.msc.app.project.ProjectExplorerPluginMSC;
import br.uece.seed.ext.ExtensionManager;

/**
 *
 * @author Bruno Barbosa
 */
public class LtsWindowManager extends DefaultWindowManagerPluginMSC<LtsWindowImpl> {
    
    @Override
    public void onStart(ExtensionManager extensionManager) throws Exception {
        super.onStart(extensionManager);
    }
    
    @Override
    protected void onShow(LtsWindowImpl window, Component c, ProjectExplorerPluginMSC mProjectExplorerDS) {
        window.setComponentLTS(c);
        window.createInitializeActionsVariablesView(mProjectExplorerDS);
    }
    
    @Override
    protected LtsWindowImpl onCreate() {
        try{
            return new LtsWindowImpl();
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    protected LtsWindowImpl onCreateStandadM(ProjectExplorerPluginMSC pep) {return null;}

    @Override
    protected void onShow(LtsWindowImpl window, HmscComponent buildDS) {}

    @Override
    protected void onShow(LtsWindowImpl window, BmscComponent cds) {}

    @Override
    protected void onHide(LtsWindowImpl window) {}
    
}