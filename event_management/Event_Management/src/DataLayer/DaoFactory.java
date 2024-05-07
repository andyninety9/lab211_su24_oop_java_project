/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package DataLayer;

import DataLayer.Event.EventDao;
import DataLayer.Event.IEventDao;

/**
 *
 * @author duyma
 */
public class DaoFactory implements IDaoFactory {
    IFileManager fileManager;

    public DaoFactory() {
    }

    public DaoFactory(String inputDataFile) {
	this.fileManager = new FileManager(inputDataFile);
    }

    @Override
    public IEventDao eventDao() throws Exception {
	return new EventDao(fileManager);
    }

}
